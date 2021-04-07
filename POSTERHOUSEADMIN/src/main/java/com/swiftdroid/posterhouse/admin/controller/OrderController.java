package com.swiftdroid.posterhouse.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swiftdroid.posterhouse.admin.model.CartItem;
import com.swiftdroid.posterhouse.admin.model.Order;
import com.swiftdroid.posterhouse.admin.model.Product;
import com.swiftdroid.posterhouse.admin.model.User;
import com.swiftdroid.posterhouse.admin.service.CartItemService;
import com.swiftdroid.posterhouse.admin.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartItemService cartItemService;

	@RequestMapping("/viewOrderDetails")
	public String viewOrderDetails(Model model, @RequestParam(name = "error", required = false) boolean error,
			@RequestParam(name = "pickUpSuccess", required = false) boolean pickUpSuccess) {

		List<Order> finaltodaysOrder = new ArrayList<Order>();

		List<Order> orderList = orderService.allOrder();
		for (Order order : orderList) {
			if (order.getUserPayment() != null)
				finaltodaysOrder.add(order);
		}
		model.addAttribute("orderList", finaltodaysOrder);

		if (error) {
			model.addAttribute("Error", true);
		}
		if (pickUpSuccess) {
			model.addAttribute("pickUpSuccess", true);
		}

		return "orderPage";

	}

	@GetMapping("/UpdateStatus")
	public String updateOrderStatus(@RequestParam("id") Long orderId, @RequestParam("status") String status,
			Model model) {
		Order order = orderService.findOrderById(orderId);
		order.setOrderStatus(status);
		List<Order> orderList = orderService.UpdateOrder(order);

		model.addAttribute("orderList", orderList);

		model.addAttribute("UpdateStatus", true);

		return "orderPage";

	}

	@GetMapping("/getAllRecentOrder")
	public String getAllOrdersFrom(Model model) {
		Date today = new Date();
		List<Order> finaltodaysOrder = new ArrayList<Order>();

		List<Order> orderList = orderService.findOrderByTodaysDate(today);
		for (Order order : orderList) {
			if (order.getUserPayment() != null)
				finaltodaysOrder.add(order);
		}
		model.addAttribute("orderList", finaltodaysOrder);
		return "orderPage";

	}

	@GetMapping("/userOrderInvoice")
	public String userInvoice(@RequestParam("id") Long id, Model model) {
		Order order = orderService.findOrderById(id);
		System.out.println(order.getUser());
		User user = order.getUser();
		model.addAttribute("user", user);
		model.addAttribute("order", order);
		model.addAttribute("cartItemList", order.getCartItemList());
		model.addAttribute("date", new Date());
		return "invoice";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> downloadFile(@RequestParam("id") Long orderId, @RequestParam("cartId") Long cartId,@RequestParam("count") Long count)
			throws IOException {

		Order order = orderService.findOrderById(orderId);
		CartItem userCartItem = cartItemService.getCartItemById(cartId);
		List<CartItem> CartItemList = order.getCartItemList();
System.out.println("order date :: "+order.getOrderDate());
		User user = order.getUser();

		for (CartItem cartItem : CartItemList) {

			if (cartItem.equals(userCartItem)) {

				Product product = cartItem.getProduct();
				// 223_670_784_1_20210407205530949 
					
				
				String fileName = user.getId() + "_" + product.getId() + "_" + order.getId() + "_"+count+"_"+order.getDownloadpath()+".png";
				// String FileName = websitePath + "/img/user/userproductImage/" + fileName;
				String photoName = "C:\\java\\POSTERHOUSE\\src\\main\\resources\\static\\img\\user\\userproductImage\\"
						+ fileName;

				File file = new File(photoName);
				InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

				HttpHeaders headers = new HttpHeaders();//223_670_764_1_20210407173509000
				headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", "image_"+count+"_"+user.getFirstName()
						+ "_" + product.getProductName() + "_" + order.getOrderDate() + "_" + file.getName()));
				headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
				headers.add("Pragma", "no-cache");
				headers.add("Expires", "0");

				ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
						.contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
					

				return responseEntity;
			}

		}
		return null;

	}

	@GetMapping("/orderDetail")
	public String OrderDetails(@RequestParam("id") Long orderId, Model model) {
		Order order = orderService.findOrderById(orderId);
		List<CartItem> CartItemList = order.getCartItemList();
		User user = order.getUser();

		System.out.println(CartItemList.size() == 1);
		System.out.println(
				CartItemList.get(0).getProduct().getProductType().getProductTypeName() + "  " + "3D Keychains");
		if (CartItemList.size() == 1 && CartItemList.get(0).getProduct().isAdminStatus() == false
				&& order.getTackingId() == null) {
			System.out.println("gggggggggggggggggggggggg");
			model.addAttribute("keychain", true);
		}

		model.addAttribute("order", order);
		model.addAttribute("userPayment", order.getUserPayment());

		model.addAttribute("CartItemList", CartItemList);

		model.addAttribute("user", user);

		model.addAttribute("user", user);
		return "orderDetails";

	}

	@GetMapping("/orderInvoiceByCategory")
	public String getOrderInvoiceByCategory(@RequestParam("catName") String catName, Model model) {

		List<Order> catOrderList = new ArrayList<>();

		List<Order> orderList = orderService.allOrder();

		for (Order order : orderList) {

			List<CartItem> cartItemList = order.getCartItemList();

			for (CartItem cartItem : cartItemList) {
				String categoryName = cartItem.getProduct().getProductType().getProductTypeName();

				System.out.println("OrderId  :  " + order.getId());

				System.out.println(categoryName + "==" + catName);

				if (cartItem.getProduct().getProductType().getProductTypeName().equals(catName)) {
					System.out.println("Inside.....");
					System.out.println();
					catOrderList.add(order);
					System.out.println(catOrderList.size());
				}

			}

		}

		model.addAttribute("catOrderList", catOrderList);

		return "multInvoice";

	}

	@PostMapping("/saveTrackingID")
	public String saveTrackingIdwithOrder(Model model, @RequestParam("orderId") Long orderId,
			@RequestParam("trackingID") Long trackingID) {
		System.out.println(orderId);
		System.out.println(trackingID);

		Order order = orderService.findOrderById(orderId);

		order.setTackingId(trackingID);

		orderService.saveOrder(order);

		model.addAttribute("trackingIdAdded", true);
		model.addAttribute("CartItemList", order.getCartItemList());
		model.addAttribute("order", order);
		model.addAttribute("userPayment", order.getUserPayment());

		return "orderDetails";
	}

}

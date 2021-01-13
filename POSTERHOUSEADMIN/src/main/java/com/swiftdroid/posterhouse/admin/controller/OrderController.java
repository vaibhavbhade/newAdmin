package com.swiftdroid.posterhouse.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
	public String viewOrderDetails(Model model) {

		List<Order> orderList = orderService.allOrder();

		model.addAttribute("orderList", orderList);
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
		List<Order> orderList = orderService.findOrderByTodaysDate(today);
		model.addAttribute("orderList", orderList);
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
	public ResponseEntity<Object> downloadFile(@RequestParam("id") Long orderId,@RequestParam("cartId") Long cartId) throws IOException {

		
	 
		
		Order order = orderService.findOrderById(orderId);
		CartItem userCartItem= cartItemService.getCartItemById(cartId);
		List<CartItem> CartItemList = order.getCartItemList();

		User user = order.getUser();

		for (CartItem cartItem : CartItemList) {
			
			if(cartItem.equals(userCartItem)) {

			Product product = cartItem.getProduct();
			String fileName = user.getId() + "_" + product.getId() +"_"+order.getId()+"_"+order.getOrderDate().getDate()+"-"+order.getOrderDate().getDay()+"_"+order.getOrderDate().getYear()+".png";
			//String FileName = websitePath + "/img/user/userproductImage/" + fileName;
			String photoName="C:\\java\\POSTERHOUSE\\src\\main\\resources\\static\\img\\user\\userproductImage\\"+fileName;

			File file = new File(photoName);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", user.getFirstName()+"_"+product.getProductName()+"_"+order.getOrderDate()+"_"+file.getName()));
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");

			ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);

			return responseEntity;
			}

		}
		return null;
		
		
	

	
}

@GetMapping("/orderDetail")
public String OrderDetails(@RequestParam("id") Long orderId,Model model) {
	Order order = orderService.findOrderById(orderId);
	List<CartItem> CartItemList = order.getCartItemList();
    User user = order.getUser();
    
    model.addAttribute("order", order);
    model.addAttribute("CartItemList", CartItemList);

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
}

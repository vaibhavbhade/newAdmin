package com.swiftdroid.posterhouse.admin.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swiftdroid.posterhouse.admin.model.Order;
import com.swiftdroid.posterhouse.admin.model.User;
import com.swiftdroid.posterhouse.admin.service.CategoryService;
import com.swiftdroid.posterhouse.admin.service.OrderService;
import com.swiftdroid.posterhouse.admin.service.ProductConfigService;
import com.swiftdroid.posterhouse.admin.service.ProductService;
import com.swiftdroid.posterhouse.admin.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductConfigService productConfigService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public String redirectHome() {
		
		System.out.println("/rredirce::"+orderService.findListOfOrder());
		
		return "redirect:/home";
		
	}
	
	@RequestMapping("/home")
	public String Home(Model model,@RequestParam(name="error",required = false) boolean error) throws Exception   {
		System.out.println("/Home::"+orderService.findListOfOrder());
		long totalOrder=orderService.findListOfOrder();
		Date today=new Date();
		List<Order> finaltodaysOrder=new ArrayList<Order>();
		
		List<Order> todaysOrder=orderService.findOrderByTodaysDate(today);
		for (Order order : todaysOrder) {
			if(order.getUserPayment() != null)
			finaltodaysOrder.add(order);
		}
		model.addAttribute("todaysOrder", finaltodaysOrder.size());
		model.addAttribute("totalOrder", totalOrder);
		long userCount=userService.userCount();
		List<User> userList =userService.findAllUser();
		model.addAttribute("userList",userList);
		model.addAttribute("userCount", userCount);
	if(error) {
		model.addAttribute("error",true);
	}
		model.addAttribute("categoryList", categoryService.findAll());
		
		return "home";
	}
	
	
    @RequestMapping("/login")
	public String login() {
    	Authentication	authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication==null || authentication instanceof AnonymousAuthenticationToken) {
		return "login";
		}
		return "redirect:/";
	}
    
    @RequestMapping("/userInfo")
   	public String userInfo(Model model) {
    	List<User> userList =userService.findAllUser();
		model.addAttribute("userList",userList);
   		return "userInfo";
   	}
    
  
    
    
    @GetMapping("/users/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<User> listUsers = userService.findAllUser();
         
        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
         
        excelExporter.export(response);    
    }  
    
    @GetMapping("/order/export/excel")
    public void OrderexportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Orders_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
       List<Order> listOrder=orderService.allOrder();
       List<Order> finalOrder=new ArrayList<Order>();
		
		for (Order order : listOrder) {
			if(order.getUserPayment() != null)
				finalOrder.add(order);
		}
       OrderExcelExporter orderExcelExporter = new OrderExcelExporter(finalOrder);
         
       orderExcelExporter.export(response);    
    } 
    
    
    
    
    
    
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/addCategory",method=RequestMethod.POST) public
	 * ProductType addCategory1(@RequestBody ProductType productType) { // Product
	 * product=productType.getProducts(); System.out.println("hit category"); return
	 * categoryService.saveCategory(productType);
	 * 
	 * 
	 * }
	 */
    
/*	
 * @ResponseBody
    @RequestMapping(value="/addCategory",method=RequestMethod.POST)
	public Product addproduct(@RequestBody Product product,@RequestParam("cat_id") Long cat_id) {


		ProductType category= categoryService.findById(cat_id);
		
		
		System.out.println("hit product");

		return productService.saveProductWithcategory(product, category);
		
		
    	
		
	}
	*/

	
}

	


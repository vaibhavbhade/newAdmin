package com.swiftdroid.posterhouse.admin.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String Home(Model model) {
		System.out.println("/Home::"+orderService.findListOfOrder());
		long totalOrder=orderService.findListOfOrder();
		Date today=new Date();
		List<Order> todaysOrder=orderService.findOrderByTodaysDate(today);
		model.addAttribute("todaysOrder", todaysOrder.size());
		model.addAttribute("totalOrder", totalOrder);
		long userCount=userService.userCount();
		List<User> userList =userService.findAllUser();
		model.addAttribute("userList",userList);
		model.addAttribute("userCount", userCount);
		
		model.addAttribute("categoryList", categoryService.findAll());
		
		return "home";
	}
	
    @RequestMapping("/login")
	public String login() {
		return "login";
	}
    
    @RequestMapping("/userInfo")
   	public String userInfo(Model model) {
    	List<User> userList =userService.findAllUser();
		model.addAttribute("userList",userList);
   		return "userInfo";
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

	


package com.swiftdroid.posterhouse.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.swiftdroid.posterhouse.admin.model.Product;
import com.swiftdroid.posterhouse.admin.model.ProductConfig;
import com.swiftdroid.posterhouse.admin.model.ProductImage;
import com.swiftdroid.posterhouse.admin.model.ProductType;
import com.swiftdroid.posterhouse.admin.model.User;
import com.swiftdroid.posterhouse.admin.service.CategoryService;
import com.swiftdroid.posterhouse.admin.service.ProductConfigService;
import com.swiftdroid.posterhouse.admin.service.ProductImageService;
import com.swiftdroid.posterhouse.admin.service.ProductService;
import com.swiftdroid.posterhouse.admin.service.UserService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	
	@Autowired
	private ProductConfigService productConfigService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductImageService productImageService;

	
@GetMapping("/addProduct")
public String addProduct(Model model) {
	
		Product product=new Product();
		
		model.addAttribute("product",product);
		
		List<ProductType> categoryList= categoryService.findAll();
		model.addAttribute("categoryList", categoryList);
		return "addproduct";
		
	}

@GetMapping("/addCategory")
public String categoryPage(Model model) {
	ProductType productType=new ProductType();
	model.addAttribute("productType", productType);
	return "categoryPage";
}

@PostMapping("/addCategory")
public String addCategoryPost(@ModelAttribute("productType") ProductType productType,Model model, Principal principal,ServletRequest request) {
	
	User user = userService.findByUsername(principal.getName());
    productType.setCretedBy(user.getFirstName());
    categoryService.saveCategory(productType);
    model.addAttribute("CategoryAdded", true);
	return "categoryPage";
	
}

@RequestMapping(method = RequestMethod.POST, value="/addProduct",consumes = {"multipart/form-data"})
public String addProductPost(Principal principal,@ModelAttribute("product") Product product,@ModelAttribute("category") ProductType productType,ServletRequest request,@RequestParam("file") MultipartFile[] imageMultipart) throws IOException {
	User user = userService.findByUsername(principal.getName());

	System.out.println(product.getOurPrice()+"*************************************************************************");
	product.setProductType(productType);
	product.setCretedBy(user.getFirstName());
	Product products=productService.save(product);

	ProductImage productImage=new ProductImage();
	productImage.setProduct(products);
	productImage.setCretedBy(user.getFirstName());
	productImage.setCretedDate(new Date());
	int count=0;
for (MultipartFile multipartFile : imageMultipart) {
	

	  try {
			byte[] bytes=multipartFile.getBytes();
			String name=products.getId()+"_"+count+".png";
			File file=new File("src/main/resources/static/image/product/"+name);
			FileOutputStream out=new FileOutputStream(file);
			BufferedOutputStream strem = new BufferedOutputStream(out);
	        strem.write(bytes);
			String path="image/product/"+name;
			if(count==0){
				productImage.setMainImageUrl(path);
			}
			if(count==1) {
				productImage.setExtramageUrl1(path);
			}
			if(count==2) {
				productImage.setExtraImageUrl2(path);
			}
			if(count==3) {
				productImage.setExtraImageUrl3(path);
			}
			count++;
			strem.close();
		} catch (Exception e) {
	e.printStackTrace();
	}
	  
  
}
productImageService.saveProductImage(productImage);

	return "addproduct";
	
	
		
}

@RequestMapping("/productList")
public String bookList(Model model) {
	
	List<Product> productList=productService.findAll();
	model.addAttribute(productList);
	return "productList";
	
}


@RequestMapping("/productConfig")
public String ProductConfigPage(Model model,@RequestParam("id") long productId) {
	
	
	Product product=productService.findOneBook(productId);
	ProductConfig productConfig=new ProductConfig();
	model.addAttribute("productConfig",productConfig);
	model.addAttribute("product", product);
	return "productConfigPage";
	
	}


@PostMapping("/addproductConfig")
public String ProductConfigPage(Principal principal,Model model,@ModelAttribute("productConfig") ProductConfig productConfig,@ModelAttribute("product") Product product) {
	User user = userService.findByUsername(principal.getName());

	productConfig.setCretedBy(user.getFirstName());
	productConfig.setProduct(product);
	productConfigService.saveProductConfig(productConfig);
	model.addAttribute("ProductConfigAdded",true);
	List<Product> productList=productService.findAll();
	model.addAttribute(productList);
	return "productList";
	
}


@GetMapping("/productConfigList")
public String ProductConfigListPage(Model model,@RequestParam("id") long productId) {
	
	System.out.println(productId);
	
	Product product =productService.findOneBook(productId);
	List<ProductConfig> productConfigList=product.getProductConfig();
	model.addAttribute("productConfigList",productConfigList);
	//model.addAttribute("product", product);
	return "productConfigListPage";
	
	}



@RequestMapping("/productInfo")
public String bookInfo(@PathParam("id")Long id,Model model) {
	Product product=productService.findOneBook(id);
	ProductType productType=product.getProductType();
	//System.out.println(productType.getId());
	
	ProductImage productImage=product.getProductImage();
	
	System.out.println("url ::"+productImage.getMainImageUrl());
	model.addAttribute("productImage", productImage);
	model.addAttribute("categoryName",productType.getProductTypeName());
	model.addAttribute("product", product);
	return "productInfo";
}

@RequestMapping("/productConfigInfo")
public String productConfigInfo(@PathParam("id")Long id,Model model) {
	
	ProductConfig productConfig=productConfigService.findProductConfigById(id);
	
	model.addAttribute("productConfig", productConfig);
	
	return "productConfigInfo";
	
	
}


@RequestMapping("/updateproduct")
public String updateProduct(@RequestParam("id")Long id,Model model) {
	
	Product product=productService.findOneBook(id);
	List<ProductType> categoryList= categoryService.findAll();

	model.addAttribute("product",product);
	model.addAttribute("categoryList", categoryList);
	
	return "updateProduct";
	
}

	
	  @RequestMapping("/updateProductConfig")
	  public String updateProductConfig(@RequestParam("id")Long id,Model model) {
	  
	  ProductConfig productConfig=productConfigService.findProductConfigById(id);
	 
	  
	  model.addAttribute("productConfig",productConfig); 

	 
	 return "updateProductConfig";
	  
	  }
	 


@PostMapping("/updateProduct")
public String updateProductByPost(@ModelAttribute("product") Product product,Principal principal,Model model,@RequestParam("file") MultipartFile[] imageMultipart) {
	User user = userService.findByUsername(principal.getName());

	product.setModifiedBy(user.getFirstName());
	Product products=productService.save(product);
	
	int count=0;
for (MultipartFile multipartFile : imageMultipart) {
	if(!multipartFile.isEmpty()) {

	  try {
			byte[] bytes=multipartFile.getBytes();
			String name=products.getId()+"_"+count+".png";
			Files.delete(Paths.get("src/main/resources/static/image/product/"+name));

			File file=new File("src/main/resources/static/image/product/"+name);
			FileOutputStream out=new FileOutputStream(file);
			BufferedOutputStream strem = new BufferedOutputStream(out);
	        strem.write(bytes);
			String path="/image/product/"+name;
			if(count==0){
		//	products.setMainImage(path);
			}
			if(count==1) {
				//products.setExtraimg1(path);
			}
			if(count==2) {
			//	products.setExtraimg2(path);
			}
			if(count==3) {
			//	products.setExtraimg3(path);
			}
			count++;
			strem.close();
		} catch (Exception e) {
	e.printStackTrace();
	}
	 
	}
	 else {
		 model.addAttribute("uploadFailmsg", true);
		  return "updateProduct";
	  }
  
}

    model.addAttribute("UpdateSucess", true);
return "redirect:/productInfo?id="+product.getId();
	
}


@PostMapping("/updateProductConfig")
public String updateProductConfigByPost(@ModelAttribute("productConfig") ProductConfig productConfig,Principal principal,Model model) {
	User user = userService.findByUsername(principal.getName());
System.out.println(productConfig.getId());
Product product=productConfig.getProduct();
System.out.println(product.getId());
	productConfig.setModifiedBy(user.getFirstName());
	productConfig.setModifiedDate(new Date());
	productConfigService.saveProductConfig(productConfig);
	model.addAttribute("UpdateSucess", true);
	
return "redirect:/productConfigInfo?id="+productConfig.getId();
	
}



	/*
	 * @PostMapping("/updateBook") public String
	 * postUpdateBook(@ModelAttribute("book")Product book) { bookService.save(book);
	 * 
	 * MultipartFile bookImage=book.getBookImage();
	 * 
	 * if(!bookImage.isEmpty()) { try { byte[] bytes=bookImage.getBytes(); String
	 * name=book.getId()+".png";
	 * 
	 * Files.delete(Paths.get("src/main/resources/static/image/book/"+name));
	 * 
	 * FileOutputStream out=new FileOutputStream(new
	 * java.io.File("src/main/resources/static/image/book/"+name));
	 * BufferedOutputStream strem = new BufferedOutputStream(out);
	 * strem.write(bytes); strem.close(); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 * 
	 * return "redirect:/book/bookInfo?id="+book.getId(); }
	 * 
	 */


	
}

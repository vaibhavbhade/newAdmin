package com.swiftdroid.posterhouse.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiftdroid.posterhouse.admin.model.Order;
import com.swiftdroid.posterhouse.admin.model.PickUp;
import com.swiftdroid.posterhouse.admin.service.OrderService;

@Controller
public class PickUPOrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/pickUpRequest")
	public String pickUpOrder(@RequestParam("id") Long Id, @RequestParam("PickUpTime") String time,
			@RequestParam("PickUpDate") String date, @RequestParam("PickUpLocation") String location) {
		Order order = orderService.findOrderById(Id);
		System.out.println("ID :: " + Id);
		System.out.println("time :: " + time);
		System.out.println("date :: " + date);
		System.out.println("location :: " + location);
		try {
			String url = "https://staging-express.delhivery.com/fm/request/new/";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			final String accessToken = "8e6534547d445c2d6f677562d07d396dd0fc974b";
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Token " + accessToken);
			PickUp pickUp = new PickUp(time, date, "ShoppingHub.com", 1);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(pickUp);

			HttpEntity<String> entity = new HttpEntity<String>(json, headers);

			ResponseEntity<PickUp> result = restTemplate.exchange(url, HttpMethod.POST, entity, PickUp.class);
			System.out.println(result.getStatusCodeValue());
			if (result.getStatusCodeValue() == 201) {
				System.out.println("in if");
				order.setOrderStatus("Dispatched");
				orderService.saveOrder(order);
				return "redirect:/viewOrderDetails?pickUpSuccess=true";
			}
			System.out.println(result.getBody());
			return "redirect:/viewOrderDetails?error=true";
		} catch (Exception e) {
			return "redirect:/viewOrderDetails?error=true";
		}

	}
@ResponseBody
	@GetMapping("/Delhivery/Packing-Slip")
	public ResponseEntity<Object> genratePackingSlip(@RequestParam("Waybill") Long Waybill,@RequestParam("orderId")Long orderId) throws JSONException, FileNotFoundException {
		String url="https://staging-express.delhivery.com/api/p/packing_slip?wbns="+Waybill;
		Order order=orderService.findOrderById(orderId);
		
		File file=new File("src/main/resources/static/image/"+order.getId()+"_slip.png");

		if (file.exists()) {
			System.out.println("file Exist");
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", order.getUser().getFirstName()
					+ "_" + order.getId()+"_slip.png"));
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");

			ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
					.contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream"))
					.body(resource);

			return responseEntity;
		}
		else {
		RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		 final String accessToken = "8e6534547d445c2d6f677562d07d396dd0fc974b";
		    headers.set("Authorization", "Token "+accessToken);
			 HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> jsonString = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);
try {
		JSONObject obj = new JSONObject(jsonString.getBody());
		
		
		JSONArray arr = obj.getJSONArray("packages");
		
		    JSONObject jsonobject = arr.getJSONObject(0);
		   
		    
				String imgBarcode = jsonobject.getString("barcode");
				
				System.out.println("imgBarcode : "+imgBarcode);
				String imageDataBytes = imgBarcode.substring(imgBarcode.indexOf(",")+1);
				byte[] byteArray = Base64.decodeBase64(imageDataBytes);
				FileOutputStream fos = new FileOutputStream(file); //change path of image according to you
				BufferedOutputStream strem = new BufferedOutputStream(fos);
				
				

		        strem.write(byteArray);
		        
		        strem.close();
		        
		        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

				HttpHeaders newHeaders = new HttpHeaders();
				newHeaders.add("Content-Disposition", String.format("attachment; filename=\"%s\"", order.getUser().getFirstName()
						+ "_" + order.getId()+"_slip.png"));
				newHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
				newHeaders.add("Pragma", "no-cache");
				newHeaders.add("Expires", "0");

				ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(newHeaders)
						.contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream"))
						.body(resource);

				return responseEntity;

}catch (Exception e) {
	// TODO: handle exception
	System.out.println(e.getMessage());
}
		}
return null;
	
		

		
		
	}

}

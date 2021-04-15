package com.swiftdroid.posterhouse.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.swiftdroid.posterhouse.admin.model.CartItem;
import com.swiftdroid.posterhouse.admin.model.Order;

public class OrderExcelExporter {

	 private XSSFWorkbook workbook;
	    private XSSFSheet sheet;
	    private List<Order> listOrders;
	     
	    public OrderExcelExporter( List<Order> listOrders) {
	        this.listOrders = listOrders;
	        workbook = new XSSFWorkbook();
	    }
	 
	 
	    private void writeHeaderLine() {
	        sheet = workbook.createSheet("Orders");
	         
	        Row row = sheet.createRow(0);
	         
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	         
	        createCell(row, 0, "Order Id", style);      
	        createCell(row, 1, "Order Date", style);       
	        createCell(row, 2, "Final Price", style);    
	        createCell(row, 3, "order status", style);
	        createCell(row, 4, "Shipping Address Name", style);
	        createCell(row, 5, "Shipping Address Street1", style);
	        createCell(row, 6, "Shipping Address Street2", style);
	        createCell(row, 7, "Shipping Address City", style);
	        createCell(row, 8, " Shipping Address State", style);
	        createCell(row, 9, " Shipping Address Zipcode", style);
	        createCell(row, 10, " phone", style);
	        
	        createCell(row, 11, " product name ", style);
	        createCell(row, 12, " product qty ", style);
	        createCell(row, 13, " product Config ", style);
	        createCell(row, 14, " product size ", style);
	        createCell(row, 15, " product Category", style);
	      
	        
	        createCell(row, 16, "amount_paid", style);
	        createCell(row, 17, " TXNID", style);
	        createCell(row, 18, "gatewayName", style);
	        createCell(row, 19, "paymentMode", style);
	        createCell(row, 20, "transectionDate", style);
	        createCell(row, 21, "status", style);

	        createCell(row, 22, "username", style);
	        createCell(row, 23, "tracking id", style);
	        createCell(row, 24, "email", style);
	        createCell(row, 25, "phone no", style);

	         
	    }
	     
	    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (value instanceof Integer) {
	            cell.setCellValue((Integer) value);
	        }else if (value instanceof Long) {
	            cell.setCellValue((Long) value);
	        }else if (value instanceof Boolean) {
	            cell.setCellValue((Boolean) value);
	        }else {
	            cell.setCellValue((String) value);
	        }
	        cell.setCellStyle(style);
	    }
	     
	    private void writeDataLines() {
	        int rowCount = 1;
	 
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);
	                 
	        for (Order order : listOrders) {
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	             
	            createCell(row, columnCount++, order.getId(), style);
	            createCell(row, columnCount++,order.getCreatedDate().toString() , style);
	            createCell(row, columnCount++, order.getFinalPrice().toString(), style);
	            createCell(row, columnCount++, order.getOrderStatus(), style);
	            createCell(row, columnCount++, order.getShippingAddress().getShippingAddressName(), style);
	            createCell(row, columnCount++, order.getShippingAddress().getShippingAddressStreet1(), style);
	            createCell(row, columnCount++, order.getShippingAddress().getShippingAddressStreet2(), style);
	            createCell(row, columnCount++, order.getShippingAddress().getShippingAddressCity(), style);
	            createCell(row, columnCount++, order.getShippingAddress().getShippingAddressState(), style);
	            createCell(row, columnCount++, order.getShippingAddress().getShippingAddressZipcode(), style);
	            createCell(row, columnCount++, order.getShippingAddress().getPhone(), style);
	            
	            List<CartItem> cartItemList = order.getCartItemList();
	            
	            
	            String product="";
	            String qty="";
	            String productConfig="";
	            String size="";
	            String cat="";
	            for (CartItem cartItem : cartItemList) {
	            	product=product+cartItem.getProduct().getProductName()+"\n";
	            	qty=qty+ cartItem.getQty()+"\n";
                   productConfig=productConfig+cartItem.getProductConfig().getProductConfigName()+"\n";
                   size=size+cartItem.getProductConfig().getSize()+"\n";
                   cat=cat+cartItem.getProduct().getProductType().getProductTypeName()+"\n";
	            }
	            
	            
	            
	            createCell(row, columnCount++, product, style);
	            createCell(row, columnCount++, qty, style);
	            createCell(row, columnCount++,productConfig, style);
	            createCell(row, columnCount++, size, style);
	            createCell(row, columnCount++,cat, style);
	            
	            createCell(row, columnCount++, order.getUserPayment().getAmount_paid(), style);
	            createCell(row, columnCount++, order.getUserPayment().getTXNID(), style);
	            createCell(row, columnCount++, order.getUserPayment().getGatewayName(), style);
	            createCell(row, columnCount++, order.getUserPayment().getPaymentMode(), style);
	            createCell(row, columnCount++, order.getUserPayment().getTransectionDate(), style);
	            createCell(row, columnCount++, order.getUserPayment().getStatus(), style);

	            
	            
	            createCell(row, columnCount++, order.getUser().getUsername(), style);
	            createCell(row, columnCount++, order.getTackingId(), style);
	            createCell(row, columnCount++, order.getUser().getEmail(), style);
	            createCell(row, columnCount++, order.getUser().getPhone(), style);
	            
	        }
	    }
	     
	    public void export(HttpServletResponse response) throws IOException {
	        writeHeaderLine();
	        writeDataLines();
	         
	        ServletOutputStream outputStream = response.getOutputStream();
	        workbook.write(outputStream);
	        workbook.close();
	         
	        outputStream.close();
	         
	    }
	}
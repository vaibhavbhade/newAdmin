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
	        createCell(row, 4, "Billing Address", style);
	        createCell(row, 5, "Shippng Address", style);
	        createCell(row, 6, "User name", style);
	        createCell(row, 7, "tracking ID", style);
	        createCell(row, 8, " User Payment Details", style);
	        createCell(row, 9, " User email", style);
	        createCell(row, 10, " User phone no", style);
	        createCell(row, 11, " CartItem ", style);
	         
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
	            createCell(row, columnCount++, order.getBillingAddress().toString(), style);
	            createCell(row, columnCount++, order.getShippingAddress().toString(), style);
	            createCell(row, columnCount++, order.getUser().getUsername(), style);
	            createCell(row, columnCount++, order.getTackingId(), style);
	            createCell(row, columnCount++, order.getUserPayment().toString(), style);
	            createCell(row, columnCount++, order.getUser().getEmail(), style);
	            createCell(row, columnCount++, order.getUser().getPhone(), style);
	            createCell(row, columnCount++, order.getCartItemList().toString(), style);
	            
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
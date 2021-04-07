package com.swiftdroid.posterhouse.admin.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity(name="Order")
@Table(name="user_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date orderDate;
	private Date shippingDate;
	private String shippingMethod;
	private String orderStatus;
	private BigDecimal orderTotal;
	private LocalDate estimateDate;
	private BigDecimal finalPrice;
	@Temporal(TemporalType.DATE)
    private Date createdDate;
	
	private boolean delhiveryStatus;
	
	private Long tackingId;
	private String downloadpath;
	@OneToMany(mappedBy = "order", cascade=CascadeType.ALL )
	private List<CartItem> cartItemList;
	
	
	
	  @OneToOne
	  (cascade=CascadeType.ALL)
	  private ShippingAddress shippingAddress;
	 
	
	
	  @OneToOne
	  (cascade=CascadeType.ALL) 
	  private BillingAddress billingAddress;
	 
	
	
	  @OneToOne(cascade = CascadeType.ALL)
		private UserPayment userPayment;
	  
	@ManyToOne
	private User user;


	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public Date getShippingDate() {
		return shippingDate;
	}


	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}


	public String getShippingMethod() {
		return shippingMethod;
	}


	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public BigDecimal getOrderTotal() {
		return orderTotal;
	}


	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}


	public List<CartItem> getCartItemList() {
		return cartItemList;
	}


	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public BillingAddress getBillingAddress() {
		return billingAddress;
	}


	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}


	public LocalDate getEstimateDate() {
		return estimateDate;
	}


	public void setEstimateDate(LocalDate estimateDate) {
		this.estimateDate = estimateDate;
	}


	public BigDecimal getFinalPrice() {
		return finalPrice;
	}


	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}





	public UserPayment getUserPayment() {
		return userPayment;
	}


	public void setUserPayment(UserPayment userPayment) {
		this.userPayment = userPayment;
	}


	public Long getTackingId() {
		return tackingId;
	}


	public void setTackingId(Long tackingId) {
		this.tackingId = tackingId;
	}
	
	
	public boolean isDelhiveryStatus() {
		return delhiveryStatus;
	}

	public void setDelhiveryStatus(boolean delhiveryStatus) {
		this.delhiveryStatus = delhiveryStatus;
	}


	public String getDownloadpath() {
		return downloadpath;
	}


	public void setDownloadpath(String downloadpath) {
		this.downloadpath = downloadpath;
	}


	
	

}

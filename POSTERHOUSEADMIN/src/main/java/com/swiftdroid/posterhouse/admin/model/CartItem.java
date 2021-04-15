package com.swiftdroid.posterhouse.admin.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int qty;
	private BigDecimal subtotal;
	private String size;
	
	
	
	@OneToOne
	private Product Product;
	
	@OneToOne
	private ProductConfig productConfig;
	
	@OneToMany(mappedBy = "cartItem")
	@JsonIgnore
	private List<ProductToCartItem> ProductToCartItemList;
	
	@OneToMany(mappedBy = "cartItem")
	private List<CartItemToImage> cartItemToImage; 
	
	@ManyToOne
	@JoinColumn(name="shopping_cart_id")
	private ShoppingCart shoppingCart;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;

	
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public ProductConfig getProductConfig() {
		return productConfig;
	}



	public void setProductConfig(ProductConfig productConfig) {
		this.productConfig = productConfig;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Product getProduct() {
		return Product;
	}

	public void setProduct(Product product) {
		Product = product;
	}

	public List<ProductToCartItem> getProductToCartItemList() {
		return ProductToCartItemList;
	}

	public void setProductToCartItemList(List<ProductToCartItem> productToCartItemList) {
		ProductToCartItemList = productToCartItemList;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}



	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}



	@Override
	public String toString() {
		return "CartItem [id=" + id + ", qty=" + qty + ", subtotal=" + subtotal + ", size=" + size + ", Product="
				+ Product + ", productConfig=" + productConfig + ", ProductToCartItemList=" + ProductToCartItemList
				+ ", shoppingCart=" + shoppingCart + ", order=" + order + "]";
	}



	public List<CartItemToImage> getCartItemToImage() {
		return cartItemToImage;
	}



	public void setCartItemToImage(List<CartItemToImage> cartItemToImage) {
		cartItemToImage = cartItemToImage;
	}
	
	

	
}


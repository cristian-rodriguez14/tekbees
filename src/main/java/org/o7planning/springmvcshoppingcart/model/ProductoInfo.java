package org.o7planning.springmvcshoppingcart.model;

import org.o7planning.springmvcshoppingcart.entity.Producto;

public class ProductoInfo {
	
	private String productoCodbarras;
	private String productoNombre;
	private String productoimagen;
	private String productoDescripcion;
	private int productoCategoria;
	private double productoPrecio;
	private int productoStock;
	
	
	public ProductoInfo() {
	
	}
	
	public ProductoInfo(Producto producto) {
		this.productoCodbarras = producto.getProductoCodbarras();
		this.productoNombre = producto.getProductoNombre();
		this.productoimagen = producto.getProductoimagen();
		this.productoDescripcion = producto.getProductoDescripcion();
		this.productoCategoria = producto.getProductoCategoria();
		this.productoPrecio = producto.getProductoPrecio();
		this.productoStock = producto.getProductoStock();
	}


	public ProductoInfo(String productoCodbarras, String productoNombre, String productoimagen,
			String productoDescripcion, int productoCategoria, double productoPrecio, int productoStock) {
		this.productoCodbarras = productoCodbarras;
		this.productoNombre = productoNombre;
		this.productoimagen = productoimagen;
		this.productoDescripcion = productoDescripcion;
		this.productoCategoria = productoCategoria;
		this.productoPrecio = productoPrecio;
		this.productoStock = productoStock;
	}

	public String getProductoCodbarras() {
		return productoCodbarras;
	}

	public void setProductoCodbarras(String productoCodbarras) {
		this.productoCodbarras = productoCodbarras;
	}

	public String getProductoNombre() {
		return productoNombre;
	}

	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}

	public String getProductoimagen() {
		return productoimagen;
	}

	public void setProductoimagen(String productoimagen) {
		this.productoimagen = productoimagen;
	}

	public String getProductoDescripcion() {
		return productoDescripcion;
	}

	public void setProductoDescripcion(String productoDescripcion) {
		this.productoDescripcion = productoDescripcion;
	}

	public int getProductoCategoria() {
		return productoCategoria;
	}

	public void setProductoCategoria(int productoCategoria) {
		this.productoCategoria = productoCategoria;
	}

	public double getProductoPrecio() {
		return productoPrecio;
	}

	public void setProductoPrecio(double productoPrecio) {
		this.productoPrecio = productoPrecio;
	}

	public int getProductoStock() {
		return productoStock;
	}

	public void setProductoStock(int productoStock) {
		this.productoStock = productoStock;
	}
	
	

}

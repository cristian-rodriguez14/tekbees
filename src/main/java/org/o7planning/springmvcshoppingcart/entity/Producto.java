package org.o7planning.springmvcshoppingcart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 7550745928843183535L;
	
	private String productoCodbarras;
	private String productoNombre;
	private String productoimagen;
	private String productoDescripcion;
	private int productoCategoria;
	private double productoPrecio;
	private int productoStock;
	
	@Id
    @Column(name = "productoCodbarras", length = 45, nullable = false)
	public String getProductoCodbarras() {
		return productoCodbarras;
	}
	
	public void setProductoCodbarras(String productoCodbarras) {
		this.productoCodbarras = productoCodbarras;
	}
	
	@Column(name = "productoNombre", length = 45, nullable = false)
	public String getProductoNombre() {
		return productoNombre;
	}
	
	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}
	
	@Column(name = "productoimagen", length = 45, nullable = false)
	public String getProductoimagen() {
		return productoimagen;
	}
	
	public void setProductoimagen(String productoimagen) {
		this.productoimagen = productoimagen;
	}
	
	@Column(name = "productoDescripcion", length = 200, nullable = false)
	public String getProductoDescripcion() {
		return productoDescripcion;
	}
	
	public void setProductoDescripcion(String productoDescripcion) {
		this.productoDescripcion = productoDescripcion;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoriaId", nullable = false, //
    foreignKey = @ForeignKey(name = "FK_Producto_Categoria") )
	public int getProductoCategoria() {
		return productoCategoria;
	}
	
	public void setProductoCategoria(int productoCategoria) {
		this.productoCategoria = productoCategoria;
	}
	
	@Column(name = "productoPrecio", nullable = false)
	public double getProductoPrecio() {
		return productoPrecio;
	}
	
	public void setProductoPrecio(double productoPrecio) {
		this.productoPrecio = productoPrecio;
	}
	
	@Column(name = "productoStock", nullable = false)
	public int getProductoStock() {
		return productoStock;
	}
	
	public void setProductoStock(int productoStock) {
		this.productoStock = productoStock;
	}

	@Override
	public String toString() {
		return "Producto [productoCodbarras=" + productoCodbarras + ", productoNombre=" + productoNombre
				+ ", productoimagen=" + productoimagen + ", productoDescripcion=" + productoDescripcion
				+ ", productoCategoria=" + productoCategoria + ", productoPrecio=" + productoPrecio + ", productoStock="
				+ productoStock + "]";
	}
	
	

}

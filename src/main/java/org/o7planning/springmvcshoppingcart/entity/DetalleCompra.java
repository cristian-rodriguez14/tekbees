package org.o7planning.springmvcshoppingcart.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalleCompra")
public class DetalleCompra implements Serializable {

	private static final long serialVersionUID = -1000119078147252957L;
	
	
	private UUID detalleCompraId;
	private Producto ProductoId;
	private String ClienteId;
	private int detalleCompraCantidad;
	private double detalleCompraPrecio;
	
	@Id
    @Column(name = "detalleCompraId", nullable = false)
	public UUID getDetalleCompraId() {
		return detalleCompraId;
	}
	
	public void setDetalleCompraId(UUID uuid) {
		this.detalleCompraId = uuid;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productoCodbarras", nullable = false, //
    foreignKey = @ForeignKey(name = "FK_DetalleCompra_Producto") )
	public Producto getProductoId() {
		return ProductoId;
	}
	
	public void setProductoId(Producto producto) {
		ProductoId = producto;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteId", nullable = false, //
    foreignKey = @ForeignKey(name = "FK_DetalleCompra_Cliente") )
	public String getClienteId() {
		return ClienteId;
	}
	
	public void setClienteId(String clienteId) {
		ClienteId = clienteId;
	}
	
	@Column(name = "detalleCompraCantidad", nullable = false)
	public int getDetalleCompraCantidad() {
		return detalleCompraCantidad;
	}
	
	public void setDetalleCompraCantidad(int detalleCompraCantidad) {
		this.detalleCompraCantidad = detalleCompraCantidad;
	}
	
	@Column(name = "detalleCompraPrecio", nullable = false)
	public double getDetalleCompraPrecio() {
		return detalleCompraPrecio;
	}
	
	public void setDetalleCompraPrecio(double detalleCompraPrecio) {
		this.detalleCompraPrecio = detalleCompraPrecio;
	}
	
	
}

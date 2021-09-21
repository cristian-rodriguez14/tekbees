package org.o7planning.springmvcshoppingcart.model;

import java.util.List;

public class DetalleCompraInfo {

	private int detalleCompraId;
	private String ProductoId;
	private String ClienteId;
	private int detalleCompraCantidad;
	private double detalleCompraPrecio;
	
	private List<DetalleCompraInfo> detalle;
	
	
		public DetalleCompraInfo() {
		
	}


	public DetalleCompraInfo(int detalleCompraId, String productoId, String clienteId, int detalleCompraCantidad,
			double detalleCompraPrecio) {
		this.detalleCompraId = detalleCompraId;
		ProductoId = productoId;
		ClienteId = clienteId;
		this.detalleCompraCantidad = detalleCompraCantidad;
		this.detalleCompraPrecio = detalleCompraPrecio;
	}


	public int getDetalleCompraId() {
		return detalleCompraId;
	}


	public void setDetalleCompraId(int detalleCompraId) {
		this.detalleCompraId = detalleCompraId;
	}


	public String getProductoId() {
		return ProductoId;
	}


	public void setProductoId(String productoId) {
		ProductoId = productoId;
	}


	public String getClienteId() {
		return ClienteId;
	}


	public void setClienteId(String clienteId) {
		ClienteId = clienteId;
	}


	public int getDetalleCompraCantidad() {
		return detalleCompraCantidad;
	}


	public void setDetalleCompraCantidad(int detalleCompraCantidad) {
		this.detalleCompraCantidad = detalleCompraCantidad;
	}


	public double getDetalleCompraPrecio() {
		return detalleCompraPrecio;
	}


	public void setDetalleCompraPrecio(double detalleCompraPrecio) {
		this.detalleCompraPrecio = detalleCompraPrecio;
	}
	
	
	public List<DetalleCompraInfo> getDetalle() {
		return detalle;
	}


	public void setDetalle(List<DetalleCompraInfo> detalle) {
		this.detalle = detalle;
	}
	
	
}

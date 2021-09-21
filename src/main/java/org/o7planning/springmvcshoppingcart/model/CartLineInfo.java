package org.o7planning.springmvcshoppingcart.model;

public class CartLineInfo {
	
	private ProductoInfo ProductoInfo;
    private int cantidad;

    public CartLineInfo() {
        this.cantidad = 0;
    }

    public ProductoInfo getProductoInfo() {
        return ProductoInfo;
    }

    public void setProductoInfo(ProductoInfo ProductoInfo) {
        this.ProductoInfo = ProductoInfo;
    }

    public int getcantidad() {
        return cantidad;
    }

    public void setcantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getAmount() {
        return this.ProductoInfo.getProductoPrecio() * this.cantidad;
    }

}

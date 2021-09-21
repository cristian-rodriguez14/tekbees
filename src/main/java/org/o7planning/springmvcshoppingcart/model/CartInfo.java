package org.o7planning.springmvcshoppingcart.model;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {
	
	private int orderNum;
	
	private ClienteInfo clienteInfo;
	
	private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();

	public CartInfo() {
		
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public ClienteInfo getClienteInfo() {
		return clienteInfo;
	}

	public List<CartLineInfo> getCartLines() {
		return cartLines;
	}
	
	private CartLineInfo findLineByCode(String code) {
        for (CartLineInfo line : this.cartLines) {
            if (line.getProductoInfo().getProductoCodbarras().equals(code)) {
                return line;
            }
        }
        return null;
    }
	
	public void addProduct(ProductoInfo ProductoInfo, int cantidad) {
        CartLineInfo line = this.findLineByCode(ProductoInfo.getProductoCodbarras());

        if (line == null) {
            line = new CartLineInfo();
            line.setcantidad(0);
            line.setProductoInfo(ProductoInfo);
            this.cartLines.add(line);
        }
        int newcantidad = line.getcantidad() + cantidad;
        if (newcantidad <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setcantidad(newcantidad);
        }
    }

    public void validate() {

    }

    public void updateProduct(String code, int cantidad) {
        CartLineInfo line = this.findLineByCode(code);

        if (line != null) {
            if (cantidad <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setcantidad(cantidad);
            }
        }
    }

    public void removeProduct(ProductoInfo ProductoInfo) {
        CartLineInfo line = this.findLineByCode(ProductoInfo.getProductoCodbarras());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }

    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }

    public int getcantidadTotal() {
        int cantidad = 0;
        for (CartLineInfo line : this.cartLines) {
            cantidad += line.getcantidad();
        }
        return cantidad;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo line : this.cartLines) {
            total += line.getAmount();
        }
        return total;
    }

    public void updatecantidad(CartInfo cartForm) {
        if (cartForm != null) {
            List<CartLineInfo> lines = cartForm.getCartLines();
            for (CartLineInfo line : lines) {
                this.updateProduct(line.getProductoInfo().getProductoCodbarras(), line.getcantidad());
            }
        }

    }

}

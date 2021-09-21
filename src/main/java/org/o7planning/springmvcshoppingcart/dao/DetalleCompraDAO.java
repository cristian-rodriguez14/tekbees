package org.o7planning.springmvcshoppingcart.dao;

import java.util.List;

import org.o7planning.springmvcshoppingcart.model.CartInfo;
import org.o7planning.springmvcshoppingcart.model.DetalleCompraInfo;
import org.o7planning.springmvcshoppingcart.model.PaginationResult;

public interface DetalleCompraDAO {

	public void guardarDetalle(CartInfo cartInfo);

    public PaginationResult<DetalleCompraInfo> listaDetalleCompraInfo(int page,
            int maxResult, int maxNavigationPage);
   
    public List<DetalleCompraInfo> listaDetalleCompraInfos(String orderId);
}

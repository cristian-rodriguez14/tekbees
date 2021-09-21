package org.o7planning.springmvcshoppingcart.dao;

import org.o7planning.springmvcshoppingcart.entity.Producto;
import org.o7planning.springmvcshoppingcart.entity.Categoria;
import org.o7planning.springmvcshoppingcart.model.PaginationResult;
import org.o7planning.springmvcshoppingcart.model.ProductoInfo;

public interface ProductoDAO {
	public Producto findProducto(String productoCodbarras);

	public Categoria findCategoria(String categoriaNombre);
	
	public ProductoInfo findProductoInfo(String productoCodbarras);

	public PaginationResult<ProductoInfo> queryProductos(int page, int maxResult, int maxNavigationPage);

	public PaginationResult<ProductoInfo> queryProductos(int page, int maxResult, int maxNavigationPage, String likeName);

	public void save(ProductoInfo productoInfo);

}

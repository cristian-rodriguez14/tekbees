package org.o7planning.springmvcshoppingcart.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.o7planning.springmvcshoppingcart.dao.ProductoDAO;
import org.o7planning.springmvcshoppingcart.entity.Categoria;
import org.o7planning.springmvcshoppingcart.entity.Producto;
import org.o7planning.springmvcshoppingcart.model.PaginationResult;
import org.o7planning.springmvcshoppingcart.model.ProductoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ProductoDAOImpl implements ProductoDAO {
	
	 @Autowired
	    private SessionFactory sessionFactory;

	    @Override
	    public Producto findProducto(String productoCodbarras) {
	        Session session = sessionFactory.getCurrentSession();
	        Criteria crit = session.createCriteria(Producto.class);
	        crit.add(Restrictions.eq("productoCodbarras", productoCodbarras));
	        return (Producto) crit.uniqueResult();
	    }
	    
	    public Categoria findCategoria(String categoriaNombre) {
	        Session session = sessionFactory.getCurrentSession();
	        Criteria crit = session.createCriteria(Categoria.class);
	        crit.add(Restrictions.eq("categoriaNombre", categoriaNombre));
	        return (Categoria) crit.uniqueResult();
	    }
	    
	    @Override
	    public ProductoInfo findProductoInfo(String productoCodbarras) {
	        Producto Producto = this.findProducto(productoCodbarras);
	        if (Producto == null) {
	            return null;
	        }
	        return new ProductoInfo(Producto.getProductoCodbarras(), Producto.getProductoNombre(), Producto.getProductoimagen(), Producto.getProductoDescripcion(), Producto.getProductoCategoria(), Producto.getProductoPrecio(), Producto.getProductoStock());
	    }

	    @Override
	    public void save(ProductoInfo ProductoInfo) {
	        String productoCodbarras = ProductoInfo.getProductoCodbarras();

	        Producto Producto = null;

	        boolean isNew = false;
	        if (productoCodbarras != null) {
	            Producto = this.findProducto(productoCodbarras);
	        }
	        if (Producto == null) {
	            isNew = true;
	            Producto = new Producto();
	        }
	        Producto.setProductoCodbarras(productoCodbarras);	        
	        Producto.setProductoNombre(ProductoInfo.getProductoNombre());
	        Producto.setProductoimagen(ProductoInfo.getProductoimagen());
	        Producto.setProductoDescripcion(ProductoInfo.getProductoDescripcion());
	        Producto.setProductoCategoria(ProductoInfo.getProductoCategoria());
	        Producto.setProductoPrecio(ProductoInfo.getProductoPrecio());
	        Producto.setProductoStock(ProductoInfo.getProductoStock());

	        if (isNew) {
	            this.sessionFactory.getCurrentSession().persist(Producto);
	        }
	        this.sessionFactory.getCurrentSession().flush();
	    }

	    @Override
	    public PaginationResult<ProductoInfo> queryProductos(int page, int maxResult, int maxNavigationPage,
	            String likeName) {
	        String sql = "Select new " + ProductoInfo.class.getName() //
	                + "(p.productoCodbarras, p.name, p.price) " + " from "//
	                + Producto.class.getName() + " p ";
	        if (likeName != null && likeName.length() > 0) {
	            sql += " Where lower(p.name) like :likeName ";
	        }
	        sql += " order by p.createDate desc ";
	        //
	        Session session = sessionFactory.getCurrentSession();

	        Query query = session.createQuery(sql);
	        if (likeName != null && likeName.length() > 0) {
	            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
	        }
	        return new PaginationResult<ProductoInfo>(query, page, maxResult, maxNavigationPage);
	    }

	    @Override
	    public PaginationResult<ProductoInfo> queryProductos(int page, int maxResult, int maxNavigationPage) {
	        return queryProductos(page, maxResult, maxNavigationPage, null);
	    }

}

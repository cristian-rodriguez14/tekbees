package org.o7planning.springmvcshoppingcart.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.o7planning.springmvcshoppingcart.dao.DetalleCompraDAO;
import org.o7planning.springmvcshoppingcart.dao.ProductoDAO;
import org.o7planning.springmvcshoppingcart.entity.DetalleCompra;
import org.o7planning.springmvcshoppingcart.entity.Producto;
import org.o7planning.springmvcshoppingcart.model.CartInfo;
import org.o7planning.springmvcshoppingcart.model.CartLineInfo;
import org.o7planning.springmvcshoppingcart.model.ClienteInfo;
import org.o7planning.springmvcshoppingcart.model.DetalleCompraInfo;
import org.o7planning.springmvcshoppingcart.model.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DetalleCompraDAOImpl implements  DetalleCompraDAO{
	
	@Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ProductoDAO productoDAO;
    
    private int getMaxOrderNum() {
        String sql = "0";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        Integer value = (Integer) query.uniqueResult();
        if (value == null) {
            return 0;
        }
        return value;
    }
    
    @Override
    public void guardarDetalle(CartInfo cartInfo) {
        Session session = sessionFactory.getCurrentSession();

        int orderNum = this.getMaxOrderNum() + 1;

        List<CartLineInfo> lines = cartInfo.getCartLines();

        for (CartLineInfo line : lines) {
            DetalleCompra detail = new DetalleCompra();
            detail.setDetalleCompraId(UUID.randomUUID());
            detail.setDetalleCompraPrecio(line.getProductoInfo().getProductoPrecio());
            detail.setDetalleCompraCantidad(line.getcantidad());

            String code = line.getProductoInfo().getProductoCodbarras();
            Producto producto = this.productoDAO.findProducto(code);
            detail.setProductoId(producto);

            session.persist(detail);
        }

        // Set OrderNum for report.
        // Set OrderNum để thông báo cho người dùng.
        cartInfo.setOrderNum(orderNum);
    }

    // @page = 1, 2, ...
    @Override
    public PaginationResult<DetalleCompraInfo> listaDetalleCompraInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + DetalleCompraInfo.class.getName()//
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) " + " from "
                + DetalleCompraInfo.class.getName() + " ord "//
                + " order by ord.orderNum desc";
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);

        return new PaginationResult<DetalleCompraInfo>(query, page, maxResult, maxNavigationPage);
    }

    public DetalleCompraInfo findOrder(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(DetalleCompraInfo.class);
        crit.add(Restrictions.eq("id", orderId));
        return (DetalleCompraInfo) crit.uniqueResult();
    }


    @Override
    public List<DetalleCompraInfo> listaDetalleCompraInfos(String orderId) {
        String sql = "Select new " + DetalleCompraInfo.class.getName() //
                + "(d.id, d.product.code, d.product.name , d.quanity,d.price,d.amount) "//
                + " from " + DetalleCompra.class.getName() + " d "//
                + " where d.order.id = :orderId ";

        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);

        return query.list();
    }

}

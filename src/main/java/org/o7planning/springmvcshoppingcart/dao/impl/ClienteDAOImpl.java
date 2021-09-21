package org.o7planning.springmvcshoppingcart.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.o7planning.springmvcshoppingcart.dao.ClienteDAO;
import org.o7planning.springmvcshoppingcart.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ClienteDAOImpl implements ClienteDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

    public Cliente findCliente(String clienteNombres ) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Cliente.class);
        crit.add(Restrictions.eq("clienteNombres", clienteNombres));
        return (Cliente) crit.uniqueResult();
    }

}

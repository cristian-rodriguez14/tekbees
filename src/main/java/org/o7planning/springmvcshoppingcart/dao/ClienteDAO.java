package org.o7planning.springmvcshoppingcart.dao;

import org.o7planning.springmvcshoppingcart.entity.Cliente;

public interface ClienteDAO {
	
	public Cliente findCliente(String clienteNombres);

}

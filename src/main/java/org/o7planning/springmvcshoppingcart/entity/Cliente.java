package org.o7planning.springmvcshoppingcart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -2054386655979281969L;
	
	private String clienteTipo;
	private String clienteId;
	private String clienteNombres;
	private String clienteApellidos;
	private String clienteDireccion;
	private String clienteCiudad;
	private String clienteEmail;
	
	@Column(name = "clienteTipo", length = 50, nullable = false)
	public String getClienteTipo() {
		return clienteTipo;
	}	
	
	public void setClienteTipo(String clienteTipo) {
		this.clienteTipo = clienteTipo;
	}
	
	@Id
	@Column(name = "clienteId", length = 45, nullable = false)
	public String getClienteId() {
		return clienteId;
	}
		
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	
	@Column(name = "clienteNombres", length = 45, nullable = false)
	public String getClienteNombres() {
		return clienteNombres;
	}
	
	public void setClienteNombres(String clienteNombres) {
		this.clienteNombres = clienteNombres;
	}
	
	@Column(name = "clienteApellidos", length = 45, nullable = false)
	public String getClienteApellidos() {
		return clienteApellidos;
	}
	
	public void setClienteApellidos(String clienteApellidos) {
		this.clienteApellidos = clienteApellidos;
	}
	
	@Column(name = "clienteDireccion", length = 60, nullable = false)
	public String getClienteDireccion() {
		return clienteDireccion;
	}
	
	public void setClienteDireccion(String clienteDireccion) {
		this.clienteDireccion = clienteDireccion;
	}
	
	@Column(name = "clienteCiudad", length = 45, nullable = false)
	public String getClienteCiudad() {
		return clienteCiudad;
	}
	
	public void setClienteCiudad(String clienteCiudad) {
		this.clienteCiudad = clienteCiudad;
	}
	
	@Column(name = "clienteEmail", length = 60, nullable = false)
	public String getClienteEmail() {
		return clienteEmail;
	}
	
	public void setClienteEmail(String clienteEmail) {
		this.clienteEmail = clienteEmail;
	}

	@Override
	public String toString() {
		return "Cliente [clienteTipo=" + clienteTipo + ", clienteId=" + clienteId + ", clienteNombres=" + clienteNombres
				+ ", clienteApellidos=" + clienteApellidos + ", clienteDireccion=" + clienteDireccion
				+ ", clienteCiudad=" + clienteCiudad + ", clienteEmail=" + clienteEmail + "]";
	}
	
	
}

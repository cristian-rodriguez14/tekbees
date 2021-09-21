package org.o7planning.springmvcshoppingcart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

	private static final long serialVersionUID = -2576670215015463100L;
	
	private int categoriaId;
	private String categoriaNombre;
	private String categoriaDescripcion;
	
	@Id
	@Column(name = "categoriaId", nullable = false)
	public int getCategoriaId() {
		return categoriaId;
	}
	
	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	@Column(name = "categoriaNombre", length = 45, nullable = false)
	public String getCategoriaNombre() {
		return categoriaNombre;
	}
	
	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}
	
	@Column(name = "categoriaDescripcion", length = 200, nullable = false)
	public String getCategoriaDescripcion() {
		return categoriaDescripcion;
	}
	
	public void setCategoriaDescripcion(String categoriaDescripcion) {
		this.categoriaDescripcion = categoriaDescripcion;
	}

	@Override
	public String toString() {
		return "Categoria [categoriaId=" + categoriaId + ", categoriaNombre=" + categoriaNombre
				+ ", categoriaDescripcion=" + categoriaDescripcion + "]";
	}
	
	
	
}

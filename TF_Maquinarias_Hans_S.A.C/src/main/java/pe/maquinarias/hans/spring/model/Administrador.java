package pe.maquinarias.hans.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Administrador")
public class Administrador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAdministrador;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameAdministrador",nullable=false, length=60)
	private String nameAdministrador;
	
	@ManyToOne
	@JoinColumn(name="idCargo",nullable=false)
	private Cargo cargo;

	public Administrador() {
		super();
	}

	public Administrador(int idAdministrador,String nameAdministrador,Cargo cargo) {
		super();
		this.idAdministrador = idAdministrador;
		this.nameAdministrador = nameAdministrador;
		this.cargo = cargo;
	}

	public int getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getNameAdministrador() {
		return nameAdministrador;
	}

	public void setNameAdministrador(String nameAdministrador) {
		this.nameAdministrador = nameAdministrador;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}
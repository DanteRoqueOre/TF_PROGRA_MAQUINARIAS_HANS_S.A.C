package pe.maquinarias.hans.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Cargo")
public class Cargo implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCargo;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameCargo",nullable=false,length=20)
	private String nameCargo;
	

	public Cargo() {
		super();
	}

	public Cargo(int idCargo, String nameCargo) {
		super();
		this.idCargo = idCargo;
		this.nameCargo = nameCargo;
	}

	public int getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}

	public String getNameCargo() {
		return nameCargo;
	}

	public void setNameCargo(String nameCargo) {
		this.nameCargo = nameCargo;
	}	
	
}
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
@Table(name="Tipo")
public class Tipo implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTipo;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameTipo",nullable=false,length=20)
	private String nameTipo;
	
	public Tipo() {
		super();
	}

	public Tipo(int idTipo, String nameTipo) {
		super();
		this.idTipo = idTipo;
		this.nameTipo = nameTipo;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getNameTipo() {
		return nameTipo;
	}

	public void setNameTipo(String nameTipo) {
		this.nameTipo = nameTipo;
	}	
	
}
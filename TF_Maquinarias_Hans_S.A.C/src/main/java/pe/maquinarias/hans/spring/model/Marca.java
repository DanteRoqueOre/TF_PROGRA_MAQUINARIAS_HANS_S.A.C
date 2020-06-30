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
@Table(name="Marca")
public class Marca implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMarca;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameMarca",nullable=false,length=20)
	private String nameMarca;
	

	public Marca() {
		super();
	}

	public Marca(int idMarca, String nameMarca) {
		super();
		this.idMarca = idMarca;
		this.nameMarca = nameMarca;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getNameMarca() {
		return nameMarca;
	}

	public void setNameMarca(String nameMarca) {
		this.nameMarca = nameMarca;
	}	
	
}
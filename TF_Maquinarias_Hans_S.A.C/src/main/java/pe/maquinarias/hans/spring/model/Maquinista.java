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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Maquinista")
public class Maquinista implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMaquinista;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameMaquinista",nullable=false, length=30)
	private String nameMaquinista;
	
	
	@Min(value=20, message="Debe ser igual o mayor que 20")  
    @Max(value=65, message="Debe ser igual o menor que 65") 
	private int edadMaquinista;
		
	
	@Min(value=0, message="Debe ser igual o mayor que 0")  
    @Max(value=45, message="Debe ser igual o menor que 45") 
	private int anioMaquinista;
	
	@ManyToOne
	@JoinColumn(name="idEspecialidad",nullable=false)
	private Especialidad especialidad;
	
	public Maquinista() {
		super();
	}

	public Maquinista(int idMaquinista, Especialidad especialidad,int edadMaquinista,String nameMaquinista,int anioMaquinista) {
		super();
		this.idMaquinista = idMaquinista;
		this.especialidad = especialidad;
		this.edadMaquinista = edadMaquinista;
		this.nameMaquinista = nameMaquinista;
		this.anioMaquinista = anioMaquinista;
	}

	public int getIdMaquinista() {
		return idMaquinista;
	}

	public void setIdMaquinista(int idMaquinista) {
		this.idMaquinista = idMaquinista;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public int getEdadMaquinista() {
		return edadMaquinista;
	}

	public void setEdadMaquinista(int edadMaquinista) {
		this.edadMaquinista = edadMaquinista;
	}

	public String getNameMaquinista() {
		return nameMaquinista;
	}

	public void setNameMaquinista(String nameMaquinista) {
		this.nameMaquinista = nameMaquinista;
	}

	public int getAnioMaquinista() {
		return anioMaquinista;
	}

	public void setAnioMaquinista(int anioMaquinista) {
		this.anioMaquinista = anioMaquinista;
	}
}
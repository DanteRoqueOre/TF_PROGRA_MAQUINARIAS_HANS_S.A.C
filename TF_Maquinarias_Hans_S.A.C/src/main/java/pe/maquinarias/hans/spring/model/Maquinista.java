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
@Table(name="Maquinista")
public class Maquinista implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMaquinista;
	
	@ManyToOne
	@JoinColumn(name="idEspecialidad",nullable=false)
	private Especialidad especialidad;
		
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="edadMaquinista",nullable=false, length=60)
	private String edadMaquinista;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameMaquinista",nullable=false, length=60)
	private String nameMaquinista;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="anioMaquinista",nullable=false, length=60)
	private String anioMaquinista;

	public Maquinista() {
		super();
	}

	public Maquinista(int idMaquinista, Especialidad especialidad,String edadMaquinista,String nameMaquinista,String anioMaquinista) {
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

	public String getEdadMaquinista() {
		return edadMaquinista;
	}

	public void setEdadMaquinista(String edadMaquinista) {
		this.edadMaquinista = edadMaquinista;
	}

	public String getNameMaquinista() {
		return nameMaquinista;
	}

	public void setNameMaquinista(String nameMaquinista) {
		this.nameMaquinista = nameMaquinista;
	}

	public String getAnioMaquinista() {
		return anioMaquinista;
	}

	public void setAnioMaquinista(String anioMaquinista) {
		this.anioMaquinista = anioMaquinista;
	}
}
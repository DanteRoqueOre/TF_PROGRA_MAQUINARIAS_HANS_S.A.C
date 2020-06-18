package pe.maquinarias.hans.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Alquiler")
public class Alquiler implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlquiler;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameAlquiler",nullable=false, length=60)
	private String nameAlquiler;
	
	@NotNull
	@Past(message="No puedes seleccionar un dia que NO existe")
	@Temporal(TemporalType.DATE)
	@Column(name="dateAlquiler")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateAlquiler;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="qHoras",nullable=false, length=60)
	private String qHoras;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="telefono",nullable=false, length=60)
	private String telefono;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="direccion",nullable=false, length=60)
	private String direccion;
	
	
	
	@ManyToOne
	@JoinColumn(name="idMaquinaria",nullable=false)
	private Maquinaria maquinaria;
	
	@ManyToOne
	@JoinColumn(name="dniUsuario",nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idMaquinista",nullable=false)
	private Maquinista maquinista;

	public Alquiler() {
		super();
	}

	public Alquiler(int idAlquiler,String nameAlquiler,Date dateAlquiler,String qHoras, String telefono,
			String direccion,Maquinaria maquinaria, Usuario usuario, Maquinista maquinista) {
		super();
		this.idAlquiler = idAlquiler;
		this.nameAlquiler = nameAlquiler;
		this.dateAlquiler = dateAlquiler;
		this.qHoras = qHoras;
		this.telefono = telefono;
		this.direccion = direccion;
		this.maquinaria = maquinaria;
		this.usuario = usuario;
		this.maquinista = maquinista;
	}

	public int getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public String getNameAlquiler() {
		return nameAlquiler;
	}

	public void setNameAlquiler(String nameAlquiler) {
		this.nameAlquiler = nameAlquiler;
	}

	public Date getDateAlquiler() {
		return dateAlquiler;
	}

	public void setDateAlquiler(Date dateAlquiler) {
		this.dateAlquiler = dateAlquiler;
	}

	public String getqHoras() {
		return qHoras;
	}

	public void setqHoras(String qHoras) {
		this.qHoras = qHoras;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Maquinaria getMaquinaria() {
		return maquinaria;
	}

	public void setMaquinaria(Maquinaria maquinaria) {
		this.maquinaria = maquinaria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Maquinista getMaquinista() {
		return maquinista;
	}

	public void setMaquinista(Maquinista maquinista) {
		this.maquinista = maquinista;
	}

	
	
}
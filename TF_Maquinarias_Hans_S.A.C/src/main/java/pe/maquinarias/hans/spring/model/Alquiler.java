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
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Alquiler")
public class Alquiler implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlquiler;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameAlquiler",nullable=false, length=60)
	private String nameAlquiler;
	
	@NotNull
	@Future(message = "La fecha debe estar en el futuro")
	@Temporal(TemporalType.DATE)
	@Column(name="dateAlquiler")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateAlquiler;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="horaAlquiler",nullable=false, length=60)
	private String horaAlquiler;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="telefonoAlquiler",nullable=false, length=60)
	private String telefonoAlquiler;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="direccionAlquiler",nullable=false, length=60)
	private String direccionAlquiler;
	
	@ManyToOne
	@JoinColumn(name="idUsuario",nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idMaquinaria",nullable=false)
	private Maquinaria maquinaria;
	
	@ManyToOne
	@JoinColumn(name="idMaquinista",nullable=false)
	private Maquinista maquinista;
	
	public Alquiler() {
		super();
	}

	public Alquiler(int idAlquiler,String nameAlquiler,Usuario usuario,Maquinaria maquinaria,Maquinista maquinista,Date dateAlquiler,String horaAlquiler,String telefonoAlquiler,String direccionAlquiler) {
		super();
		this.idAlquiler = idAlquiler;
		this.nameAlquiler = nameAlquiler;
		this.usuario = usuario;
		this.maquinaria = maquinaria;
		this.maquinista = maquinista;
		this.dateAlquiler = dateAlquiler;
		this.horaAlquiler = horaAlquiler;
		this.telefonoAlquiler = telefonoAlquiler;
		this.direccionAlquiler = direccionAlquiler;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Maquinaria getMaquinaria() {
		return maquinaria;
	}

	public void setMaquinaria(Maquinaria maquinaria) {
		this.maquinaria = maquinaria;
	}

	public Maquinista getMaquinista() {
		return maquinista;
	}

	public void setMaquinista(Maquinista maquinista) {
		this.maquinista = maquinista;
	}

	public Date getDateAlquiler() {
		return dateAlquiler;
	}

	public void setDateAlquiler(Date dateAlquiler) {
		this.dateAlquiler = dateAlquiler;
	}

	public String getHoraAlquiler() {
		return horaAlquiler;
	}

	public void setHoraAlquiler(String horaAlquiler) {
		this.horaAlquiler = horaAlquiler;
	}

	public String getTelefonoAlquiler() {
		return telefonoAlquiler;
	}

	public void setTelefonoAlquiler(String telefonoAlquiler) {
		this.telefonoAlquiler = telefonoAlquiler;
	}	
	
	public String getDireccionAlquiler() {
		return direccionAlquiler;
	}

	public void setDireccionAlquiler(String direccionAlquiler) {
		this.direccionAlquiler = direccionAlquiler;
	}	
}


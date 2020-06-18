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
@Table(name="Usuario")
public class Usuario implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameUsuario",nullable=false,length=60)
	private String nameUsuario;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="dniUsuario",nullable=false,length=60)
	private String dniUsuario;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="rucUsuario",nullable=false,length=60)
	private String rucUsuario;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="empresaUsuario",nullable=false,length=60)
	private String empresaUsuario;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="telefonoUsuario",nullable=false,length=60)
	private String telefonoUsuario;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="direccionUsuario",nullable=false,length=60)
	private String direccionUsuario;

	public Usuario() {
		super();
	}

	public Usuario(int idUsuario,String nameUsuario,String dniUsuario,String rucUsuario,String empresaUsuario, String telefonoUsuario, String direccionUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nameUsuario = nameUsuario;
		this.dniUsuario = dniUsuario;
		this.rucUsuario = rucUsuario;
		this.empresaUsuario = empresaUsuario;
		this.telefonoUsuario = telefonoUsuario;
		this.direccionUsuario = direccionUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNameUsuario() {
		return nameUsuario;
	}

	public void setNameUsuario(String nameUsuario) {
		this.nameUsuario = nameUsuario;
	}
	
	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	
	public String getRucUsuario() {
		return rucUsuario;
	}

	public void setRucUsuario(String rucUsuario) {
		this.rucUsuario = rucUsuario;
	}
	
	public String getEmpresaUsuario() {
		return empresaUsuario;
	}

	public void setEmpresaUsuario(String empresaUsuario) {
		this.empresaUsuario = empresaUsuario;
	}

	public String getTelefonoUsuario() {
		return telefonoUsuario;
	}

	public void setTelefonoUsuario(String telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}

	public String getDireccionUsuario() {
		return direccionUsuario;
	}

	public void setDireccionUsuario(String direccionUsuario) {
		this.direccionUsuario = direccionUsuario;
	}

}
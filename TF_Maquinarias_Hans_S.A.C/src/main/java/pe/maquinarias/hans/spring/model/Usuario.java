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
import javax.validation.constraints.Size;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameUsuario",nullable=false,length=30)
	private String nameUsuario;
	
	@Size(min=8, max=8, message= "El DNI del usuario debe tener 8 dijitos")
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="dniUsuario",nullable=false,length=8)
	private String dniUsuario;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="empresaUsuario",nullable=false,length=30)
	private String empresaUsuario;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="direccionUsuario",nullable=false,length=30)
	private String direccionUsuario;

	public Usuario() {
		super();
	}

	public Usuario(int idUsuario,String nameUsuario,String dniUsuario,String empresaUsuario, String direccionUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nameUsuario = nameUsuario;
		this.dniUsuario = dniUsuario;
		this.empresaUsuario = empresaUsuario;
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
	
	public String getEmpresaUsuario() {
		return empresaUsuario;
	}

	public void setEmpresaUsuario(String empresaUsuario) {
		this.empresaUsuario = empresaUsuario;
	}

	public String getDireccionUsuario() {
		return direccionUsuario;
	}

	public void setDireccionUsuario(String direccionUsuario) {
		this.direccionUsuario = direccionUsuario;
	}

}
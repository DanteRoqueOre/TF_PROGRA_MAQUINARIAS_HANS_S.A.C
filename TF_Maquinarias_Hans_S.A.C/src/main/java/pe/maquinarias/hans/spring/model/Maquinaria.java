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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Maquinaria")
public class Maquinaria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMaquinaria;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameMaquinaria",nullable=false, length=30)
	private String nameMaquinaria;
	
	@NotNull
	@Past(message="No puedes seleccionar un dia que NO existe")
	@Temporal(TemporalType.DATE)
	@Column(name="dateMaquinaria")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateMaquinaria;
	
	@Min(value=700, message="Debe ser igual o mayor que 700")  
    @Max(value=150000, message="Debe ser igual o menor que 150000") 
	private int pesoMaquinaria;

	@Min(value=50, message="Debe ser igual o mayor que 50")  
    @Max(value=1500, message="Debe ser igual o menor que 1500") 
	private int potenciaMaquinaria;
	
	@ManyToOne
	@JoinColumn(name="idModelo",nullable=false)
	private Modelo modelo;
	
	@ManyToOne
	@JoinColumn(name="idMarca",nullable=false)
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name="idTipo",nullable=false)
	private Tipo tipo;
	
	public Maquinaria() {
		super();
	}

	public Maquinaria(int idMaquinaria,String nameMaquinaria,Modelo modelo,Marca marca,Tipo tipo,Date dateMaquinaria,int pesoMaquinaria,int potenciaMaquinaria) {
		super();
		this.idMaquinaria = idMaquinaria;
		this.nameMaquinaria = nameMaquinaria;
		this.modelo = modelo;
		this.marca = marca;
		this.tipo = tipo;
		this.dateMaquinaria = dateMaquinaria;
		this.pesoMaquinaria = pesoMaquinaria;
		this.potenciaMaquinaria = potenciaMaquinaria;
	}

	public int getIdMaquinaria() {
		return idMaquinaria;
	}

	public void setIdMaquinaria(int idMaquinaria) {
		this.idMaquinaria = idMaquinaria;
	}

	public String getNameMaquinaria() {
		return nameMaquinaria;
	}

	public void setNameMaquinaria(String nameMaquinaria) {
		this.nameMaquinaria = nameMaquinaria;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Date getDateMaquinaria() {
		return dateMaquinaria;
	}

	public void setDateMaquinaria(Date dateMaquinaria) {
		this.dateMaquinaria = dateMaquinaria;
	}

	public int getPesoMaquinaria() {
		return pesoMaquinaria;
	}

	public void setPesoMaquinaria(int pesoMaquinaria) {
		this.pesoMaquinaria = pesoMaquinaria;
	}

	public int getPotenciaMaquinaria() {
		return potenciaMaquinaria;
	}

	public void setPotenciaMaquinaria(int potenciaMaquinaria) {
		this.potenciaMaquinaria = potenciaMaquinaria;
	}	
}
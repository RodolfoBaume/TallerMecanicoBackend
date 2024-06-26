package com.tallerMecanico.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVehiculo;
	private String vin;
	private String matricula;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modeloId")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Modelo modelo;
	private int anioModelo;
	private String color;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipoMotorId")
	private TipoMotor tipoMotor;
	private String imagen;
	@ManyToOne
    @JoinColumn(name = "clienteId")
	@JsonBackReference
    private Cliente cliente;
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehiculoId")
	//@JsonManagedReference
    private List<OrdenServicio> ordenServicio;
	
	public Vehiculo() {
		super();
	}

	public Vehiculo(long idVehiculo, String vin, String matricula, Modelo modelo, int anioModelo, String color,
			TipoMotor tipoMotor, String imagen, Cliente cliente, List<OrdenServicio> ordenServicio) {
		super();
		this.idVehiculo = idVehiculo;
		this.vin = vin;
		this.matricula = matricula;
		this.modelo = modelo;
		this.anioModelo = anioModelo;
		this.color = color;
		this.tipoMotor = tipoMotor;
		this.imagen = imagen;
		this.cliente = cliente;
		this.ordenServicio = ordenServicio;
	}

	public long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public int getAnioModelo() {
		return anioModelo;
	}

	public void setAnioModelo(int anioModelo) {
		this.anioModelo = anioModelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public TipoMotor getTipoMotor() {
		return tipoMotor;
	}

	public void setTipoMotor(TipoMotor tipoMotor) {
		this.tipoMotor = tipoMotor;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<OrdenServicio> getOrdenServicio() {
		return ordenServicio;
	}

	public void setOrdenServicio(List<OrdenServicio> ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	
}

package com.example.spring_futbol.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "clubes")
public class Club {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String ciudad;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "entrenador_id")
	private Entrenador entrenador;

	// Relación bidireccional: un club tiene muchos jugadores
	@OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
	private List<Jugador> jugadores;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asociacion_id")
	private Asociacion asociacion;

	@ManyToMany
	@JoinTable(name = "club_competicion", joinColumns = @JoinColumn(name = "club_id"), inverseJoinColumns = @JoinColumn(name = "competicion_id"))
	private List<Competicion> competiciones;

	// getters y setters...

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Asociacion getAsociacion() {
		return asociacion;
	}

	public void setAsociacion(Asociacion asociacion) {
		this.asociacion = asociacion;
	}

	public List<Competicion> getCompeticiones() {
		return competiciones;
	}

	public void setCompeticiones(List<Competicion> competiciones) {
		this.competiciones = competiciones;
	}
}

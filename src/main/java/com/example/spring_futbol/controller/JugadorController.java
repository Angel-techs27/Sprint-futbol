package com.example.spring_futbol.controller;

import com.example.spring_futbol.entity.Club;
import com.example.spring_futbol.entity.Jugador;
import com.example.spring_futbol.repository.ClubRepository;
import com.example.spring_futbol.repository.JugadorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

	private final JugadorRepository jugadorRepository;
	private final ClubRepository clubRepository;

	public JugadorController(JugadorRepository jugadorRepository, ClubRepository clubRepository) {
		this.jugadorRepository = jugadorRepository;
		this.clubRepository = clubRepository;
	}

	// LISTAR
	@GetMapping
	public String listarJugadores(Model model) {
		model.addAttribute("jugadores", jugadorRepository.findAll());
		return "jugadores-lista";
	}

	// NUEVO
	@GetMapping("/nuevo")
	public String mostrarFormularioNuevo(Model model) {
		model.addAttribute("jugador", new Jugador());
		model.addAttribute("clubes", clubRepository.findAll());
		return "jugadores-form";
	}

	// EDITAR
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
		Jugador jugador = jugadorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id de jugador inválido: " + id));

		model.addAttribute("jugador", jugador);
		model.addAttribute("clubes", clubRepository.findAll());
		return "jugadores-form";
	}

	// GUARDAR
	@PostMapping("/guardar")
	public String guardarJugador(@ModelAttribute Jugador jugador, @RequestParam("clubId") Long clubId) {

		Club club = clubRepository.findById(clubId).orElse(null);
		jugador.setClub(club);

		jugadorRepository.save(jugador);
		return "redirect:/jugadores";
	}

	// ELIMINAR
	@GetMapping("/eliminar/{id}")
	public String eliminarJugador(@PathVariable Long id) {
		jugadorRepository.deleteById(id);
		return "redirect:/jugadores";
	}
}

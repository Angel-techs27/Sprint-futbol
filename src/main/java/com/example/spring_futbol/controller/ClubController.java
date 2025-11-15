package com.example.spring_futbol.controller;

import com.example.spring_futbol.entity.*;
import com.example.spring_futbol.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clubes")
public class ClubController {

	private final ClubRepository clubRepository;
	private final EntrenadorRepository entrenadorRepository;
	private final AsociacionRepository asociacionRepository;
	private final CompeticionRepository competicionRepository;
	private final JugadorRepository jugadorRepository;

	public ClubController(ClubRepository clubRepository, EntrenadorRepository entrenadorRepository,
			AsociacionRepository asociacionRepository, CompeticionRepository competicionRepository,
			JugadorRepository jugadorRepository) {
		this.clubRepository = clubRepository;
		this.entrenadorRepository = entrenadorRepository;
		this.asociacionRepository = asociacionRepository;
		this.competicionRepository = competicionRepository;
		this.jugadorRepository = jugadorRepository;
	}

	// LISTAR: GET /clubes
	@GetMapping
	public String listarClubes(Model model) {
		model.addAttribute("clubes", clubRepository.findAll());
		return "clubes-listar";
	}

	// MOSTRAR FORM CREAR: GET /clubes/nuevo
	@GetMapping("/nuevo")
	public String mostrarFormularioNuevo(Model model) {
		Club club = new Club();
		model.addAttribute("club", club);
		model.addAttribute("entrenadores", entrenadorRepository.findAll());
		model.addAttribute("asociaciones", asociacionRepository.findAll());
		model.addAttribute("competiciones", competicionRepository.findAll());
		return "clubes-form";
	}

	// MOSTRAR FORM EDITAR: GET /clubes/editar/{id}
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
		Club club = clubRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id de club inválido: " + id));

		model.addAttribute("club", club);
		model.addAttribute("entrenadores", entrenadorRepository.findAll());
		model.addAttribute("asociaciones", asociacionRepository.findAll());
		model.addAttribute("competiciones", competicionRepository.findAll());
		return "clubes-form";
	}

	// GUARDAR (crear o editar): POST /clubes/guardar
	@PostMapping("/guardar")
	public String guardarClub(@ModelAttribute Club club, @RequestParam("entrenadorId") Long entrenadorId,
			@RequestParam("asociacionId") Long asociacionId,
			@RequestParam(value = "competicionesIds", required = false) List<Long> competicionesIds) {

		Entrenador entrenador = entrenadorRepository.findById(entrenadorId).orElse(null);
		Asociacion asociacion = asociacionRepository.findById(asociacionId).orElse(null);

		club.setEntrenador(entrenador);
		club.setAsociacion(asociacion);

		if (competicionesIds != null) {
			List<Competicion> comps = competicionRepository.findAllById(competicionesIds);
			club.setCompeticiones(comps);
		} else {
			club.setCompeticiones(null);
		}

		clubRepository.save(club);
		return "redirect:/clubes";
	}

	// ELIMINAR: GET /clubes/eliminar/{id}
	@GetMapping("/eliminar/{id}")
	public String eliminarClub(@PathVariable Long id) {
		clubRepository.deleteById(id);
		return "redirect:/clubes";
	}
}

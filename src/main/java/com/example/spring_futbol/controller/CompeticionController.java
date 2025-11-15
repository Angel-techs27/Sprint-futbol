package com.example.spring_futbol.controller;

import com.example.spring_futbol.entity.Competicion;
import com.example.spring_futbol.repository.CompeticionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/competiciones")
public class CompeticionController {

	private final CompeticionRepository competicionRepository;

	public CompeticionController(CompeticionRepository competicionRepository) {
		this.competicionRepository = competicionRepository;
	}

	// LISTAR
	@GetMapping
	public String listarCompeticiones(Model model) {
		model.addAttribute("competiciones", competicionRepository.findAll());
		return "competiciones-lista";
	}

	// NUEVA
	@GetMapping("/nuevo")
	public String mostrarFormularioNuevo(Model model) {
		model.addAttribute("competicion", new Competicion());
		return "competiciones-form";
	}

	// EDITAR
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
		Competicion competicion = competicionRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id de competición inválido: " + id));
		model.addAttribute("competicion", competicion);
		return "competiciones-form";
	}

	// GUARDAR
	@PostMapping("/guardar")
	public String guardarCompeticion(@ModelAttribute Competicion competicion) {
		competicionRepository.save(competicion);
		return "redirect:/competiciones";
	}

	// ELIMINAR
	@GetMapping("/eliminar/{id}")
	public String eliminarCompeticion(@PathVariable Long id) {
		competicionRepository.deleteById(id);
		return "redirect:/competiciones";
	}
}

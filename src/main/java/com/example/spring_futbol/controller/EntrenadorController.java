package com.example.spring_futbol.controller;

import com.example.spring_futbol.entity.Entrenador;
import com.example.spring_futbol.repository.EntrenadorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorController {

	private final EntrenadorRepository entrenadorRepository;

	public EntrenadorController(EntrenadorRepository entrenadorRepository) {
		this.entrenadorRepository = entrenadorRepository;
	}

	// LISTAR: GET /entrenadores
	@GetMapping
	public String listarEntrenadores(Model model) {
		model.addAttribute("entrenadores", entrenadorRepository.findAll());
		return "entrenadores-lista";
	}

	// FORM NUEVO: GET /entrenadores/nuevo
	@GetMapping("/nuevo")
	public String mostrarFormularioNuevo(Model model) {
		model.addAttribute("entrenador", new Entrenador());
		return "entrenadores-form";
	}

	// FORM EDITAR: GET /entrenadores/editar/{id}
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
		Entrenador entrenador = entrenadorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id de entrenador inválido: " + id));
		model.addAttribute("entrenador", entrenador);
		return "entrenadores-form";
	}

	// GUARDAR: POST /entrenadores/guardar
	@PostMapping("/guardar")
	public String guardarEntrenador(@ModelAttribute Entrenador entrenador) {
		entrenadorRepository.save(entrenador);
		return "redirect:/entrenadores";
	}

	// ELIMINAR: GET /entrenadores/eliminar/{id}
	@GetMapping("/eliminar/{id}")
	public String eliminarEntrenador(@PathVariable Long id) {
		entrenadorRepository.deleteById(id);
		return "redirect:/entrenadores";
	}
}

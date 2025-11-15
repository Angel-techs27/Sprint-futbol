package com.example.spring_futbol.controller;

import com.example.spring_futbol.entity.Asociacion;
import com.example.spring_futbol.repository.AsociacionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionController {

	private final AsociacionRepository asociacionRepository;

	public AsociacionController(AsociacionRepository asociacionRepository) {
		this.asociacionRepository = asociacionRepository;
	}

	// LISTAR
	@GetMapping
	public String listarAsociaciones(Model model) {
		model.addAttribute("asociaciones", asociacionRepository.findAll());
		return "asociaciones-lista";
	}

	// NUEVO
	@GetMapping("/nuevo")
	public String mostrarFormularioNuevo(Model model) {
		model.addAttribute("asociacion", new Asociacion());
		return "asociaciones-form";
	}

	// EDITAR
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
		Asociacion asociacion = asociacionRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id de asociación inválido: " + id));
		model.addAttribute("asociacion", asociacion);
		return "asociaciones-form";
	}

	// GUARDAR
	@PostMapping("/guardar")
	public String guardarAsociacion(@ModelAttribute Asociacion asociacion) {
		asociacionRepository.save(asociacion);
		return "redirect:/asociaciones";
	}

	// ELIMINAR
	@GetMapping("/eliminar/{id}")
	public String eliminarAsociacion(@PathVariable Long id) {
		asociacionRepository.deleteById(id);
		return "redirect:/asociaciones";
	}
}

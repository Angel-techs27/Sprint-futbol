package com.example.spring_futbol.config;

import com.example.spring_futbol.entity.*;
import com.example.spring_futbol.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

	private final ClubRepository clubRepository;
	private final EntrenadorRepository entrenadorRepository;
	private final JugadorRepository jugadorRepository;
	private final AsociacionRepository asociacionRepository;
	private final CompeticionRepository competicionRepository;

	public DataLoader(ClubRepository clubRepository, EntrenadorRepository entrenadorRepository,
			JugadorRepository jugadorRepository, AsociacionRepository asociacionRepository,
			CompeticionRepository competicionRepository) {
		this.clubRepository = clubRepository;
		this.entrenadorRepository = entrenadorRepository;
		this.jugadorRepository = jugadorRepository;
		this.asociacionRepository = asociacionRepository;
		this.competicionRepository = competicionRepository;
	}

	@Override
	public void run(String... args) {
		if (clubRepository.count() > 0) {
			return; // ya hay datos, no cargamos otra vez
		}

		// Asociación
		Asociacion asociacion = new Asociacion();
		asociacion.setNombre("Asociación Nacional de Fútbol");
		asociacion.setPais("Colombia");
		asociacion.setPresidente("Juan Pérez");
		asociacionRepository.save(asociacion);

		// Entrenador
		Entrenador entrenador = new Entrenador();
		entrenador.setNombre("Carlos");
		entrenador.setApellido("Gómez");
		entrenador.setEdad(45);
		entrenador.setNacionalidad("Colombiano");
		entrenadorRepository.save(entrenador);

		// Competición
		Competicion liga = new Competicion();
		liga.setNombre("Liga Profesional");
		liga.setMontoPremio(5000000);
		competicionRepository.save(liga);

		// Club (primero creamos el club sin jugadores)
		Club club = new Club();
		club.setNombre("Club Atlético Arcadia");
		club.setCiudad("Bucaramanga");
		club.setEntrenador(entrenador);
		club.setAsociacion(asociacion);
		club.setCompeticiones(List.of(liga));
		clubRepository.save(club);

		// Jugadores (asignando el club)
		Jugador j1 = new Jugador();
		j1.setNombre("Luis");
		j1.setApellido("Martínez");
		j1.setNumero(10);
		j1.setPosicion("Delantero");
		j1.setClub(club);

		Jugador j2 = new Jugador();
		j2.setNombre("Andrés");
		j2.setApellido("Rodríguez");
		j2.setNumero(5);
		j2.setPosicion("Defensa");
		j2.setClub(club);

		jugadorRepository.saveAll(List.of(j1, j2));
	}
}
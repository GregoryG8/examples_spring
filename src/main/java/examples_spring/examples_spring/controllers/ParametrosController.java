package examples_spring.examples_spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import examples_spring.examples_spring.models.Equipo;
import examples_spring.examples_spring.models.Jugador;

@Controller
public class ParametrosController {

    @GetMapping("/parametros")
    public String parametros(@RequestParam(defaultValue = "Valor default") String valor,
            @RequestParam(defaultValue = "", name = "valor_dos") String otroValor, Model model) {
        model.addAttribute("titulo", "Parametros");
        model.addAttribute("parametro", valor);
        model.addAttribute("otroValor", otroValor);
        return "parametros";
    }

    // /equipos/{nombre_equipo}/{numero_jugador}

    @GetMapping("/equipos/{nombre}/{numero}")
    public String parametrosPorPath(@PathVariable String nombre, @PathVariable("numero") Integer numero, Model model) {

        Optional<Equipo> equipoOptional = getEquipos().stream()
                .filter(equipo -> nombre.toLowerCase().equals(equipo.getNombre().toLowerCase())).findFirst();

        if (equipoOptional.isPresent()) {
            Optional<Jugador> jugadorOptional = equipoOptional.get().getPlantilla().stream()
                    .filter(jugador -> numero == jugador.getNumero()).findFirst();

                    if (jugadorOptional.isPresent()) {
                        model.addAttribute("jugador", jugadorOptional.get());
                    }
        }

        return "parametros";
    }

    private List<Equipo> getEquipos() {
        Equipo barcelona = new Equipo();
        Equipo realMadrid = new Equipo();

        barcelona.setNombre("Barcelona");
        barcelona.addJugador(new Jugador("TER STEGEN", 1));
        barcelona.addJugador(new Jugador("ARAUJO", 4));
        barcelona.addJugador(new Jugador("LEWANDOSKI", 9));
        barcelona.addJugador(new Jugador("PEDRI", 8));
        barcelona.addJugador(new Jugador("GAVI", 6));

        realMadrid.setNombre("Real Madrid");
        realMadrid.addJugador(new Jugador("VINI JR", 7));
        realMadrid.addJugador(new Jugador("BELLINGHAM", 5));
        realMadrid.addJugador(new Jugador("MODRIC", 10));
        realMadrid.addJugador(new Jugador("KROSS", 8));
        realMadrid.addJugador(new Jugador("RODRYGO", 11));

        return List.of(barcelona, realMadrid);
    }

}

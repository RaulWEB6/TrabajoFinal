package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Trabajador;
import pe.edu.upc.service.ITrabajadorService;

@Controller
@SessionAttributes("trabajador")
@RequestMapping("/trabajadores")
public class TrabajadorController {
	@Autowired
	private ITrabajadorService tService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "bienvenido";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/nuevo")
	public String nuevoTrabajador(Model model) {
		model.addAttribute("trabajador", new Trabajador());
		return "trabajador/trabajador";
	}

	@PostMapping("/guardar")
	public String guardarTrabajador(@Valid Trabajador trabajador, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/trabajador/trabajador";
		} else {
			int rpta = tService.insertar(trabajador);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe el trabajador con ese DNI");
				return  "/trabajador/trabajador";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaTrabajadores", tService.listar());
		return "/trabajador/listaTrabajador";
	}

	@GetMapping("/listar")
	public String listarTrabajadores(Model model) {
		try {
			model.addAttribute("trabajador", new Trabajador());
			model.addAttribute("listaTrabajadores", tService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/trabajador/listaTrabajador";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				tService.eliminar(id);
				model.put("mensaje", "Se despidio correctamente al trabajador");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede retirar al trabajador seleccionado");
		}
		model.put("listaTrabajadores", tService.listar());

		return "redirect:/trabajadores/listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}")//modificar
	public String detailsCliente(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Trabajador> trabajador = tService.listarId(id);
			if (!trabajador.isPresent()) {
				model.addAttribute("info", "Trabajador no existe");
				return "redirect:/trabajadores/listar";
			} else {
				model.addAttribute("trabajador", trabajador.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/trabajador/trabajador";
	}

	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Trabajador trabajador) throws ParseException {

		List<Trabajador> listaTrabajadores;

		trabajador.setNombreTrabajador(trabajador.getNombreTrabajador());
		listaTrabajadores = tService.buscarNombre(trabajador.getNombreTrabajador());
		if (listaTrabajadores.isEmpty()) {
			model.put("mensaje", "No se encontró al trabajador con el nombre espesificado");
		}
		model.put("listaTrabajadores", listaTrabajadores);
		return "trabajador/listaTrabajador";
	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Trabajador> trabajador = tService.listarId(id);
		if (trabajador == null) {
			flash.addFlashAttribute("error", "El trabajador no existe en la base de datos");
			return "redirect:/trabajadores/listar";
		}

		model.put("trabajador", trabajador.get());

		return "trabajador/ver";
	}
}

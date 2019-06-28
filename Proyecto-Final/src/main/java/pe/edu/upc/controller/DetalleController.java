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

import pe.edu.upc.entity.Detalle_orden;
import pe.edu.upc.service.IDetalleService;
import pe.edu.upc.service.IOrdenService;
import pe.edu.upc.service.IRecursoService;

@Controller
@SessionAttributes("detalle")
@RequestMapping("/detalles")
public class DetalleController {

	@Autowired
	private IDetalleService dService;

	@Autowired
	private IOrdenService oService;

	@Autowired
	private IRecursoService rService;

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/bienvenido")
	public String bienvenido(Model model) {
		return "bienvenido";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/nuevo")
	public String nuevoDetalle(Model model) {
		model.addAttribute("detalle", new Detalle_orden());
		model.addAttribute("listaOrdenes", oService.listar());
		model.addAttribute("listaRecursos", rService.listar());
		return "detalle/detalle";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/guardar")
	public String guardarDetalle(@Valid Detalle_orden detalle, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaOrdenes", oService.listar());
			model.addAttribute("listaRecursos", rService.listar());
			return "/detalle/detalle";
		} else {
			dService.insertar(detalle);
			model.addAttribute("mensaje", "Se guardó correctamente la orden");
			status.setComplete();
			return "redirect:/detalles/listar";
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/listar")
	public String listarDetalles(Model model) {
		try {
			model.addAttribute("detalle", new Detalle_orden());
			model.addAttribute("listaDetalles", dService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/detalle/listaDetalle";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}")
	public String detailsDetalle(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Detalle_orden> detalle = dService.listarId(id);

			if (!detalle.isPresent()) {
				model.addAttribute("info", "Detalle de la orden no existe");
				return "redirect:/detalles/listar";
			} else {
				model.addAttribute("detalle", detalle.get());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/detalle/detalle";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				dService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente el detalle de orden");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el detalle de orden");
		}
		model.put("listaDetalles", dService.listar());
		return "redirect:/detalles/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Detalle_orden detalle) throws ParseException {
		List<Detalle_orden> listaDetalles;
		detalle.setIdDetalle(detalle.getIdDetalle());
		listaDetalles = dService.buscarOrden(detalle.getIdDetalle());
		if (listaDetalles.isEmpty()) {
			model.put("mensaje", "No se encontro ningun resultado");
		}
		model.put("listaDetalles", listaDetalles);
		return "detalle/listaDetalle";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Detalle_orden> objPro = dService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error al modificar en detalle de orden");
			return "redirect:/detalles/listar";
		} else {
			model.addAttribute("listaOrdenes", oService.listar());
			model.addAttribute("listaRecursos", rService.listar());
			model.addAttribute("detalle", objPro.get());
			return "detalle/detalle";
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {
		Optional<Detalle_orden> detalle = dService.listarId(id);
		if (detalle == null) {
			flash.addFlashAttribute("error", "El detalle de orden de venta no existe en la base de datos");
			return "redirect:/detalles/listar";
		}
		model.put("detalle", detalle.get());
		return "detalle/verd";
	}
}

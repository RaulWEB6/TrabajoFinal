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

import pe.edu.upc.entity.Orden_compra;
import pe.edu.upc.service.IOrdenService;
import pe.edu.upc.service.IProveedorService;
import pe.edu.upc.service.ITrabajadorService;

@Controller
@SessionAttributes("orden")
@RequestMapping("/ordenes")
public class OrdenController {

	@Autowired
	private IOrdenService oService;
	@Autowired
	private ITrabajadorService tService;
	@Autowired
	private IProveedorService pService;

	@GetMapping("/bienvenido")
	public String bienvenido(Model model) {
		return "bienvenido";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/nuevo")
	public String nuevaOrden(Model model) {
		model.addAttribute("orden", new Orden_compra());
		model.addAttribute("listaTrabajadores", tService.listar());
		model.addAttribute("listaProveedores", pService.listar());
		return "orden/orden";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/guardar")
	public String guardarOrden(@Valid Orden_compra orden, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaTrabajador", tService.listar());
			model.addAttribute("listaProveedor", pService.listar());
			return "/orden/orden";
		} else {
			oService.insertar(orden);
			model.addAttribute("mensaje", "Se guardó correctamente la orden");
			status.setComplete();
			return "redirect:/ordenes/listar";
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/listar")
	public String listarOrdenes(Model model) {
		try {
			model.addAttribute("orden", new Orden_compra());

			model.addAttribute("listaOrdenes", oService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/orden/listaOrden";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}")
	public String detailsOrden(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Orden_compra> orden = oService.listarId(id);

			if (!orden.isPresent()) {
				model.addAttribute("info", "Orden no existe");
				return "redirect:/ordenes/listar";
			} else {
				model.addAttribute("orden", orden.get());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/orden/orden";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				oService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente la orden");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar la orden");
		}
		model.put("listaOrdenes", oService.listar());
		return "redirect:/ordenes/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Orden_compra orden) throws ParseException {
		List<Orden_compra> listaOrdenes;
		orden.setNotaOrden(orden.getNotaOrden());
		listaOrdenes = oService.buscar(orden.getNotaOrden());
		if (listaOrdenes.isEmpty()) {
			listaOrdenes = oService.buscarTrabajador(orden.getNotaOrden());
		}
		if (listaOrdenes.isEmpty()) {
			listaOrdenes = oService.buscarProveedor(orden.getNotaOrden());
		}
		if (listaOrdenes.isEmpty()) {
			model.put("mensaje", "No se encontro ningun resultado");
		}
		model.put("listaOrdenes", listaOrdenes);
		return "orden/listaOrden";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Orden_compra> objPro = oService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error al modificar");
			return "redirect:/ordenes/listar";
		} else {
			model.addAttribute("listaTrabajadores", tService.listar());
			model.addAttribute("listaProveedores", pService.listar());
			model.addAttribute("orden", objPro.get());
			return "orden/orden";
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {
		Optional<Orden_compra> orden = oService.listarId(id);
		if (orden == null) {
			flash.addFlashAttribute("error", "La orden de compra no existe en la base de datos");
			return "redirect:/ordenes/listar";
		}
		model.put("orden", orden.get());
		return "orden/vero";
	}
}

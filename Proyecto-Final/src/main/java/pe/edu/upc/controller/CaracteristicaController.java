package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import pe.edu.upc.entity.Caracteristica;
import pe.edu.upc.service.ICaracteristicaService;
import pe.edu.upc.service.IRecursoService;

@Controller
@SessionAttributes("caracteristica")
@RequestMapping("/caracteristicas")
public class CaracteristicaController {
	@Autowired
	private ICaracteristicaService caService;// p
	@Autowired
	private IRecursoService rService;// c

	@GetMapping("/bienvenido")
	public String bienvenido(Model model) {
		return "bienvenido";
	}
	
	@GetMapping("/nuevo")
	public String nuevaCaracteristica(Model model) {
		model.addAttribute("caracteristica", new Caracteristica());
		model.addAttribute("listaRecursos", rService.listar());
		return "caracteristica/caracteristica";
	}

	@PostMapping("/guardar")
	public String guardarCaracteristica(@Valid Caracteristica caracteristica, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaRecursos", rService.listar());
			return "/caracteristica/caracteristica";
		} else {
			caService.insertar(caracteristica);
			model.addAttribute("mensaje", "Se guardó correctamente la caracteristica");
			status.setComplete();
			return "redirect:/caracteristicas/listar";
		}
	}

	@GetMapping("/listar")
	public String listarCaracteristicas(Model model) {
		try {
			model.addAttribute("caracteristica", new Caracteristica());

			model.addAttribute("listaCaracteristicas", caService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/caracteristica/listaCaracteristica";
	}

	@GetMapping("/detalle/{id}")
	public String detailsCaracteristica(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Caracteristica> caracteristica = caService.listarId(id);

			if (!caracteristica.isPresent()) {
				model.addAttribute("info", "Caracteristica no existe");
				return "redirect:/caracteristicas/listar";
			} else {
				model.addAttribute("caracteristica", caracteristica.get());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/caracteristica/caracteristica";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				caService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente la caracteristica");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar la caracteristica");
		}
		model.put("listaCaracteristicas", caService.listar());
		return "redirect:/caracteristicas/listar";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Caracteristica caracteristica)
			throws ParseException {
		List<Caracteristica> listaCaracteristicas;
		caracteristica.setNombreCaracteristica(caracteristica.getNombreCaracteristica());
		listaCaracteristicas = caService.buscar(caracteristica.getNombreCaracteristica());
		if (listaCaracteristicas.isEmpty()) {
			listaCaracteristicas = caService.buscarRecurso(caracteristica.getNombreCaracteristica());
		}
		if (listaCaracteristicas.isEmpty()) {
			model.put("mensaje", "No se encontro el recurso");
		}
		model.put("listaCaracteristicas", listaCaracteristicas);
		return "caracteristica/listaCaracteristica";
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Caracteristica> objPro = caService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error al modificar");
			return "redirect:/caracteristicas/listar";
		} else {
			model.addAttribute("listaRecursos", rService.listar());
			model.addAttribute("caracteristica", objPro.get());
			return "caracteristica/caracteristica";
		}
	}
}

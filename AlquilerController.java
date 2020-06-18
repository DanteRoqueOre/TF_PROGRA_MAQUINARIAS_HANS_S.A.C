package pe.maquinarias.hans.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.maquinarias.hans.spring.model.Alquiler;
import pe.maquinarias.hans.spring.model.Maquinaria;
import pe.maquinarias.hans.spring.model.Maquinista;
import pe.maquinarias.hans.spring.model.Usuario;
import pe.maquinarias.hans.spring.service.IAlquilerService;
import pe.maquinarias.hans.spring.service.IMaquinariaService;
import pe.maquinarias.hans.spring.service.IMaquinistaService;
import pe.maquinarias.hans.spring.service.IUsuarioService;


@Controller
@RequestMapping("/alquiler")
public class AlquilerController {

	@Autowired
	private IMaquinariaService mqService;
	@Autowired
	private IMaquinistaService mquiService;
	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IAlquilerService aqService;	
	
	@RequestMapping("/bienvenido")
	public String irAlquilerBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irAlquiler(Map<String, Object> model) {
		model.put("listaAlquileres", aqService.listar());
		return "listAlquiler";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("listaMaquinarias", mqService.listar());
		model.addAttribute("listaMaquinistas", mquiService.listar());
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("maquinaria", new Maquinaria());
		model.addAttribute("maquinista", new Maquinista());
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("alquiler", new Alquiler());
		return "alquiler";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Alquiler objAlquiler, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaMaquinarias", mqService.listar());
			model.addAttribute("listaMaquinistas", mquiService.listar());
			model.addAttribute("listaUsuarios", uService.listar());
			return "alquiler";
		}
		else {
				boolean flag = aqService.insertar(objAlquiler);
				if (flag) {
					return "redirect:/alquiler/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/alquiler/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Alquiler objAlquiler, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/alquilar/listar";
		}
		else {
			boolean flag = aqService.modificar(objAlquiler);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/alquiler/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/alquiler/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Alquiler> objAlquiler = aqService.listarId(id);
		if (objAlquiler == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/alquiler/listar";
		}
		else {
			model.addAttribute("listaMaquinarias", mqService.listar());
			model.addAttribute("listaMaquinistas", mquiService.listar());		
			model.addAttribute("listausuarios", uService.listar());	
			if (objAlquiler.isPresent())
				objAlquiler.ifPresent(o -> model.addAttribute("alquiler", o));						
			return "alquiler";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				aqService.eliminar(id);
				model.put("listaAlquileres", aqService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaAlquileres", aqService.listar());
		}
		return "listAlquiler";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("lista", aqService.listar());
		return "listAlquiler";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Alquiler alquiler) 
	throws ParseException
	{
		aqService.listarId(alquiler.getIdAlquiler());
		return "listAlquiler";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Alquiler alquiler) 
			throws ParseException
	{
		List<Alquiler> listaAlquileres;
		alquiler.setNameAlquiler(alquiler.getNameAlquiler());
		listaAlquileres = aqService.buscarNombre(alquiler.getNameAlquiler());
		
		if (listaAlquileres.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaAlquileres", listaAlquileres);
		return "buscarAlquiler";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("alquilar", new Alquiler());
		return "buscarAlquiler";
	}	
}
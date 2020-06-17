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

import pe.maquinarias.hans.spring.model.Maquinaria;
import pe.maquinarias.hans.spring.model.Marca;
import pe.maquinarias.hans.spring.model.Modelo;
import pe.maquinarias.hans.spring.model.Tipo;
import pe.maquinarias.hans.spring.service.IMaquinariaService;
import pe.maquinarias.hans.spring.service.IMarcaService;
import pe.maquinarias.hans.spring.service.IModeloService;
import pe.maquinarias.hans.spring.service.ITipoService;


@Controller
@RequestMapping("/maquinaria")
public class MaquinariaController {

	@Autowired
	private IModeloService moService;
	@Autowired
	private IMarcaService maService;
	@Autowired
	private ITipoService tService;
	@Autowired
	private IMaquinariaService mqService;	
	
	@RequestMapping("/bienvenido")
	public String irMaquinariaBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irMaquinaria(Map<String, Object> model) {
		model.put("listaMaquinarias", mqService.listar());
		return "listMaquinaria";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("listaModelos", moService.listar());
		model.addAttribute("listaMarcas", maService.listar());
		model.addAttribute("listaTipos", tService.listar());
		model.addAttribute("modelo", new Modelo());
		model.addAttribute("marca", new Marca());
		model.addAttribute("tipo", new Tipo());
		model.addAttribute("maquinaria", new Maquinaria());
		return "maquinaria";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Maquinaria objMaquinaria, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaModelos", moService.listar());
			model.addAttribute("listaMarcas", maService.listar());
			model.addAttribute("listaTipos", tService.listar());
			return "maquinaria";
		}
		else {
				boolean flag = mqService.insertar(objMaquinaria);
				if (flag) {
					return "redirect:/maquinaria/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/maquinaria/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Maquinaria objMaquinaria, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/maquinaria/listar";
		}
		else {
			boolean flag = mqService.modificar(objMaquinaria);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/maquinaria/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/maquinaria/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Maquinaria> objMaquinaria = mqService.listarId(id);
		if (objMaquinaria == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/maquinaria/listar";
		}
		else {
			model.addAttribute("listaModelos", moService.listar());
			model.addAttribute("listaMarcas", maService.listar());		
			model.addAttribute("listaTipos", tService.listar());	
			if (objMaquinaria.isPresent())
				objMaquinaria.ifPresent(o -> model.addAttribute("maquinaria", o));						
			return "maquinaria";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				mqService.eliminar(id);
				model.put("listaMaquinarias", mqService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaMascotas", mqService.listar());
		}
		return "listMaquinaria";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaMaquinarias", mqService.listar());
		return "listMaquinaria";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Maquinaria maquinaria) 
	throws ParseException
	{
		mqService.listarId(maquinaria.getIdMaquinaria());
		return "listMaquinaria";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Maquinaria maquinaria) 
			throws ParseException
	{
		List<Maquinaria> listaMaquinarias;
		maquinaria.setNameMaquinaria(maquinaria.getNameMaquinaria());
		listaMaquinarias = mqService.buscarNombre(maquinaria.getNameMaquinaria());
		
		if (listaMaquinarias.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaMaquinarias", listaMaquinarias);
		return "buscarMaquinaria";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("maquinaria", new Maquinaria());
		return "buscarMaquinaria";
	}	
}
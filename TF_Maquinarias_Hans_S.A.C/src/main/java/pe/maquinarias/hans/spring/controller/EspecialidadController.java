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

import pe.maquinarias.hans.spring.model.Especialidad;
import pe.maquinarias.hans.spring.service.IEspecialidadService;

@Controller
@RequestMapping("/especialidad")
public class EspecialidadController {
	
	@Autowired
	private IEspecialidadService eService;
	
	@RequestMapping("/bienvenido")
	public String irEspecialidadBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irEspecialidad(Map<String,Object>model) {
		model.put("listaEspecialidades", eService.listar());
		return "listEspecialidad";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		model.addAttribute("especialidad",new Especialidad());
		return "especialidad";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Especialidad objEspecialidad,BindingResult binRes,Model model)
	      throws ParseException
	{
		if (binRes.hasErrors()) {
			return "especialidad";
		}
		else {
			int rpta = eService.insertar(objEspecialidad);
			if(rpta > 0) {
				model.addAttribute("mensaje", "Ya existe la Especialidad");
			}
			
			else {
				model.addAttribute("mensaje", "Se guardo correctamente");
			}
		}
		return "listEspecialidad";
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Especialidad objEspecialidad,BindingResult binRes,Model model,RedirectAttributes objRedir) 
			throws ParseException
    {
    	if (binRes.hasErrors()) {
			return "redirect:/especialidad/listar";
		}
		else {
			boolean flag = eService.modificar(objEspecialidad);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/especialidad/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/especialidad/irRegistrar";
			}			
		}		
    	
    }
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id,Model model,RedirectAttributes objRedir)
			throws ParseException 
	{
		Optional<Especialidad> objEspecialidad= eService.listarId(id);
		if (objEspecialidad== null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/especialidad/listar";
		}
		else {
			model.addAttribute("especialidad", objEspecialidad);
			return "especialidad";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				eService.eliminar(id);
				model.put("listaEspecialidades", eService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaEspecialidades", eService.listar());
		}
		return "listEspecialidad";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEspecialidades", eService.listar());
		return "listEspecialidad";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Especialidad especialidad) 
	throws ParseException
	{
		eService.listarId(especialidad.getIdEspecialidad());
		return "listEspecialidad";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Especialidad especialidad) 
			throws ParseException
	{
		List<Especialidad> listaEspecialidades;
		especialidad.setNameEspecialidad(especialidad.getNameEspecialidad());
		listaEspecialidades= eService.buscarNombre(especialidad.getNameEspecialidad());
		
		if (listaEspecialidades.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaEspecialidades", listaEspecialidades);
		return "buscarEspecialidad";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("especialidad", new Especialidad());
		return "buscarEspecialidad";
	}	
}
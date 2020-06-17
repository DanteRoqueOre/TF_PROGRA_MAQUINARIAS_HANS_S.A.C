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

import pe.maquinarias.hans.spring.model.Administrador;
import pe.maquinarias.hans.spring.model.Cargo;
import pe.maquinarias.hans.spring.service.IAdministradorService;
import pe.maquinarias.hans.spring.service.ICargoService;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private ICargoService caService;
	@Autowired
	private IAdministradorService adService;
	
	@RequestMapping("/bienvenido")
	public String irAdministradorBienvenido() {		
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irAdministrador(Map<String, Object> model) {
		model.put("listaAdministradores", adService.listar());
		return "listAdministrador";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("listaCargos", caService.listar());
		model.addAttribute("cargo", new Cargo());
		model.addAttribute("administrador", new Administrador());
		return "administrador";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Administrador objAdministrador, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaCargos", caService.listar());
			return "administrador";
		}
		else {
				boolean flag = adService.insertar(objAdministrador);
				if (flag) {
					return "redirect:/administrador/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/administrador/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Administrador objAdministrador, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/administrador/listar";
		}
		else {
			boolean flag = adService.modificar(objAdministrador);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/administrador/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/administrador/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Administrador> objAdministrador= adService.listarId(id);
		if (objAdministrador == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/administrador/listar";
		}
		else {	
			model.addAttribute("listaCargos", caService.listar());	
			if (objAdministrador.isPresent())
				objAdministrador.ifPresent(o -> model.addAttribute("administrador", o));						
			return "administrador";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				adService.eliminar(id);
				model.put("listaAdministradores", adService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaAdministradores", adService.listar());
		}
		return "listAdministrador";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAdministradores", adService.listar());
		return "listAdministrador";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Administrador administrador) 
	throws ParseException
	{
		adService.listarId(administrador.getIdAdministrador());
		return "listAdministrador";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Administrador administrador) 
			throws ParseException
	{
		List<Administrador> listaAdministradores;
		administrador.setNameAdministrador(administrador.getNameAdministrador());
		listaAdministradores = adService.buscarNombre(administrador.getNameAdministrador());
		
		if (listaAdministradores.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaAdministradores", listaAdministradores);
		return "buscarAdministrador";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("administrador", new Administrador());
		return "buscarAdministrador";
	}	
}
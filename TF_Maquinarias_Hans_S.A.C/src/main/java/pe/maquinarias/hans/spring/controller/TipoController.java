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

import pe.maquinarias.hans.spring.model.Tipo;
import pe.maquinarias.hans.spring.service.ITipoService;

@Controller
@RequestMapping("/tipo")
public class TipoController {
	
	@Autowired
	private ITipoService tService;
	
	@RequestMapping("/bienvenido")
	public String irTipoBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irTipo(Map<String,Object>model) {
		model.put("listaTipos", tService.listar());
		return "listTipo";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		model.addAttribute("tipo",new Tipo());
		return "tipo";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Tipo objTipo,BindingResult binRes,Model model)
	      throws ParseException
	{
		if (binRes.hasErrors()) {
			return "tipo";
		}
		else {
				boolean flag = tService.insertar(objTipo);
				if (flag) {
					return "redirect:/tipo/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/tipo/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Tipo objTipo,BindingResult binRes,Model model,RedirectAttributes objRedir) 
			throws ParseException
    {
    	if (binRes.hasErrors()) {
			return "redirect:/tipo/listar";
		}
		else {
			boolean flag = tService.modificar(objTipo);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/tipo/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/tipo/irRegistrar";
			}			
		}		
    	
    }
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id,Model model,RedirectAttributes objRedir)
			throws ParseException 
	{
		Optional<Tipo> objTipo = tService.listarId(id);
		if (objTipo == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/tipo/listar";
		}
		else {
			model.addAttribute("tipo", objTipo);
			return "tipo";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				tService.eliminar(id);
				model.put("listaTipos", tService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaTipos", tService.listar());
		}
		return "listTipo";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaTipos", tService.listar());
		return "listTipo";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Tipo tipo) 
	throws ParseException
	{
		tService.listarId(tipo.getIdTipo());
		return "listTipo";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Tipo tipo) 
			throws ParseException
	{
		List<Tipo> listaTipos;
		tipo.setNameTipo(tipo.getNameTipo());
		listaTipos = tService.buscarNombre(tipo.getNameTipo());
		
		if (listaTipos.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaTipos", listaTipos);
		return "buscarTipo";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("tipo", new Tipo());
		return "buscarTipo";
	}	
}
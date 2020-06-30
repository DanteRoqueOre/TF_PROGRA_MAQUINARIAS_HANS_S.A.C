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

import pe.maquinarias.hans.spring.model.Modelo;
import pe.maquinarias.hans.spring.service.IModeloService;

@Controller
@RequestMapping("/modelo")
public class ModeloController {
	
	@Autowired
	private IModeloService moService;
	
	@RequestMapping("/bienvenido")
	public String irModeloBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irModelo(Map<String,Object>model) {
		model.put("listaModelos", moService.listar());
		return "listModelo";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		model.addAttribute("modelo",new Modelo());
		return "modelo";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Modelo objModelo,BindingResult binRes,Model model)
	      throws ParseException
	{
		if (binRes.hasErrors()) {
			return "modelo";
		}
		else {
			int rpta = moService.insertar(objModelo);
			if(rpta > 0) {
				model.addAttribute("mensaje", "Ya existe el Modelo");
			}
			
			else {
				model.addAttribute("mensaje", "Se guardo correctamente");
			}
		}
		return "listModelo";
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Modelo objModelo,BindingResult binRes,Model model,RedirectAttributes objRedir) 
			throws ParseException
    {
    	if (binRes.hasErrors()) {
			return "redirect:/modelo/listar";
		}
		else {
			boolean flag = moService.modificar(objModelo);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/modelo/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/modelo/irRegistrar";
			}			
		}		
    	
    }
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id,Model model,RedirectAttributes objRedir)
			throws ParseException 
	{
		Optional<Modelo> objModelo = moService.listarId(id);
		if (objModelo == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/modelo/listar";
		}
		else {
			model.addAttribute("modelo", objModelo);
			return "modelo";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				moService.eliminar(id);
				model.put("listaModelos", moService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaModelos", moService.listar());
		}
		return "listModelo";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaModelos", moService.listar());
		return "listModelo";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Modelo modelo) 
	throws ParseException
	{
		moService.listarId(modelo.getIdModelo());
		return "listModelo";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Modelo modelo) 
			throws ParseException
	{
		List<Modelo> listaModelos;
		modelo.setNameModelo(modelo.getNameModelo());
		listaModelos = moService.buscarNombre(modelo.getNameModelo());
		
		if (listaModelos.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaModelos", listaModelos);
		return "buscarModelo";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("modelo", new Modelo());
		return "buscarModelo";
	}	
}

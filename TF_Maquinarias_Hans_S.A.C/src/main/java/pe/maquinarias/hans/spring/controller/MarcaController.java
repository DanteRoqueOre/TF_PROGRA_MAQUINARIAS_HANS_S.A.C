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

import pe.maquinarias.hans.spring.model.Marca;
import pe.maquinarias.hans.spring.service.IMarcaService;

@Controller
@RequestMapping("/marca")
public class MarcaController {
	
	@Autowired
	private IMarcaService maService;
	
	@RequestMapping("/bienvenido")
	public String irMarcaBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irMarca(Map<String,Object>model) {
		model.put("listaMarcas", maService.listar());
		return "listMarca";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		model.addAttribute("marca",new Marca());
		return "marca";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Marca objMarca,BindingResult binRes,Model model)
	      throws ParseException
	{
		if (binRes.hasErrors()) {
			return "marca";
		}
		else {
				boolean flag = maService.insertar(objMarca);
				if (flag) {
					return "redirect:/marca/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/marca/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Marca objMarca,BindingResult binRes,Model model,RedirectAttributes objRedir) 
			throws ParseException
    {
    	if (binRes.hasErrors()) {
			return "redirect:/marca/listar";
		}
		else {
			boolean flag = maService.modificar(objMarca);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/marca/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/marca/irRegistrar";
			}			
		}		
    	
    }
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id,Model model,RedirectAttributes objRedir)
			throws ParseException 
	{
		Optional<Marca> objMarca = maService.listarId(id);
		if (objMarca == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/marca/listar";
		}
		else {
			model.addAttribute("marca", objMarca);
			return "marca";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				maService.eliminar(id);
				model.put("listaMarcas", maService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaMarcas", maService.listar());
		}
		return "listMarca";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaMarcas", maService.listar());
		return "listMarca";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Marca marca) 
	throws ParseException
	{
		maService.listarId(marca.getIdMarca());
		return "listMarca";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Marca marca) 
			throws ParseException
	{
		List<Marca> listaMarcas;
		marca.setNameMarca(marca.getNameMarca());
		listaMarcas = maService.buscarNombre(marca.getNameMarca());
		
		if (listaMarcas.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaMarcas", listaMarcas);
		return "buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("marca", new Marca());
		return "buscar";
	}	
}
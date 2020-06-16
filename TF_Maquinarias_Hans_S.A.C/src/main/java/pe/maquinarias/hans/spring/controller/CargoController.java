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

import pe.maquinarias.hans.spring.model.Cargo;
import pe.maquinarias.hans.spring.service.ICargoService;

@Controller
@RequestMapping("/cargo")
public class CargoController {
	
	@Autowired
	private ICargoService caService;
	
	@RequestMapping("/bienvenido")
	public String irCargoBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irCargo(Map<String,Object>model) {
		model.put("listaCargos", caService.listar());
		return "listCargo";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		model.addAttribute("cargo",new Cargo());
		return "cargo";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Cargo objCargo,BindingResult binRes,Model model)
	      throws ParseException
	{
		if (binRes.hasErrors()) {
			return "cargo";
		}
		else {
				boolean flag = caService.insertar(objCargo);
				if (flag) {
					return "redirect:/cargo/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/cargo/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Cargo objCargo,BindingResult binRes,Model model,RedirectAttributes objRedir) 
			throws ParseException
    {
    	if (binRes.hasErrors()) {
			return "redirect:/cargo/listar";
		}
		else {
			boolean flag = caService.modificar(objCargo);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/cargo/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/cargo/irRegistrar";
			}			
		}		
    	
    }
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id,Model model,RedirectAttributes objRedir)
			throws ParseException 
	{
		Optional<Cargo> objCargo= caService.listarId(id);
		if (objCargo== null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/cargo/listar";
		}
		else {
			model.addAttribute("cargo", objCargo);
			return "cargo";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				caService.eliminar(id);
				model.put("listaCargos", caService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaCargos", caService.listar());
		}
		return "listCargo";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCargos", caService.listar());
		return "listCargo";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Cargo cargo) 
	throws ParseException
	{
		caService.listarId(cargo.getIdCargo());
		return "listCargo";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Cargo cargo) 
			throws ParseException
	{
		List<Cargo> listaCargos;
		cargo.setNameCargo(cargo.getNameCargo());
		listaCargos = caService.buscarNombre(cargo.getNameCargo());
		
		if (listaCargos.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaCargos", listaCargos);
		return "buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("cargo", new Cargo());
		return "buscar";
	}	
}
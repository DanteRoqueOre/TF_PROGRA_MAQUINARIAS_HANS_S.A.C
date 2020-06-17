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
import pe.maquinarias.hans.spring.model.Maquinista;
import pe.maquinarias.hans.spring.service.IEspecialidadService;
import pe.maquinarias.hans.spring.service.IMaquinistaService;




@Controller
@RequestMapping("/maquinista")
public class MaquinistaController {

	@Autowired
	private IEspecialidadService eService;
	@Autowired
	private IMaquinistaService mquiService;	
	
	@RequestMapping("/bienvenido")
	public String irMaquinistaBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irMaquinista(Map<String, Object> model) {
		model.put("listaMaquinistas", mquiService.listar());
		return "listMaquinista";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("listaEspecialidades", eService.listar());
		model.addAttribute("especialidad", new Especialidad());
		model.addAttribute("maquinista", new Maquinista());
		return "maquinista";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Maquinista objMaquinista, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaEspecialidades", eService.listar());
			return "maquinista";
		}
		else {
				boolean flag = mquiService.insertar(objMaquinista);
				if (flag) {
					return "redirect:/maquinista/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/maquinista/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Maquinista objMaquinista, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/maquinista/listar";
		}
		else {
			boolean flag = mquiService.modificar(objMaquinista);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/maquinista/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/maquinista/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Maquinista> objMaquinista= mquiService.listarId(id);
		if (objMaquinista == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/maquinista/listar";
		}
		else {	
			model.addAttribute("listaEspecialidades", eService.listar());	
			if (objMaquinista.isPresent())
				objMaquinista.ifPresent(o -> model.addAttribute("maquinista", o));						
			return "maquinista";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				mquiService.eliminar(id);
				model.put("listaMaquinistas", mquiService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaMaquinistas", mquiService.listar());
		}
		return "listMaquinista";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaMaquinistas", mquiService.listar());
		return "listMaquinista";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Maquinista maquinista) 
	throws ParseException
	{
		mquiService.listarId(maquinista.getIdMaquinista());
		return "listMaquinista";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Maquinista maquinista) 
			throws ParseException
	{
		List<Maquinista> listaMaquinistas;
		maquinista.setNameMaquinista(maquinista.getNameMaquinista());
		listaMaquinistas= mquiService.buscarNombre(maquinista.getNameMaquinista());
		
		if (listaMaquinistas.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaMaquinistas", listaMaquinistas);
		return "buscarMaquinista";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("maquinista", new Maquinista());
		return "buscarMaquinista";
	}	
}
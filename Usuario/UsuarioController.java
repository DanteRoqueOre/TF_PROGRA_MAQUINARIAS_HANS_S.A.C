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

import pe.maquinarias.hans.spring.model.Usuario;
import pe.maquinarias.hans.spring.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService uService;
	
	@RequestMapping("/bienvenido")
	public String irUsuarioBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irUsuario(Map<String,Object>model) {
		model.put("listaUsuarios", uService.listar());
		return "listUsuario";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		model.addAttribute("usuario",new Usuario());
		return "usuario";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Usuario objUsuario,BindingResult binRes,Model model)
	      throws ParseException
	{
		if (binRes.hasErrors()) {
			return "usuario";
		}
		else {
				boolean flag = uService.insertar(objUsuario);
				if (flag) {
					return "redirect:/usuario/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/usuario/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Usuario objUsuario,BindingResult binRes,Model model,RedirectAttributes objRedir) 
			throws ParseException
    {
    	if (binRes.hasErrors()) {
			return "redirect:/usuario/listar";
		}
		else {
			boolean flag = uService.modificar(objUsuario);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/usuario/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/usuario/irRegistrar";
			}			
		}		
    	
    }
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id,Model model,RedirectAttributes objRedir)
			throws ParseException 
	{
		Optional<Usuario> objUsuario= uService.listarId(id);
		if (objUsuario== null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/usuario/listar";
		}
		else {
			model.addAttribute("usuario", objUsuario);
			return "usuario";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				uService.eliminar(id);
				model.put("listaUsuarios", uService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaUsuarios", uService.listar());
		}
		return "listUsuario";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaUsuarios", uService.listar());
		return "listUsuario";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Usuario usuario) 
	throws ParseException
	{
		uService.listarId(usuario.getIdUsuario());
		return "listUsuario";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Usuario usuario) 
			throws ParseException
	{
		List<Usuario> listaUsuarios;
		usuario.setNameUsuario(usuario.getNameUsuario());
		listaUsuarios = uService.buscarNombre(usuario.getNameUsuario());
		
		if (listaUsuarios.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaUsuarios", listaUsuarios);
		return "buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "buscar";
	}	
}
package tr.lkd.lyk2015.springtodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.lkd.lyk2015.springtodo.model.Todo;
import tr.lkd.lyk2015.springtodo.service.TodoService;

@Controller
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService;

	@RequestMapping("")
	public String list(Model model) {
		List<Todo> todos = todoService.getAll();
		
		model.addAttribute("todoList", todos);
		return "todoList";
	}
	
	@RequestMapping(value = "/mark", method = RequestMethod.POST)
	public String checkDone(@RequestParam("id") Long id) {
		todoService.markAsDone(id, true);
		
		return "redirect:/todo";
	}
	

	
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm(@ModelAttribute Todo todo) {
		
		return "createForm";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute Todo todo) {
		todoService.create(todo);
		
		return "redirect:/todo";
	}
	

	   @RequestMapping(value = "/edit", method = RequestMethod.GET)
	    public String editForm(@RequestParam("id") Long id, Model model){

	        Todo todo = todoService.getById(id);
	        model.addAttribute("todo", todo);


	        return "editForm";
	    }
	

	   @RequestMapping(value = "/edit", method = RequestMethod.POST)
		public String update(@ModelAttribute Todo todo) {
			todoService.update(todo);
			
			return "redirect:/todo";
		}
}

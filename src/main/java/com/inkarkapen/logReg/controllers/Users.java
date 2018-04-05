package com.inkarkapen.logReg.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inkarkapen.logReg.models.Idea;
import com.inkarkapen.logReg.models.User;
import com.inkarkapen.logReg.services.UserService;
import com.inkarkapen.logReg.services.IdeaService;
import com.inkarkapen.logReg.validator.UserValidator;

@Controller
public class Users {
	private UserService userService;
	private IdeaService ideaService;
    private UserValidator userValidator;
    public Users(UserService userService, UserValidator userValidator,IdeaService ideaService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.ideaService = ideaService;
    }
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		userValidator.validate(user,  result);
		if(result.hasErrors()){
			return "loginPage.jsp";
		}
		userService.saveWithUserRole(user);
		return "redirect:/login";
	}
	@RequestMapping("/login")
	public String login(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, please try again.");
		}
		if(logout != null) {
			model.addAttribute("LogoutMessage", "Logout Successfull!");
		}
		return "loginPage.jsp";
	}
	@RequestMapping(value = {"ideas"})
	public String home(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("currentUser", userService.findByUsername(username));
		model.addAttribute("ideas", ideaService.findAll());
		return "homePage.jsp";
	}
	@RequestMapping("/ideas/new")
	public String newIdea(@Valid @ModelAttribute("newIdea") Idea newIdea) {
		return "newIdea.jsp";
	}
	@PostMapping("/ideas/new")
	public String createIdea(@Valid @ModelAttribute("newIdea") Idea newIdea, BindingResult result, Model model,  Principal principal) {
		if(result.hasErrors()){
			model.addAttribute("error", "Length of Idea must be greater than 3 characters");
			return "newIdea.jsp";
		}
		String username = principal.getName();
		User user = userService.findByUsername(username);
		newIdea.setUser(user);
		user.getUser_ideas().add(newIdea);
		ideaService.saveIdea(newIdea);
		return "redirect:/ideas";
	}
	@RequestMapping("/like/{id}")
	public String like(Principal principal, @PathVariable("id") Long idea_id) {
		String username = principal.getName();
        User user = userService.findByUsername(username);
    		Idea idea = ideaService.findById(idea_id);
    		idea.incrementLikes();
    		user.getLiked_ideas().add(idea);
    		userService.update(user);
    		return "redirect:/ideas";
	}
	@RequestMapping("/unlike/{id}")
	public String unlike(Principal principal, @PathVariable("id") Long idea_id) {
		String username = principal.getName();
        User user = userService.findByUsername(username);
    		Idea idea = ideaService.findById(idea_id);
    		idea.decrementLikes();
    		user.getLiked_ideas().remove(idea);
    		userService.update(user);
    		return "redirect:/ideas";
	}
	@RequestMapping("/ideas/{id}")
	public String showIdea(Principal principal, @PathVariable("id") Long idea_id, Model model) {
    		model.addAttribute("idea", ideaService.findById(idea_id));
    		return "showIdea.jsp";
	}
	@RequestMapping("ideas/ascOrder")
	public String ascOrder(Model model) {
		model.addAttribute("ideas", ideaService.findAllByOrderByLikesAsc());
		return "redirect:/ideas";
	}
	@RequestMapping("ideas/descOrder")
	public String descOrder(Model model) {
		model.addAttribute("ideas", ideaService.findAllByOrderByLikesDesc());
		return "homePage.jsp";
	}
	@RequestMapping("/ideas/edit/{id}")
	public String editIdea(@PathVariable("id") Long id, Model model,Principal principal) {
		String username = principal.getName();
        User user = userService.findByUsername(username);
        Idea updateIdea = ideaService.findById(id);
        if(!user.equals(updateIdea.getUser())) {
        		return "redirect:/ideas";
        }
		model.addAttribute("updateIdea", updateIdea);
		return "editIdea.jsp";
	}
	@PostMapping("/update/{id}")
    public String updateIdea(@Valid @ModelAttribute("updateIdea") Idea idea, BindingResult result, Principal principal, @PathVariable("id") Long id,Model model) {
	    	if (result.hasErrors()) {
				model.addAttribute("error", "Length of Idea must be greater than 3 characters");
	            return "editIdea.jsp";
	        }else {
	    	Idea updateIdea = ideaService.findById(id);
	    	updateIdea.setIdea(idea.getIdea());
	    ideaService.update(updateIdea);
	    return "redirect:/ideas";
	        }
    }
	@RequestMapping("/delete/{id}")
    public String deleteIdea(Principal principal, @PathVariable("id") Long id) {
		String username = principal.getName();
        User user = userService.findByUsername(username);
        Idea idea = ideaService.findById(id);
        if(!user.equals(idea.getUser())) {
        		return "redirect:/ideas";
        }
		ideaService.delete(id);
    	return "redirect:/ideas";
    }
}

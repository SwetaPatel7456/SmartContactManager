package com.smart.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
public class HomeController {
	
	@Autowired(required = true)
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired(required = true)
	private UserRepository userRepository;
	
	@RequestMapping(value = "/home")
	public String home(Model m) {
	m.addAttribute("title", "Home-Smart Contact Manager");
	return "home";
	}
	
	@RequestMapping(value = "/about")
	public String about(Model m) {
	m.addAttribute("title", "About-Smart Contact Manager");
	return "about";
	}
   
	@RequestMapping(value = "/login")
	public String login(Model m) {
	m.addAttribute("title", "Login-Smart Contact Manager");
	return "login";
	}
	@RequestMapping(value = "/signup")
	public String signup(Model m) {
	m.addAttribute("title", "Signup-Smart Contact Manager");
	m.addAttribute("user", new User());
	return "signup"; 
	}

	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid@ModelAttribute("user") User user,BindingResult result,@RequestParam(value = "agreement" ,defaultValue = "false") boolean agreement ,Model m, HttpSession session) {
	try {
		/*(if(!agreement) {
		System.out.println("You have not agrred the terms and conditions");
		throw new Exception("You have not agrred the terms and conditions");
	    }*/
		if(result.hasErrors()) {
			System.out.println("ERROR" + result.toString());
			m.addAttribute("user", user);
			return "signup";
		}
		
	user.setRole("Role User");
	user.setEnabled(true);
	user.setImageUrl("default.png");
	
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	
	System.out.println("Agreement" + agreement);
	System.out.println("User" + user);
	User res = this.userRepository.save(user);
	m.addAttribute("user" , user);//iska profit y h ki jo data aaya h vo baps chla jyega
    session.setAttribute("message" ,new Message("Successfully Registered !!","alert-success"));
	
	return "signup";
	}
	catch(Exception e) {
      e.printStackTrace();
      m.addAttribute("user" , user);
      session.setAttribute("message" ,new Message("Something Went wrong !!" + e.getMessage(),"alert-danger"));
      return "signup";
      }


}
}

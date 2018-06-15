package com.integrador.hemoSoft.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.integrador.hemoSoft.model.User;



@Controller
public class IndexController {

	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
	    if(user != null) {
            return "redirect:/doador/lista";
        }
		return "redirect:/entrar";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/entrar")
    public String entrar() {
        return "entrar";
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "/")
    public String inicio() {
        return "inicio";
    }
	
}

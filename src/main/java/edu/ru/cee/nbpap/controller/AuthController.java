package edu.ru.cee.nbpap.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @RequestMapping(value = "api/auth/login", method = RequestMethod.POST)
    public String doLogin(@RequestParam String token, HttpServletRequest request) {
        if ("risk".equals(token)) {
        	request.getSession().setAttribute("token", token);
            return "redirect:/index.html";
        }
        return "redirect:/login.html";
    }
    
    @RequestMapping(value = "/")
    public String authView() {
    	return "redirect:/index.html";
    }

}

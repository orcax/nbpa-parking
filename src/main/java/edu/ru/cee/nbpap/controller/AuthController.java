package edu.ru.cee.nbpap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @RequestMapping(value = "api/auth/login", method = RequestMethod.POST)
    public String doLogin(@RequestParam String token) {
        if ("risk".equals(token)) {
            return "redirect:/index.html";
        }
        return "redirect:/login.html";
    }

}

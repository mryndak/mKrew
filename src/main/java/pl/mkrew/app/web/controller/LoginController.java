package pl.mkrew.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/login")
public class LoginController {

    @GetMapping
    public String login() {
        return "login";
    }
}

package com.courtmanager.webapp.lightsaccount;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LightsAccountController {
  public LightsAccountController() {

  }

  @GetMapping("/home")
  public String sayHello(Model model) {
    model.addAttribute("title", "Data Application");
    return "layout";
  }
}

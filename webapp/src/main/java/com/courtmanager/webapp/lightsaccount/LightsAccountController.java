package com.courtmanager.webapp.lightsaccount;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LightsAccountController {

  private LightsAccountRepository repository;

  public LightsAccountController() throws SQLException {
    this.repository = new LightsAccountRepository();
  }

  @GetMapping("/home")
  public String sayHello(Model model) {
    model.addAttribute("title", "Data Application");
    return "layout";
  }

  @GetMapping("/accounts")
  public String getAllLightsAccounts(Model model) throws SQLException {
    repository.selectAll();
    return "layout";
  }

  @GetMapping("/accounts/{id}")
  public String getById(@PathVariable("id") int id, Model model) throws Exception {
    LightsAccount account = repository.getById(id);
    return "layout";
  }
};

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

  @GetMapping("/")
  public String sayHello(Model model) {
    model.addAttribute("title", "Data Application");
    return "layout";
  }

  @GetMapping("/lightsaccounts")
  public String getAllLightsAccounts(Model model) throws SQLException {
    model.addAttribute("accounts", repository.selectAll());
    return "lightsAccounts :: results";
  }

  @GetMapping("/lightsaccounts/{id}")
  public String getById(@PathVariable("id") int id, Model model) throws Exception {
    LightsAccount account = repository.getById(id);
    model.addAttribute("name", account.getName());
    return "lightsAccounts :: accounts";
  }
};

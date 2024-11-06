package com.courtmanager.webapp.lightsaccount;

import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LightsAccountController {

  private LightsAccountRepository repository;

  public LightsAccountController() throws SQLException {
    this.repository = new LightsAccountRepository();
  }

  @GetMapping("/lightsaccounts")
  public String home(Model model) {
    model.addAttribute("title", "Lights Accounts");

    LocalDate today = LocalDate.now();
    LocalDate sevenDaysAgo = today.minusDays(7);
    model.addAttribute("defaultFromDate", sevenDaysAgo);
    model.addAttribute("defaultToDate", today);
    return "LightsAccounts/LightsAccounts";
  }

  @GetMapping("/lightsaccounts/table")
  public String getAllLightsAccounts(Model model) throws SQLException {
    model.addAttribute("accounts", repository.selectAll());
    return "LightsAccounts/LightsAccountsTable :: results";
  }

  @GetMapping("lightsaccounts/search")
  public String search(@RequestParam(value = "q", required = false) String q,
      @RequestParam(value = "from", required = false) String from,
      @RequestParam(value = "to", required = false) String to,
      Model model) throws SQLException {

    var filtered = repository.selectAll().stream();

    // Handle date filtering
    if (from != null && !from.isEmpty() && to != null && !to.isEmpty()) {
      LocalDate fromDate = LocalDate.parse(from);
      LocalDate toDate = LocalDate.parse(to);
      filtered = filtered.filter(s -> {
        LocalDate date = s.getDate(); // Make sure this method is correct
        return (date.isEqual(fromDate) || date.isEqual(toDate) ||
            (date.isAfter(fromDate) && date.isBefore(toDate)));
      });
    }

    // Handle search filtering
    if (q != null && !q.isEmpty()) {
      System.out.println(q);
      filtered = filtered.filter(s -> s.getName().toLowerCase().contains(q.toLowerCase())
          || s.getUser().toLowerCase().contains(q.toLowerCase()));
    }

    model.addAttribute("accounts", filtered.toList());
    return "LightsAccounts/LightsAccountsTable :: results";
  }
};

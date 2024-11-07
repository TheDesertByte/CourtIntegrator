package com.courtmanager.webapp.lightsaccount;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.courtmanager.webapp.interfaces.IService;

@Controller
public class LightsAccountController {

  private IService<LightsAccount> service;

  @Autowired
  public LightsAccountController(IService<LightsAccount> service) throws SQLException {
    this.service = service;
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
    model.addAttribute("accounts", service.getAll());
    return "LightsAccounts/LightsAccountsTable :: results";
  }

  @GetMapping("lightsaccounts/search")
  public String search(@RequestParam(value = "q", required = false) String q,
      @RequestParam(value = "from", required = false) String from,
      @RequestParam(value = "to", required = false) String to,
      Model model) throws SQLException {

    var filtered = service.getAll().stream();
    //
    // Handle date filtering
    if (from != null && !from.isEmpty() && to != null && !to.isEmpty()) {
      LocalDate localDateFrom = LocalDate.parse(from);
      LocalDate localDateTo = LocalDate.parse(to);
      LocalDateTime fromDate = localDateFrom.atStartOfDay();
      LocalDateTime toDate = localDateTo.atTime(LocalTime.MAX);
      filtered = filtered.filter(s -> {
        LocalDateTime date = s.getDate(); // Make sure this method is correct
        return (date.isEqual(fromDate) || date.isEqual(toDate) ||
            (date.isAfter(fromDate) && date.isBefore(toDate)));
      });
    }

    // Handle search filtering
    if (q != null && !q.isEmpty()) {
      filtered = filtered.filter(s -> s.getName().toLowerCase().contains(q.toLowerCase())
          || s.getUser().toLowerCase().contains(q.toLowerCase()));
    }

    model.addAttribute("accounts", filtered);
    return "LightsAccounts/LightsAccountsTable :: results";
  }
};

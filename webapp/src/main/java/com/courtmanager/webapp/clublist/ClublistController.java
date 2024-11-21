package com.courtmanager.webapp.clublist;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.courtmanager.webapp.interfaces.IService;

@Controller
public class ClublistController {
  private IService<Clublist> service;

  @Autowired
  public ClublistController(IService<Clublist> service) throws SQLException {
    this.service = service;
  }

  @GetMapping("/clublist")
  public String getAllMembers(Model model) throws SQLException {
    model.addAttribute("clublist", service.getAll());
    return "";
  }

  @GetMapping("/clublist/search")
  public String search(@RequestParam(value = "q") String q, Model model) throws SQLException {
    List<Clublist> list;

    if (q != null && !q.isEmpty()) {
      list = service.getAll().stream()
          .filter(s -> (s.getSurname() != null && s.getSurname().toLowerCase().contains(q.toLowerCase())) ||
              (s.getFirst() != null && s.getFirst().toLowerCase().contains(q.toLowerCase())) ||
              (s.getMemNo() != null && s.getMemNo().toString().contains(q))
              || (s.getFirst() + " " + s.getSurname()).toLowerCase().contains(q.toLowerCase()))
          .toList();
    } else {
      list = List.of(); // Return an empty list if no query is provided
    }

    model.addAttribute("results", list);
    return "Clublist/clublist_for_lightsaccounts";
  }
}

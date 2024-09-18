package org.example.controller;

import org.example.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientOrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping("/orderhistory")
    public String getOrderHistoryForCurrentUser(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String surname = userDetails.getUsername();
        model.addAttribute("clientSurname", surname);
        model.addAttribute("orderHistoryList", orderHistoryService.getOrderHistoryBySurname(surname));
        return "client-orderhistory";
    }
}

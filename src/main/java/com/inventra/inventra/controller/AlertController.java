package com.inventra.inventra.controller;
import com.inventra.inventra.repository.AlertRepository;
import com.inventra.inventra.service.AlertFilteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.inventra.inventra.entity.Alert;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlertController {
    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private AlertFilteringService filteringService;


    @GetMapping("/admin/alerts")
    public String viewAlerts(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String status,
            Model model) {

        model.addAttribute("alerts",
                filteringService.getFilteredAlerts(type, severity, status));
        long activeCount = alertRepository.countByStatus("ACTIVE");
        model.addAttribute("activeAlertCount", activeCount);

        return "alerts";
    }
    @GetMapping("/admin/alerts/resolve/{id}")
    public String resolveAlert(@PathVariable Long id) {

        Alert alert = alertRepository.findById(id).orElse(null);

        if(alert != null) {
            alert.setStatus("RESOLVED");
            alertRepository.save(alert);
        }

        return "redirect:/admin/alerts";
    }
}
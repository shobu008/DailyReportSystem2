package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("/")
public class DailyReportSystemController {
    private final ReportService service;

    public DailyReportSystemController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getIndex(Model model) {

        model.addAttribute("reportlist", service.getReportList());
        // index.htmlに画面遷移
        return "index";
    }

}

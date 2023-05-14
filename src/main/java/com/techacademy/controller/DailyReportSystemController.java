package com.techacademy.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
    public String getIndex(Model model,@AuthenticationPrincipal UserDetail userDetail) {
        model.addAttribute("reportlist", service.getMyReportList(userDetail.getEmployee()));



        // index.htmlに画面遷移
        return "index";
    }


}

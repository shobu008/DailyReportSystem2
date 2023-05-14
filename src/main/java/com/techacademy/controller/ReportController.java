package com.techacademy.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;


@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/rlist")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("reportlist", service.getReportList());
        // user/list.htmlに画面遷移
        return "report/rlist";
    }

    @GetMapping(value = {"/repo","/repo/{id}"})
    public String getRepo(@PathVariable("id") Integer report_id, Model model) {
        // 詳細画面に遷移
        model.addAttribute("report", service.getReport(report_id));
        return "report/repo";
    }

    @GetMapping("/rregister")
    public String getRegister(@ModelAttribute Report report,Model model,@AuthenticationPrincipal UserDetail userDetail) {
        model.addAttribute("loginUser", userDetail.getEmployee());
        System.out.println(userDetail.getEmployee().getName());
        // User登録画面に遷移
        return "report/rregister";
    }

    /** User登録処理 */
    @PostMapping("/rregister")
    public String postRegister(Report report,Model model,@AuthenticationPrincipal UserDetail userDetail) {
        model.addAttribute("loginUser", userDetail.getEmployee());
        // User登録
        report.setCreatedAt(LocalDateTime.now());
        report.setUpdatedAt(LocalDateTime.now());
        report.setEmployee(userDetail.getEmployee());

        service.saveReport(report);
        // 一覧画面にリダイレクト
        return "redirect:/report/rlist";
    }

    @GetMapping("/rupdate/{id}")
    public String getReport(@PathVariable("id") Integer id, Model model,@AuthenticationPrincipal UserDetail userDetail) {
        // Modelに登録
        model.addAttribute("loginUser", userDetail.getEmployee());
        System.out.println(userDetail.getEmployee().getName());
        model.addAttribute("report", service.getReport(id));

        // User更新画面に遷移
        return "report/rupdate";
    }

    /** User更新処理 */
    @PostMapping("/rupdate/{id}")
    public String postReport(@PathVariable("id") Integer id,Report report,Model model,@AuthenticationPrincipal UserDetail userDetail) {
        // User登録

        model.addAttribute("loginUser", userDetail.getEmployee());

        Report upRepo = service.getReport(id);

        upRepo.setReportDate(report.getReportDate());

        upRepo.setTitle(report.getTitle());

        upRepo.setContent(report.getContent());

        upRepo.setUpdatedAt(LocalDateTime.now());

        service.saveReport(upRepo);


        // 一覧画面にリダイレクト
        return "redirect:/report/rlist";
    }
}
package com.techacademy.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;


@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // ----- 一覧画面 -----
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("employeelist", service.getEmployeeList());
        // /list.htmlに画面遷移
        return "employee/list";
    }

    @GetMapping(value = {"/emp","/emp/{id}"})
    public String getEmp(@PathVariable("id") Integer employee_id, Model model) {
        // 詳細画面に遷移
        model.addAttribute("employee", service.getEmployee(employee_id));
        return "employee/emp";
    }

    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        // User登録画面に遷移
        return "employee/register";
    }

    /** User登録処理 */
    @PostMapping("/register")
    public String postRegister(Employee employee) {
        // User登録
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        employee.setDelete_flag(0);
        employee.getAuthentication().setEmployee(employee);
        employee.getAuthentication().setPassword(passwordEncoder.encode(employee.getAuthentication().getPassword()));

        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    @GetMapping("/update/{id}")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));

        // User更新画面に遷移
        return "employee/update";
    }

    /** User更新処理 */
    @PostMapping("/update/{id}")

    public String postEmployee(@PathVariable("id") Integer id,Employee employee) {
        // User登録

        Employee upEm = service.getEmployee(id);

        upEm.setName(employee.getName());

        upEm.getAuthentication().setPassword(passwordEncoder.encode(employee.getAuthentication().getPassword()));

        upEm.getAuthentication().setRole(employee.getAuthentication().getRole());

        upEm.setUpdatedAt(LocalDateTime.now());



        service.saveEmployee(upEm);


        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id,Employee employee) {
        // User登録

        Employee upEm = service.getEmployee(id);

        upEm.setDelete_flag(1);

        service.saveEmployee(upEm);


        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

}

package com.techacademy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;


@Service
public class ReportService {
    private final ReportRepository reportrepository;

    public ReportService(ReportRepository repository) {
        this.reportrepository = repository;
    }

    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return reportrepository.findAll();
    }

    public Report getReport(Integer id) {
        return reportrepository.findById(id).get();
    }

    @Transactional
    public Report saveReport(Report report) {
        return reportrepository.save(report);
    }


}

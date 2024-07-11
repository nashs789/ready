package com.example.ready.controller;

import com.example.ready.Service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {

    public final ExcelService excelService;

    @PostMapping("")
    public void downloadBasicExcelFile() throws IOException {
        excelService.downloadBasicExcelFile();
    }
}

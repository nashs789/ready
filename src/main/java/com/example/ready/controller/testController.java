package com.example.ready.controller;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class testController {

    public final ChromeDriver chromeDriver;

    @GetMapping("/")
    public void test() {
        chromeDriver.get("https://kream.co.kr/");

        System.out.println(chromeDriver.getTitle());
    }
}

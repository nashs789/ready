package com.example.ready.controller;

import com.example.ready.entity.Algorithm;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class testController {

    public final ChromeDriver chromeDriver;

    @GetMapping("/")
    public void test() {
        chromeDriver.get("https://www.acmicpc.net/problem/tags");

        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        List<WebElement> rows = chromeDriver.findElements(By.cssSelector(".table-responsive tbody tr"));

        rows.forEach(row -> {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            WebElement firstTd = tds.get(0);
            WebElement secondTd = tds.get(1);
            String url = firstTd.findElement(By.tagName("a")).getAttribute("href");

            Algorithm al = new Algorithm(firstTd.getText(), secondTd.getText(), url);
            System.out.println(al);
        });
    }
}

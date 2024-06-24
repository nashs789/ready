package com.example.ready.controller;

import com.example.ready.entity.Algorithm;
import com.example.ready.entity.Problem;
import com.example.ready.enums.Level;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class testController {

    public final ChromeDriver chromeDriver;

    @Value("${baekjoon.id}")
    public String baekjoonId;

    @Value("${baekjoon.pw}")
    public String baekjoonPw;

    @GetMapping("/")
    public void test() throws InterruptedException {
        chromeDriver.get("https://www.acmicpc.net/login");

        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        // 로그인
        WebElement loginForm = chromeDriver.findElement(By.id("login_form"));

        loginForm.findElement(By.name("login_user_id")).sendKeys(baekjoonId);
        loginForm.findElement(By.name("login_password")).sendKeys(baekjoonPw);
        loginForm.findElement(By.id("submit_button")).click();

        Thread.sleep(3000);

        List<Algorithm> algorithms = getAlgorithms();

        chromeDriver.get(algorithms.get(0).getUrl());  // 알고리즘: 수학 (실습은 하나만 해도 될 것 같음 page: 63개)
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        List<String> pageUrls = getPageUrls();

        for(int i = 0; i < 3; i++) {  // 1 ~ 3 page 까지만
            String url = pageUrls.get(i);

            chromeDriver.get(url);
            chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement problemSet = chromeDriver.findElement(By.id("problemset"));
            List<WebElement> tbody_tr = problemSet.findElements(By.cssSelector("tbody tr"));

            tbody_tr.forEach(tr -> {
                List<WebElement> tds = tr.findElements(By.tagName("td"));

                Long proNo = Long.parseLong(tds.get(0).getText());
                String title = tds.get(1).getText();
                Level level = Level.getLevelFromMap(
                        Integer.parseInt(tds.get(1).findElement(By.tagName("img")).getAttribute("src").replaceAll(".*?/(\\-?\\d+)\\.svg", "$1"))
                );
                Long solved = Long.parseLong(tds.get(3).getText());
                Long tryCnt = Long.parseLong(tds.get(4).getText());
                String rate = tds.get(5).getText();

                Problem problem = new Problem(proNo, title, level, solved, tryCnt, rate);
                System.out.println(problem);
            });
        }
    }

    public List<String> getPageUrls() {
        List<String> pages = new ArrayList<>();
        List<WebElement> pagination = chromeDriver.findElements(By.cssSelector(".pagination li"));

        pagination.forEach(e -> {
            pages.add(e.findElement(By.tagName("a")).getAttribute("href"));
        });

        return pages;
    }

    // 알고리즘 분류: 한국, 영어, url 데이터 수집
    public List<Algorithm> getAlgorithms() {
        chromeDriver.get("https://www.acmicpc.net/problem/tags");

        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        List<WebElement> rows = chromeDriver.findElements(By.cssSelector(".table-responsive tbody tr"));
        List<Algorithm> algorithms = new ArrayList<>();

        rows.forEach(row -> {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            WebElement firstTd = tds.get(0);
            WebElement secondTd = tds.get(1);
            String url = firstTd.findElement(By.tagName("a")).getAttribute("href");

            Algorithm al = new Algorithm(firstTd.getText(), secondTd.getText(), url);
            algorithms.add(al);
        });

        return algorithms;
    }
}

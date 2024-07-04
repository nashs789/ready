package com.example.ready.Service;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BooksService {

    public final ChromeDriver chromeDriver;

    public void getBooks() {
        chromeDriver.get("https://books.toscrape.com/");
        waiting();

        WebElement side_categories = chromeDriver.findElement(By.className("side_categories"));
        String[] categories = side_categories.getText().split("\n");  // 카테고리 text 배열

        List<String> categoryUrls = side_categories.findElement(By.cssSelector("ul ul"))
                                                   .findElements(By.tagName("a"))
                                                   .stream()
                                                   .map(e -> e.getAttribute("href"))
                                                   .collect(Collectors.toList());

        for(String url : categoryUrls) {
            chromeDriver.get(url);  // 페이지 이동
            waiting();  // 페이지 렌더링 대기

            String currentCategory = chromeDriver.findElement(By.className("page-header"))
                                                 .getText();

            if(currentCategory.startsWith("Sequential Art")) {  // 4번째 카테고리 종료 하드코딩
                break;
            }

            List<WebElement> booksInfo = chromeDriver.findElement(By.tagName("section"))
                                                     .findElement(By.tagName("ol"))
                                                     .findElements(By.tagName("li"));

            booksInfo.stream().forEach(e -> {
                System.out.println(e.getText());
            });
        }
    }

    public void waiting() {
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }
}

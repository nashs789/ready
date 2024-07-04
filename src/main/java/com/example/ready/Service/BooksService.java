package com.example.ready.Service;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class BooksService {

    public final ChromeDriver chromeDriver;

    public void getBooks() {
        chromeDriver.get("https://books.toscrape.com/");
        chromeDriver.manage()
                    .timeouts()
                    .implicitlyWait(Duration.ofMillis(500));

        WebElement side_categories = chromeDriver.findElement(By.className("side_categories"));
        System.out.println(side_categories.getText());
        String[] categories = side_categories.getText().split("\n");
    }
}

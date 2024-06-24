package com.example.ready.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {

    @Value("${webdriver.driver.chrome}")
    private String chromeDriverPath;

    @Bean
    public ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        return options;
    }

    @Bean
    public ChromeDriver chromeDriver(ChromeOptions chromeOptions) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        return new ChromeDriver(chromeOptions);
    }
}

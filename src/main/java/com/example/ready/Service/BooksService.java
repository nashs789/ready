package com.example.ready.Service;

import com.example.ready.entity.Book;
import com.example.ready.enums.BookCategory;
import com.example.ready.enums.Stars;
import com.example.ready.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BooksService {

    public final ChromeDriver chromeDriver;
    public final BookRepository bookRepository;

    public void insertBooks() {
        chromeDriver.get("https://books.toscrape.com/");
        waiting();

        WebElement side_categories = chromeDriver.findElement(By.className("side_categories"));
        String[] categories = side_categories.getText().split("\n");  // 카테고리 text 배열

        List<String> categoryUrls = side_categories.findElement(By.cssSelector("ul ul"))
                                                   .findElements(By.tagName("a"))
                                                   .stream()
                                                   .map(e -> e.getAttribute("href"))
                                                   .collect(Collectors.toList());

        Map<BookCategory, List<String>> allBooksDetailUrls = new HashMap<>();

        for(String url : categoryUrls) {
            chromeDriver.get(url);  // 페이지 이동
            waiting();  // 페이지 렌더링 대기

            String currentCategory = chromeDriver.findElement(By.className("page-header"))
                                                 .getText();
            allBooksDetailUrls.put(BookCategory.getBookCategoryEnum(currentCategory), new ArrayList<>());

            if(currentCategory.startsWith("Sequential Art")) {  // 4번째 카테고리 종료 하드코딩
                break;
            }

            List<WebElement> detailUrls = chromeDriver.findElement(By.tagName("section")).findElements(By.className("image_container"));

            // 책 상세정보 url 저장
            for(WebElement detailUrl : detailUrls) {
                String bookDetailUrl = detailUrl.findElement(By.tagName("a")).getAttribute("href");

                allBooksDetailUrls.get(BookCategory.getBookCategoryEnum(currentCategory))
                                  .add(bookDetailUrl);
            }

            // 다음 페이지가 존재 하면 탐색 url 에 추가
            try {
                WebElement next = chromeDriver.findElement(By.className("next"));
                String nextPage = next.findElement(By.tagName("a")).getAttribute("href");

                //categoryUrls.add(nextPage);
            } catch (Exception e) {

            }
        }

        // 책 상세정보 데이터 수집
        for(BookCategory bookCategory : allBooksDetailUrls.keySet()) {
            for(String url: allBooksDetailUrls.get(bookCategory)) {
                chromeDriver.get(url);  // 페이지 이동
                waiting();  // 페이지 렌더링 대기

                WebElement product_main = chromeDriver.findElement(By.className("product_main"));

                String title = product_main.findElement(By.cssSelector("h1")).getText();
                Stars stars = Stars.getStars(product_main.findElement(By.className("star-rating")).getAttribute("class").split(" ")[1]);
                List<WebElement> tds = chromeDriver.findElement(By.cssSelector("table")).findElements(By.cssSelector("td"));

                String upc = tds.get(0).getText();
                String price = tds.get(2).getText();
                String tax = tds.get(4).getText();
                String reviews = tds.get(6).getText();

                Book book = Book.builder()
                                .title(title)
                                .stars(stars)
                                .category(bookCategory)
                                .upc(upc)
                                .price(price)
                                .tax(tax)
                                .available(true)
                                .reviews(reviews)
                                .build();

                bookRepository.save(book);
            }
        }
    }

    public void waiting() {
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }
}
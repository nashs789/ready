package com.example.ready.Service;

import com.example.ready.entity.Book;
import com.example.ready.enums.BookCategory;
import com.example.ready.repository.BookRepository;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelService {

    public final BookRepository bookRepository;

    public void downloadBasicExcelFile() throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet1 = wb.createSheet("Hello World!!!");

        try(OutputStream fileOut = new FileOutputStream("book.xls")) {
            wb.write(fileOut);
        } catch (Exception e) {

        } finally {
            wb.close();
        }
    }

    public void downloadAllBooksExcelFile() throws IOException {
        Map<BookCategory, List<Book>> books = bookRepository.findAll()
                                                            .stream()
                                                            .collect(Collectors.groupingBy(Book::getCategory));
        Workbook wb = new HSSFWorkbook();
        Map<BookCategory, Sheet> sheetMap = getBookCategorySheetMap(wb);

        for(BookCategory key : books.keySet()) {
            Sheet sheet = sheetMap.get(key);
            List<Book> bookForCategory = books.get(key);

            for(int idx = 0; idx < bookForCategory.size(); idx++) {
                Row row = sheet.createRow(idx);

                row.createCell(0).setCellValue(bookForCategory.get(idx).getId());
                row.createCell(1).setCellValue(bookForCategory.get(idx).getTitle());
                row.createCell(2).setCellValue(bookForCategory.get(idx).getStars().name());
                row.createCell(3).setCellValue(bookForCategory.get(idx).getPrice());
                row.createCell(4).setCellValue(bookForCategory.get(idx).getTax());
                row.createCell(5).setCellValue(bookForCategory.get(idx).getReviews());
            }
        }

        try(OutputStream fileOut = new FileOutputStream("book.xls")) {
            wb.write(fileOut);
        } catch (Exception e) {

        } finally {
            wb.close();
        }
    }

    private static Map<BookCategory, Sheet> getBookCategorySheetMap(Workbook wb) {
        Map<BookCategory, Sheet> sheetMap = new HashMap<>();

        for(BookCategory category : BookCategory.values()) {
            sheetMap.put(category, wb.createSheet(category.name()));
        }

        return sheetMap;
    }
}

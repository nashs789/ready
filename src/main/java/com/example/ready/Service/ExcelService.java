package com.example.ready.Service;

import com.example.ready.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
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
}

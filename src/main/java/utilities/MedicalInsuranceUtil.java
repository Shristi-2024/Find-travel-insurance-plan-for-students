package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class MedicalInsuranceUtil {
    public static void writeMedicalOptionsToExcel(List<String> options) {
        if (options == null || options.isEmpty()) {
            System.out.println("No options available to write to Excel.");
            return;
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Medical Insurance Options");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Medical Menu");
            headerRow.createCell(1).setCellValue("Status");

            // Write data rows
            int rowCount = 1;
            for (String option : options) {
                Row row = sheet.createRow(rowCount++);

                // First column: Menu item
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(option);

                // Second column: Status
                Cell cell2 = row.createCell(1);
                cell2.setCellValue("PASS"); // Customize if needed
            }

            // Auto-size columns
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            // Create directory if not exists
            File dir = new File("./src/test/resources/testdata");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Write to file
            try (FileOutputStream out = new FileOutputStream("./src/test/resources/testdata/MedicalInsuranceOptions.xlsx")) {
                workbook.write(out);
            }

            System.out.println("Excel file saved: ./src/test/resources/testdata/MedicalInsuranceOptions.xlsx");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package utilities;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriterUtil {

	private static Workbook workbook = new XSSFWorkbook();
	private static Sheet sheet = workbook.createSheet("Test Results");
	private static int rowCount = 0;
	public static void logResult(String stepDescription, String status) {
		Row row = sheet.createRow(rowCount++);
		row.createCell(0).setCellValue(stepDescription);
		row.createCell(1).setCellValue(status);
	}

	public static void saveExcel(String filePath) {
		try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
			workbook.write(fileOut);
			System.out.println("Excel file saved: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
package utils;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private Workbook workbook;

    public ExcelUtils(String excelPath) throws IOException {
        FileInputStream fis = new FileInputStream(excelPath);
        workbook = new XSSFWorkbook(fis);
    }

    // Reads the sheet and returns a 2D Object array (skip header)
    public Object[][] getSheetData(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount - 1][colCount]; // skip header row

        for (int i = 1; i < rowCount; i++) { // start from 1 to skip header
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    data[i - 1][j] = cell.getStringCellValue();
                } else {
                    data[i - 1][j] = "";
                }
            }
        }
        return data;
    }
}

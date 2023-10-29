package test.java;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataReader {

    public List<List<Object>> readExcelFile(String excelFilePath) {
        List<List<Object>> dataList = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath))) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) { // Eina per visas eilutes
                List<Object> rowData = new ArrayList<>();
                for (Cell cell : row) { // Eina per visus stulpelius eilutėje
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                rowData.add(cell.getDateCellValue());
                            } else {
                                rowData.add(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            rowData.add(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            rowData.add(cell.getCellFormula());
                            break;
                        case BLANK:
                            rowData.add("");
                            break;
                        default:
                            rowData.add(" ");
                    }
                }
                dataList.add(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
}

package com.restassured.api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.File;
import java.util.*;

public class ExcelUtils {

    private Workbook workbook;

    // Constructor to load Excel file
    public ExcelUtils(String excelFilePath) {
        try (FileInputStream fis = new FileInputStream(new File(excelFilePath))) {
            this.workbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get row data as Map<ColumnName, Value> based on TC_Id
    public Map<String, String> getRowDataByTC(String sheetName, String tcId) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new RuntimeException("Sheet " + sheetName + " not found!");
        }

        // Read headers
        Row headerRow = sheet.getRow(0);
        List<String> headers = new ArrayList<>();
        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue().trim());
        }

        // Find row with matching TC_Id
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Cell tcCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK); // assuming TC_Id is in 1st column
            String currentTcId = getCellValueAsString(tcCell);

            if (currentTcId.equalsIgnoreCase(tcId)) {
                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData.put(headers.get(j), getCellValueAsString(cell));
                }
                return rowData; // return once match is found
            }
        }

        throw new RuntimeException("TC_Id " + tcId + " not found in sheet " + sheetName);
    }

    // Convert any cell type to String
    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    // Close workbook
    public void close() {
        try {
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
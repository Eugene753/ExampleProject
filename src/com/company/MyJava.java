package com.company;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class MyJava {


    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\imark\\IdeaProjects\\ExampleProject\\covidcases\\Book1.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        /*String cellValue=sheet.getRow(0).getCell(0).getStringCellValue();
        System.out.println(cellValue );*/

        int rowCount = sheet.getPhysicalNumberOfRows();

        for (int i = 0; i < rowCount; i++) {
            XSSFRow row = sheet.getRow(i);

            int cellCount = row.getPhysicalNumberOfCells();
            for (int j = 0; j < cellCount; j++) {
                XSSFCell cell = row.getCell(j);
                String cellValue=getCellValue(cell);
                System.out.print(cellValue+"    ");
            }
            System.out.println();

        }

        workbook.close();
        inputStream.close();
    }

    public static String getCellValue(XSSFCell cell) {
        switch (cell.getCellType()) {
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case STRING:
                return String.valueOf(cell.getStringCellValue());
            default:
                return String.valueOf(cell.getStringCellValue());
        }
    }

}


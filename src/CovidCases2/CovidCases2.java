package CovidCases2;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

public class CovidCases2 {

    public static void main(String[] args) throws IOException {

        String path="C:\\Users\\imark\\IdeaProjects\\ExampleProject\\covidcases\\Book1.xlsx";
        FileInputStream fileInputStream=new FileInputStream(path);
        XSSFWorkbook covid=new XSSFWorkbook(fileInputStream);
        Sheet sheet1=covid.getSheet("Sheet1");
        DataFormatter dataFormatter=new DataFormatter();

        /*Row row=sheet1.getRow(1);
        CreationHelper creationHelper=covid.getCreationHelper();
        CellStyle cellStyle=covid.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("mm/dd/yyyy"));
        cell.setCellStyle(cellStyle);
        System.out.println(cell);*/



        CreationHelper creationHelper=covid.getCreationHelper();
        CellStyle cellStyle=covid.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("mm/dd/yyyy"));

        int rowCount=sheet1.getPhysicalNumberOfRows();
        Collection<Cell> countries=new ArrayList<>();

        for(int i=0;i<rowCount;i++){
            Row row=sheet1.getRow(i);
            int cellCount=row.getPhysicalNumberOfCells();

                for (int j = 0; j < cellCount; j++) {
                    Cell cell= row.getCell(j);
                    cell.setCellStyle(cellStyle);
                    System.out.println(cell);

                    }
            }
            System.out.println();
        }
}


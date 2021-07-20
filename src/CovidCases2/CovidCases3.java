package CovidCases2;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class CovidCases3 {

    public static void main(String[] args) throws IOException {

        String path="C:\\Users\\imark\\IdeaProjects\\ExampleProject\\covidcases\\full_data.xlsx";
        FileInputStream fileInputStream=new FileInputStream(path);
        XSSFWorkbook covid=new XSSFWorkbook(fileInputStream);
        Sheet sheet=covid.getSheet("full_data");

        int rowCount=sheet.getPhysicalNumberOfRows();

        TreeSet<Cell> countires=new TreeSet<>();

        for(int i=1;i<rowCount;i++){
            Row row= sheet.getRow(i);

            int cellCount=row.getPhysicalNumberOfCells();
            for(int j=1;j<cellCount;j++){
            Cell cell= row.getCell(j);
            countires.add(cell);
            }
        }
        for(Cell country:countires){
            System.out.println(country);
        }

    }
}

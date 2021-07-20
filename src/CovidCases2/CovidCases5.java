package CovidCases2;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class CovidCases5 {

    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\imark\\IdeaProjects\\ExampleProject\\covidcases\\full_data.xlsx";
        FileInputStream fileInputStream = new FileInputStream(path);
        XSSFWorkbook covid = new XSSFWorkbook(fileInputStream);
        Sheet sheet = covid.getSheet("full_data");
        int noOfRows = sheet.getPhysicalNumberOfRows();

        List<Person> list = new ArrayList<Person>();
        Map<String, Long> map = new TreeMap<>();
        Map<Long, Double> map2 = new TreeMap<>();
        String country = "";
        long cases=0;
        long deaths=0;
        double deathToCaseRation=0;
        for (int i = 1; i < noOfRows; i++) {
            Row row = sheet.getRow(i);
            try {
                country = row.getCell(1).toString();
                cases = (long) row.getCell(4).getNumericCellValue();
                deaths = (long) row.getCell(5).getNumericCellValue();
                deathToCaseRation=(double)deaths/cases*100;
                Person person=new Person(country,cases,deaths,deathToCaseRation);
                list.add(person);
                if (country.contains("World")) {
                    map.remove(country);
                }

            } catch (NullPointerException e) {
                e.printStackTrace();

            }

        }

    }
}

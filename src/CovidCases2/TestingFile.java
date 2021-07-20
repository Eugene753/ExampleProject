package CovidCases2;

import CovidCase.CovidCase;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class TestingFile {


    public static void main(String[] args) throws IOException {

        List <CovidCase>caseList;

        String path = "C:\\Users\\imark\\IdeaProjects\\ExampleProject\\covidcases\\full_data.xlsx";
        FileInputStream fileInputStream = new FileInputStream(path);
        XSSFWorkbook covid = new XSSFWorkbook(fileInputStream);
        Sheet sheet = covid.getSheet("full_data");

        int noOfRows = sheet.getPhysicalNumberOfRows();
        caseList = new ArrayList<CovidCase>();
        Map<String, Long> map = new TreeMap<>();
        Map<String, Long> map2 = new TreeMap<>();
        String country = "";
        for (int i = 1; i < noOfRows; i++) {
            Row row = sheet.getRow(i);
            try {
                country = row.getCell(1).toString();
                long cases = (long) row.getCell(4).getNumericCellValue();
                long deaths = (long) row.getCell(5).getNumericCellValue();
                map.put(country, cases);
                map2.put(country, deaths);
                if (country.contains("World")) {
                    map.remove(country);
                }

            } catch (NullPointerException e) {

            }
        }

        Set<String> set2 = map.keySet();

        double ration = 0;
        for (String str : set2) {
            ration = (double) map2.get(str) / map.get(str) * 100;
            double newRatio = 0;
            try {
                BigDecimal bd = new BigDecimal(ration).setScale(2, RoundingMode.HALF_UP);
                newRatio = bd.doubleValue();
            } catch (NumberFormatException e) {

            }
            CovidCase covidCase = new CovidCase(str, map.get(str), map2.get(str), newRatio);

            caseList.add(covidCase);
        }




    }
}


package qtriptest.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.apache.poi.ss.usermodel.DataFormatter;


public class DP {

    @DataProvider (name = "data-provider")
    public Object[][] excelDataProvider() throws IOException {
      DataFormatter formatter= new DataFormatter();
      try {
        File flObj = new File("/home/crio-user/workspace/anchalsingh2914-ME_QTRIP_QA_V2/app/src/test/resources/DatasetsforQTrip.xlsx");
        FileInputStream fileInputObj = new FileInputStream(flObj);
        XSSFWorkbook wbObj = new XSSFWorkbook(fileInputObj);
        XSSFSheet sheet = wbObj.getSheet("TestCase01");
        int TC1_rowCount = sheet.getLastRowNum();
        String TC1_username1 = ""; String TC1_username2 = "";String TC1_username3 = "";
        String TC1_password1 = "";String TC1_password2 = "";String TC1_password3 = ""; 
        for (int i = 1; i <=TC1_rowCount; i++) {
          Row row = sheet.getRow(i);
          Cell cell = row.getCell(1);
          String testData = formatter.formatCellValue(cell);

          TC1_username1 = "testuser@gmail.com";
          TC1_password1 = "abc@123";

          if (testData.equalsIgnoreCase("TESTUSER@gmail.com")) {
            TC1_username2 = testData;
            TC1_password2 = formatter.formatCellValue(row.getCell(2));
          }

          else if (testData.equalsIgnoreCase("123_USER_low@gmail.com")) {
            TC1_username3 = testData;
            TC1_password3 = formatter.formatCellValue(row.getCell(2));
          }
      }
      return new Object[][] {{TC1_username1, TC1_password1}, {TC1_username2, TC1_password2},
              {TC1_username3, TC1_password3}};
} catch(Exception e){
    e.printStackTrace();
  }
  return null;
}
}


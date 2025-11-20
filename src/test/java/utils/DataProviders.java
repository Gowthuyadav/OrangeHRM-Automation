package utils;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    private static String path = "./testdata/OrangeHRM_LoginData.xlsx";

    @DataProvider(name = "logindata")
    public static Object[][] getLoginData() throws IOException {
        ExcelUtils excel = new ExcelUtils(path);
        return excel.getSheetData("logindata");
    }

    @DataProvider(name = "addEmployeeData")
    public static Object[][] getAddEmployeeData() throws IOException {
        ExcelUtils excel = new ExcelUtils(path);
        return excel.getSheetData("addEmployeeData");
    }

    @DataProvider(name = "searchEmployeeData")
    public static Object[][] getSearchEmployeeData() throws IOException {
        ExcelUtils excel = new ExcelUtils(path);
        return excel.getSheetData("searchEmployeeData");
    }

    @DataProvider(name = "editEmployeeData")
    public static Object[][] getEditEmployeeData() throws IOException {
        ExcelUtils excel = new ExcelUtils(path);
        return excel.getSheetData("editEmployeeData");
    }

    @DataProvider(name = "deleteEmployeeData")
    public static Object[][] getDeleteEmployeeData() throws IOException {
        ExcelUtils excel = new ExcelUtils(path);
        return excel.getSheetData("deleteEmployeeData");
    }
}

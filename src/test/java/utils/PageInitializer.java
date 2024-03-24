package utils;

import org.apache.commons.math3.analysis.function.Add;
import pages.*;

public class PageInitializer {

    public static LoginPage login;
    public static DashboardPage dashboardPage;
    public static AddEmployeePage addEmployeePage;
    public static EmployeeSearchPage employeeSearchPage;
public static AddLanguagePage addLanguagePage;
public static  PictureUploadPage pictureUploadPage;

    public static void initializePageObjects(){
        login = new LoginPage();
        dashboardPage = new DashboardPage();
        addEmployeePage = new AddEmployeePage();
        employeeSearchPage = new EmployeeSearchPage();
        addLanguagePage = new AddLanguagePage();
      pictureUploadPage=  new PictureUploadPage();
    }

}


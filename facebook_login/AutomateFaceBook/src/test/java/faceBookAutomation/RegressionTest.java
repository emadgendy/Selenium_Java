package faceBookAutomation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.custom.library.BasePage;
import com.custom.library.ExcelManager;

public class RegressionTest extends BasePage {

	@DataProvider(name = "login_info")
	public static Object[][] login() {
		ExcelManager read = new ExcelManager("src/test/resources/login_info.xls");
		Object[][] loginData = read.getExcelData("Sheet1");
		Object[][] credentials = new Object[loginData.length][2];
		for (int i = 0; i < loginData.length; i++) {
			credentials[i][0] = loginData[i][0];
			credentials[i][1] = loginData[i][1];
		}
		return credentials;
	}

	public ObjectMap objmap;
	HomePage HomePage = new HomePage();

	@Test(dataProvider = "login_info")
	public void faceBookLogin(String UserName, String UserPassword) throws Exception {
		HomePage.goTo_facebook_HomePage();
		HomePage.Enter_userName(UserName);
		HomePage.Enter_userPassword(UserPassword);
		HomePage.Click_loginButton();
	}

}

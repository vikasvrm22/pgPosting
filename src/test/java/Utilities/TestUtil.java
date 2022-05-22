package Utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;
import org.testng.annotations.DataProvider;
import pageActions.baseClass;

public class TestUtil extends baseClass {
	public static String screenshotPath;
	public static String Filename;

	@DataProvider(name = "dp") // name of data provider
	public static Object[][] getData(Method m) {
		System.out.println(m.getName());
		String sheetName = m.getName();// this can pick the test name so please
										// keep sheetname same as test name.
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][cols];
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;

	}

	// data provider for hashtable
	@DataProvider(name = "dph") // name of data provider
	public static Object[][] getDataHashtable(Method m) {
		System.out.println(m.getName());
		String sheetName = m.getName();// this can pick the test name so please
										// keep sheetname same as test name.
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][1];
		Hashtable<String, String> table = null;
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
				// data[rowNum - 2][colNum] = excel.getCellData(sheetName,
				// colNum, rowNum);
			}
		}
		return data;
	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName = "testSuite";
		int rows = excel.getRowCount(sheetName);
		for (int rNum = 2; rNum <= rows; rNum++) {
			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			if (testCase.equalsIgnoreCase(testName)) {
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				if (runmode.equalsIgnoreCase("Y")) {

					return true;
				} else {
					return false;
				}
			}

		}
		return true;
	}

	
	/*public static void logResponseInReport(Response response) {
		testLevelLog.get()
				.info("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Click Here To See API Response"
						+ "</font>" + "</b >" + "</summary>" + response.asString() + "</details>" + " \n");

	}*/

}

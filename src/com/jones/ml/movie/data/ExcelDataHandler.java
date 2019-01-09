/**
 * 
 */
package com.jones.ml.movie.data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Callum Jones, 2019
 *
 */
public class ExcelDataHandler {

	
	private String filename = "trainingData.xls";
	
	public class JExcelHelper {
		 
	    public Map<Integer, List<String>> readJExcel(String fileLocation) 
	      throws IOException, BiffException {
	  
	        Map<Integer, List<String>> data = new HashMap<>();
	 
	        Workbook workbook = Workbook.getWorkbook(new File(fileLocation));
	        Sheet sheet = workbook.getSheet(0);
	        int rows = sheet.getRows();
	        int columns = sheet.getColumns();
	 
	        for (int i = 0; i < rows; i++) {
	            data.put(i, new ArrayList<String>());
	            for (int j = 0; j < columns; j++) {
	                data.get(i)
	                  .add(sheet.getCell(j, i)
	                  .getContents());
	            }
	        }
	        return data;
	    }
	}
	
}

/**
 * 
 */
package com.jones.ml.movie.data;
import java.io.*;
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
	public int test = 1;
	
	public ExcelDataHandler(String fileName) {
		this.filename = fileName;
	}
	
	public void readCSV() {
		
		String line = null;
		List<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		List<String> lineData = new ArrayList<String>();
		
		try {
			
			FileReader fileReader = new FileReader(filename);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				
				lineData = new ArrayList<String>();
				for (String dataEntry: line.split(",")) {
					lineData.add(dataEntry);
				}
				data.add((ArrayList<String>) lineData);
				
			}
			
			bufferedReader.close();
			
		}
		catch(FileNotFoundException ex) {
			System.out.println("File not Found!");
			// Cast File Not Found Error Here
		}
		catch(IOException ex) {
			// Cast Error for IO - Read Error / File in Use
		}
		
		System.out.println(data.get(1).get(2));
		
	}
	
}

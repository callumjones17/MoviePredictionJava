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
	private int lineLen = -1;
	private int numLines = -1;
	

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
			numLines = 0;
			
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				numLines++;
				
				lineData = new ArrayList<String>();
				lineLen = 0;
				for (String dataEntry: line.split(",")) {
					lineData.add(dataEntry);
					lineLen++;
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
	
	public int getLineLen() {
		return lineLen;
	}

	public int getNumLines() {
		return numLines;
	}
	
	
	/*public void writeCSV(dataToWrite) {
		
		try {
			
			FileWriter fileWriter = new FileWriter(filename,false);
			
			
		}catch (Exception e){
			
		}
		
	}*/
	
}

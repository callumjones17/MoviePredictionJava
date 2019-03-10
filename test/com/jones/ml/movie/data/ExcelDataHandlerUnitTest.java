package com.jones.ml.movie.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class ExcelDataHandlerUnitTest extends TestCase {

	ExcelDataHandler edh = new ExcelDataHandler("D:\\zTestFiles\\test.csv");
	
	@Before
	@Override
	public void setUp() {
		
	}
	
	@Test
	void testExcelDataHandler() {
		//fail("Not yet implemented");
	}

	@Test
	void testReadCSV() {
		//fail("Not yet implemented");
		edh.readCSV();
		System.out.println(edh.getLineLen());
		System.out.println(edh.getNumLines());
	}

}

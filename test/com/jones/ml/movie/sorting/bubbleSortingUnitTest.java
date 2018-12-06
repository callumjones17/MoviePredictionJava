package com.jones.ml.movie.sorting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

import java.util.*;

class bubbleSortingUnitTest extends TestCase {

	private bubbleSorting bs = new bubbleSorting();
	
	@Override
	@Before
	public void setUp() {
		
	}
	
	@Test
	void testSortList() {
		
		List<Integer> sortedlist = new ArrayList<>();
		
		List<Integer> unsorted = new ArrayList<>();
		unsorted.add(1);
		unsorted.add(3);
		unsorted.add(4);
		unsorted.add(2);
		System.out.println(unsorted);
		
		List<Integer> expected = new ArrayList<>();
		expected.add(4);
		expected.add(3);
		expected.add(2);
		expected.add(1);
		System.out.println(expected);
		
		sortedlist = bs.sortList(unsorted);
		System.out.println(sortedlist);
		
		assertEquals(expected, sortedlist);
		
		//fail("Not yet implemented"); // TODO
	}

	@Test
	void testChangePositionInList() {
		
		List<Integer> listNumbers = new ArrayList<>();
		listNumbers.add(1);
		listNumbers.add(3);
		listNumbers.add(4);
		listNumbers.add(2);
		System.out.println(listNumbers);
		
		List<Integer> expected = new ArrayList<>();
		expected.add(1);
		expected.add(4);
		expected.add(3);
		expected.add(2);
		
		listNumbers = bs.changePositionInList(listNumbers, 1, 2);
		System.out.println(listNumbers);
		
		assertEquals(expected, listNumbers);
		//fail("Not yet implemented"); // TODO
	}

}

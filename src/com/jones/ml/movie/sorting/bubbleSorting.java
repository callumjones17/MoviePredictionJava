// This will be hard because in java I can't directly access the data.

package com.jones.ml.movie.sorting;

import java.util.*;

public class bubbleSorting {
	
	// Highest to lowest eg. [5,5,3,2,1,1,]
	public List<Integer> sortList(List<Integer> listToSort){
		
		int listSize = listToSort.size();
		int maxIterations = listSize * listSize;
		boolean changeOccured = true;
		
		for (int i = 0; i < maxIterations; i++) {
			changeOccured = false;
			for (int j = 0; j < (listSize-1); j++) {
				if (listToSort.get(j) < listToSort.get(j+1)) {
					listToSort = changePositionInList(listToSort,j,j+1);
					changeOccured = true;
				}
			}
			if (!changeOccured) {
				//System.out.println("this func works");
				break;
			}
		}
		
		return listToSort;
	}
	
	public List<Integer> changePositionInList(List<Integer> list, int fromIndex, int toIndex){
		
		Integer temp = list.get(fromIndex);
		list.set(fromIndex, list.get(toIndex));
		list.set(toIndex, temp);
		
		return list;
	}
	
}

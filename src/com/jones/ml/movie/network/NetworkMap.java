package com.jones.ml.movie.network;

import java.util.ArrayList;
import java.util.List;

public class NetworkMap {

	private List<Integer> nodes = new ArrayList<>();
	private boolean isFirstLayer1to1 = true; 						// Are all the data points connected to one network map each. False means dynamic
	private List<Integer> firstLayerMap = new ArrayList();
	
	public NetworkMap(List<Integer> nodes, boolean isFirstLayer1to1, List<Integer> firstLayerMap) {
		this.isFirstLayer1to1 = isFirstLayer1to1;
		this.nodes = nodes;
		this.firstLayerMap = firstLayerMap;
	}
	
	public NetworkMap(List<Integer> nodes) {
		this.nodes = nodes;
		this.isFirstLayer1to1 = false;
		this.firstLayerMap = null;
	}
	
	public Integer getNodesByLayer(Integer layer) {
		if (nodes.size() <= layer) {
			return -1;
		}
		return nodes.get(layer);
	}
	
	public List<Integer> getMap(){
		return nodes;
	}
	
	public boolean getIsFirstLayer1to1() {
		return isFirstLayer1to1;
	}
	
	public List<Integer> getFirstLayerMap(){
		return firstLayerMap;
	}
	
}

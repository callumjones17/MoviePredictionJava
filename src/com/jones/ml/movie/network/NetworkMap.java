package com.jones.ml.movie.network;

//import com.jones.ml.error.*;

import java.util.ArrayList;
import java.util.List;

public class NetworkMap {

	//private ErrorHandler erH = new ErrorHandler();
	
	private List<Integer> nodes = new ArrayList<>();
	private boolean isFirstLayer1to1 = true; 						// Are all the data points connected to one network map each. False means dynamic
	private List<Integer> firstLayerMap = new ArrayList();
	
	private boolean customRouting = false;
	private List<List<List<Integer>>> customRoutingMap = new ArrayList<>();
	
	public NetworkMap(List<Integer> nodes, boolean isFirstLayer1to1, List<Integer> firstLayerMap) {
		this.isFirstLayer1to1 = isFirstLayer1to1;
		this.nodes = nodes;
		this.firstLayerMap = firstLayerMap;
		this.customRouting = false;
		this.customRoutingMap = null;
	}
	
	public NetworkMap(List<Integer> nodes) {
		this.nodes = nodes;
		this.isFirstLayer1to1 = false;
		this.firstLayerMap = null;
		this.customRouting = false;
		this.customRoutingMap = null;
	}
	
	public NetworkMap(List<Integer> nodeMap, boolean isFirstLayer1to1, List<Integer> firstLayerMap, boolean customRouting, List<List<List<Integer>>> routingMap) {
		this.nodes = nodeMap;
		this.isFirstLayer1to1 = isFirstLayer1to1;
		this.firstLayerMap = firstLayerMap;
		this.customRouting = customRouting;
		this.customRoutingMap = routingMap;
	}
	
	public int calculateNumberOfWeightingsRequired() {
		
		int weCount = 0;
		
		if (!this.isFirstLayer1to1 & !this.customRouting) {
			
			weCount = this.getNodesByLayer(0);
			
			for (int layer = 1; layer < this.getMap().size(); layer++) {
				
				weCount += this.getNodesByLayer(layer-1) * this.getNodesByLayer(layer);
				
			}
			
		}
		
		return weCount;
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
	
	public boolean getIsCustomRouting() {
		return customRouting;
	}
	
	public List<List<List<Integer>>> getRoutingMap(){
		return customRoutingMap;
	}
	
}

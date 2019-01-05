package com.jones.ml.movie.network;

//import com.jones.ml.error.*;

import java.util.ArrayList;
import java.util.List;

import com.jones.ml.error.ErrorCodes;
import com.jones.ml.error.ErrorHandler;

public class NetworkMap {

	//private ErrorHandler erH = new ErrorHandler();
	
	private List<Integer> nodes = new ArrayList<>();
	private boolean isFirstLayer1to1 = true; 						// Are all the data points connected to one network map each. False means dynamic
	private List<Integer> firstLayerMap = new ArrayList<>();
	ErrorHandler erH = new ErrorHandler();
	
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
		this.isFirstLayer1to1 = true;
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
		
		if (this.isFirstLayer1to1 & !this.customRouting) {
			
			weCount = this.getNodesByLayer(0);
			
			for (int layer = 1; layer < this.getMap().size(); layer++) {
				
				weCount += this.getNodesByLayer(layer-1) * this.getNodesByLayer(layer);
			}
		}else if(!this.isFirstLayer1to1 & !this.customRouting) {
			
			for (int nodes: this.firstLayerMap) {
				weCount += nodes;
			}
			
			for (int layer = 1; layer < this.getMap().size(); layer++) {
				
				weCount += this.getNodesByLayer(layer-1) * this.getNodesByLayer(layer);
			}
		}else if (this.isFirstLayer1to1 & this.customRouting) {
			
			weCount = this.getNodesByLayer(0);
			
			for (int layer = 0; layer < this.getMap().size()-1; layer++) {
				
				for (int node = 0; node<this.getRoutingMap().get(layer).size(); node++) {
					for (int prevNode: this.getRoutingMap().get(layer).get(node)) {

						weCount++;
					}
				}
			}
		}else if (!this.isFirstLayer1to1 & this.customRouting) {

			for (int nodes: this.firstLayerMap) {
				weCount += nodes;
			}

			for (int layer = 0; layer < this.getMap().size()-1; layer++) {

				//System.out.println(this.getRoutingMap().get(layer).size());	
				for (int node = 0; node<this.getRoutingMap().get(layer).size(); node++) {
					for (int prevNode: this.getRoutingMap().get(layer).get(node)) {

						weCount++;
					}
				}
			}

		}else {
			erH.passError(ErrorCodes.INVALID_MAP_CONFIG);
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

package com.jones.ml.movie.network;

import java.util.ArrayList;
import java.util.List;

public class NetworkMap {

	private List<Integer> nodes = new ArrayList<>();
	
	public NetworkMap(List<Integer> nodes) {
		this.nodes = nodes;
	}
	
	public Integer getNodesByLayer(Integer layer) {
		if (nodes.size() <= layer) {
			return -1;
		}
		return nodes.get(layer);
	}
	
}

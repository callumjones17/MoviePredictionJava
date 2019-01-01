/**
 * 
 */
package com.jones.ml.error;


/**
 * @author Callum Jones
 *
 */
public enum ErrorCodes {
	
	// Issues with Genetic Alg are between 100 and 200
	NOT_ENOUGH_REM_AGENTS("Not Enough Remaining Agents",100),
	AGENTS_MUST_BE_SAME_LEN("All Agents must be the same length",101),
	
	// Issues with Neural Net are between 200 and 300
	INSUF_DATA_LAYER_1("Not Enough Data to fill First Layer",200),
	NOT_ENOUGH_WEIGHTINGS("Not Enough Weights for the network Configured!",201),
	DATA_INDEX_NOT_EQUAL_TO_AGENT("Data Index is not the same size as the Agent Weightings",202);
	
	private String desc;
	private int code;
	
	private ErrorCodes(String description, int errorCode) {
		this.code = errorCode;
		this.desc = description;
	}
	
	public int getCode() {
		return this.code;
	}
	public String getDesc() {
		return this.desc;
	}
}

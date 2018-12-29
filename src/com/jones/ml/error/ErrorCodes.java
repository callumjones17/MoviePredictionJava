/**
 * 
 */
package com.jones.ml.error;


/**
 * @author Callum Jones
 *
 */
public enum ErrorCodes {
	
	// Issues with Genetica Alg are between 100 and 200
	NOT_ENOUGH_REM_AGENTS("Not Enough Remaining Agents",100),
	AGENTS_MUST_BE_SAME_LEN("All Agents must be the same length",101);
	
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

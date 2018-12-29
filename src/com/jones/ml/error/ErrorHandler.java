/**
 * 
 */
package com.jones.ml.error;

/**
 * @author Callum Jones 2018
 *
 */
public class ErrorHandler {
	
	// Expects Error Code.
	public void passError(int code) {
		for (ErrorCodes error : ErrorCodes.values()) {
			if (error.getCode() == code) {
				System.out.println("Error: Code: " + code + ": " + error.getDesc());
				System.exit(0);
			}
		}
		System.out.println("Error: System Called for an error, not yet Defined!");
		System.exit(0);
	}
	
	public void passError(ErrorCodes error) {
		System.out.println("Error: Code: " + error.getCode() + ": " + error.getDesc());
		System.exit(0);
	}
	
}


package com.fa3.synced.core.exceptions;

public class NoSuchInterpreterException extends RuntimeException {

	public NoSuchInterpreterException(Class<?> classFor) {
		super("No Interpreter found for " + classFor.getCanonicalName());
	}
}

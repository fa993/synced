package com.fa3.synced.core.interpreters.strings;

public class IntegerToStringInterpreter extends StringInterpreter<Integer> {

	@Override
	public Integer decode(String encoded) {
		return Integer.parseInt(encoded);
	}
}

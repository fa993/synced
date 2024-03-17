package com.fa3.synced.core.interpreters.strings;

public class StringToStringInterpreter extends StringInterpreter<String> {

	@Override
	public String decode(String encoded) {
		return encoded;
	}

}

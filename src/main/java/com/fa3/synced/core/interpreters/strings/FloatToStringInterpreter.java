package com.fa3.synced.core.interpreters.strings;

public class FloatToStringInterpreter extends StringInterpreter<Float> {

	@Override
	public Float decode(String encoded) {
		return Float.parseFloat(encoded);
	}

}

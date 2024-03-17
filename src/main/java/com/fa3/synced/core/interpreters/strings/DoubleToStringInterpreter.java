package com.fa3.synced.core.interpreters.strings;

public class DoubleToStringInterpreter extends StringInterpreter<Double> {

	@Override
	public Double decode(String encoded) {
		return Double.parseDouble(encoded);
	}

}

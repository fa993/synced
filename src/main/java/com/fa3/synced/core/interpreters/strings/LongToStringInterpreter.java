package com.fa3.synced.core.interpreters.strings;

public class LongToStringInterpreter extends StringInterpreter<Long>{

	@Override
	public Long decode(String encoded) {
		return Long.parseLong(encoded);
	}

}

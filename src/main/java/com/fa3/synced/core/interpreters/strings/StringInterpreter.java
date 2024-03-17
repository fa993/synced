package com.fa3.synced.core.interpreters.strings;

import com.fa3.synced.core.interpreters.Interpreter;

public abstract class StringInterpreter<T> implements Interpreter<T, String> {

	@Override
	public String encode(T data) {
		return data.toString();
	}
}

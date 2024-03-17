package com.fa3.synced.core.interpreters;

import com.fa3.synced.core.Pair;

public class ConcatPairInterpreter<A, B> implements Interpreter<Pair<A, B>, String> {

	private char connector;
	private Interpreter<A, String> firstInterpreter;
	private Interpreter<B, String> secondInterpreter;

	public ConcatPairInterpreter(char connector, Interpreter<A, String> firstInterpreter, Interpreter<B, String> secondInterpreter) {
		this.connector = connector;
		this.firstInterpreter = firstInterpreter;
		this.secondInterpreter = secondInterpreter;
	}

	@Override
	public Pair<A, B> decode(String encoded) {
		int loc = encoded.indexOf(connector);

		String first = encoded.substring(0, loc);
		String second = encoded.substring(loc + 1);

		A fi = firstInterpreter.decode(first);
		B se = secondInterpreter.decode(second);

		return new Pair<>(fi, se);
	}

	@Override
	public String encode(Pair<A, B> data) {
		return firstInterpreter.encode(data.getFirst()) + connector + secondInterpreter.encode(data.getSecond());
	}

	public A decodeKey(String encoded) {
		return firstInterpreter.decode(encoded.substring(0, encoded.indexOf(connector)));
	}

}

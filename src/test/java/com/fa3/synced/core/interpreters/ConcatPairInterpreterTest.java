package com.fa3.synced.core.interpreters;

import com.fa3.synced.core.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcatPairInterpreterTest {

	ConcatPairInterpreter<String, String> stringConcatPairInterpreter;
	Pair<String, String> decodedPair;
	String encodedLine;
	String key;

	@BeforeEach
	public void init() {
		stringConcatPairInterpreter = new ConcatPairInterpreter<>('=', Interpreters.getDefaultEncoder(String.class), Interpreters.getDefaultEncoder(String.class));
		decodedPair = new Pair<>("Hello", "World");
		encodedLine = "Hello=World";
		key = "Hello";
	}

	@Test
	public void testEncode() {
		String line = stringConcatPairInterpreter.encode(decodedPair);
		assertEquals(line, encodedLine);
	}

	@Test
	public void testDecode() {
		Pair<String, String> pair = stringConcatPairInterpreter.decode(encodedLine);
		assertEquals(pair, decodedPair);
	}

	@Test
	public void testDecodeKey() {
		String decoded = stringConcatPairInterpreter.decodeKey(encodedLine);
		assertEquals(decoded, key);
	}

}

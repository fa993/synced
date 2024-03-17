package com.fa3.synced.core.interpreters;

import com.fa3.synced.core.exceptions.NoSuchInterpreterException;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class InterpretersTest {
	
	@Test
	public void testStringInterpreter() {
		Interpreter<String, String> inter = Interpreters.getDefaultEncoder(String.class);
		String payload = "hello";
		assertSame(inter.encode(payload), payload);
		assertSame(inter.decode(payload), payload);
	}

	@Test
	public void testIntInterpreter() {
		Interpreter<Integer, String> inter = Interpreters.getDefaultEncoder(Integer.class);
		assertEquals(inter.encode(12), "12");
		assertEquals(inter.decode("-1"), -1);
	}

	@Test
	public void testLongInterpreter() {
		Interpreter<Long, String> inter = Interpreters.getDefaultEncoder(Long.class);
		assertEquals(inter.encode(-12L), "-12");
		assertEquals(inter.decode("1"), 1L);
	}

	@Test
	public void testFloatInterpreter() {
		Interpreter<Float, String> inter = Interpreters.getDefaultEncoder(Float.class);
		assertEquals(inter.encode(-12.3f), "-12.3");
		assertEquals(inter.decode("14.6"), 14.6f);
	}

	@Test
	public void testDoubleInterpreter() {
		Interpreter<Double, String> inter = Interpreters.getDefaultEncoder(Double.class);
		assertEquals(inter.encode(12.3d), "12.3");
		assertEquals(inter.decode("-14.6"), -14.6d);
	}

	@Test
	public void testSubtypeInterpreter() {
		// TODO: 11/10/23 Will add when subtypeable classes are supported
	}

	@Test
	public void testNoDefaultInterpreter() {
		assertThrowsExactly(NoSuchInterpreterException.class, () -> {
			Interpreters.getDefaultEncoder(Object.class);
		});
	}

}

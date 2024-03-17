package com.fa3.synced.core.interpreters;

import com.fa3.synced.core.exceptions.NoSuchInterpreterException;
import com.fa3.synced.core.interpreters.strings.*;

public class Interpreters {

	@SuppressWarnings("unchecked")
	public static <T> Interpreter<T, String> getDefaultEncoder(Class<? extends T> clazz) {
		if (String.class.isAssignableFrom(clazz)) {
			return (Interpreter<T, String>) new StringToStringInterpreter();
		} else if(Integer.class.isAssignableFrom(clazz)) {
			return (Interpreter<T, String>) new IntegerToStringInterpreter();
		} else if(Long.class.isAssignableFrom(clazz)) {
			return (Interpreter<T, String>) new LongToStringInterpreter();
		} else if(Float.class.isAssignableFrom(clazz)){
			return (Interpreter<T, String>) new FloatToStringInterpreter();
		} else if(Double.class.isAssignableFrom(clazz)) {
			return (Interpreter<T, String>) new DoubleToStringInterpreter();
		} else {
			throw new NoSuchInterpreterException(clazz);
		}
	}


}

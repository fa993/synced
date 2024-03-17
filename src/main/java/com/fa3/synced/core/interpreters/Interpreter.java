package com.fa3.synced.core.interpreters;

public interface Interpreter<F, T> {

	F decode(T encoded);

	T encode(F data);

}

package com.fa3.synced.core;

import java.io.IOException;

public interface LazySynced<T> {

	void reflect(T item) throws IOException;

	void flush() throws IOException;

	void sync() throws IOException;

}

package com.fa3.synced.core.store;

import java.io.IOException;
import java.util.Map;

public interface Store<T> {

	void write(T index, String data) throws IOException;

	String read(T index) throws IOException;

	Map<T, String> readAll() throws IOException;

	void writeAll(Map<? extends T, String> data) throws IOException;

}

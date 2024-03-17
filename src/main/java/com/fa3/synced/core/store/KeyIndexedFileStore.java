package com.fa3.synced.core.store;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public class KeyIndexedFileStore<T> implements Store<T>{

	private Store<Integer> inner;
	private Map<T, Integer> lineNos;
	private BiFunction<Integer, String, T> keyGenerator;

	public KeyIndexedFileStore(Store<Integer> inner, BiFunction<Integer, String, T> keyGenerator) {
		this.inner = inner;
		this.lineNos = new HashMap<>();
		this.keyGenerator = keyGenerator;
	}

	@Override
	public void write(T index, String data) throws IOException {
		int def = lineNos.values().stream().map(t->t+1).max(Comparator.naturalOrder()).orElseGet(lineNos::size);
		int lineNo = lineNos.getOrDefault(index, def);
		inner.write(lineNo, data);
		lineNos.put(index, lineNo);
	}

	@Override
	public String read(T index) throws IOException {
		return inner.read(Optional.ofNullable(lineNos.get(index)).orElseThrow(() -> new IOException("Index " + index + " not found")));
	}

	//This changes internal state
	@Override
	public Map<T, String> readAll() throws IOException {
		Map<Integer, String> cache = inner.readAll();
		lineNos.clear();
		Map<T, String> all = new HashMap<>();
		for (Map.Entry<Integer, String> en: cache.entrySet()) {
			T key = keyGenerator.apply(en.getKey(), en.getValue());
			all.put(key, en.getValue());
			lineNos.put(key, en.getKey());
		}
		return all;
	}

	//This changes internal state
	@Override
	public void writeAll(Map<? extends T, String> data) throws IOException {
		Map<Integer, String> indexedData = new HashMap<>();
		Map<T, Integer> nLineNos = new HashMap<>();
		int index = 0;
		for(Map.Entry<? extends T, String> en: data.entrySet()) {
			indexedData.put(index, en.getValue());
			nLineNos.put(en.getKey(), index);
			index++;
		}
		inner.writeAll(indexedData);
		lineNos = nLineNos;
	}

//	public void setDelimiter(String delimiter) {
//		this.inner.setDelimiter(delimiter);
//	}
}

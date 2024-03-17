package com.fa3.synced.collections;

import com.fa3.synced.collections.delegated.DelegatedList;
import com.fa3.synced.core.Pair;
import com.fa3.synced.core.LazySynced;
import com.fa3.synced.core.interpreters.Interpreter;
import com.fa3.synced.core.store.Store;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LazySyncedList<E> extends DelegatedList<E> implements LazySynced<Integer> {

	private final Store<Integer> persistedStore;
	private final Interpreter<E, String> valueInterpreter;

	public LazySyncedList(List<E> inner, Store<Integer> persistedStore, Interpreter<E, String> valueInterpreter) {
		super(inner);
		this.persistedStore = persistedStore;
		this.valueInterpreter = valueInterpreter;
	}

	@Override
	public void reflect(Integer item) throws IOException {
		this.persistedStore.write(item, valueInterpreter.encode(inner.get(item)));
	}

	@Override
	public void flush() throws IOException {
		Map<Integer, String> all = IntStream.range(0, inner.size()).mapToObj(t -> new Pair<>(t, valueInterpreter.encode(inner.get(t)))).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
		this.persistedStore.writeAll(all);
	}

	@Override
	public void sync() throws IOException {
		Map<Integer, String> all = this.persistedStore.readAll();
		while (all.size() > inner.size()) {
			inner.add(null);
		}
		while (all.size() < inner.size()) {
			inner.remove(inner.size() - 1);
		}
		for(Map.Entry<Integer, String> en : all.entrySet()) {
			inner.set(en.getKey(), valueInterpreter.decode(en.getValue()));
		}
	}
}

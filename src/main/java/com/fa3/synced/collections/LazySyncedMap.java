package com.fa3.synced.collections;

import com.fa3.synced.collections.delegated.DelegatedMap;
import com.fa3.synced.core.Pair;
import com.fa3.synced.core.LazySynced;
import com.fa3.synced.core.interpreters.ConcatPairInterpreter;
import com.fa3.synced.core.interpreters.Interpreter;
import com.fa3.synced.core.interpreters.Interpreters;
import com.fa3.synced.core.store.IndexedEagerFileStore;
import com.fa3.synced.core.store.KeyIndexedFileStore;
import com.fa3.synced.core.store.IndexedFixedLineLengthFileStore;
import com.fa3.synced.core.store.Store;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class LazySyncedMap<K, V> extends DelegatedMap<K, V> implements LazySynced<K> {

	private final Store<K> persistedStore;
	private final Interpreter<Pair<K, V>, String> entryInterpreter;

	public static <K,V> LazySyncedMap<K, V> getFixedMapEnvLike(Map<K, V> inner, Class<K> keyClazz, Class<V> valueClazz, File backingFile) throws FileNotFoundException {
		ConcatPairInterpreter<K, V> entryInterpreter = new ConcatPairInterpreter<>('=', Interpreters.getDefaultEncoder(keyClazz), Interpreters.getDefaultEncoder(valueClazz));
		Store<K> store = new KeyIndexedFileStore<>(new IndexedFixedLineLengthFileStore(backingFile, 120), (k, v) -> entryInterpreter.decodeKey(v));
		return new LazySyncedMap<>(inner, store, entryInterpreter);
	}

	public static <K,V> LazySyncedMap<K, V> getFixedMapEnvLike(Map<K, V> inner, Class<K> keyClazz, Class<V> valueClazz, File backingFile, String delimiter) throws FileNotFoundException {
		ConcatPairInterpreter<K, V> entryInterpreter = new ConcatPairInterpreter<>('=', Interpreters.getDefaultEncoder(keyClazz), Interpreters.getDefaultEncoder(valueClazz));
		Store<K> store = new KeyIndexedFileStore<>(new IndexedFixedLineLengthFileStore(backingFile, 120, delimiter), (k, v) -> entryInterpreter.decodeKey(v));
		return new LazySyncedMap<>(inner, store, entryInterpreter);
	}

	public static <K,V> LazySyncedMap<K, V> getEagerMapEnvLike(Map<K, V> inner, Class<K> keyClazz, Class<V> valueClazz, File backingFile) throws IOException {
		ConcatPairInterpreter<K, V> entryInterpreter = new ConcatPairInterpreter<>('=', Interpreters.getDefaultEncoder(keyClazz), Interpreters.getDefaultEncoder(valueClazz));
		Store<K> store = new KeyIndexedFileStore<>(new IndexedEagerFileStore(backingFile), (k, v) -> entryInterpreter.decodeKey(v));
		return new LazySyncedMap<>(inner, store, entryInterpreter);
	}

	public static <K,V> LazySyncedMap<K, V> getEagerMapEnvLike(Map<K, V> inner, Class<K> keyClazz, Class<V> valueClazz, File backingFile, String delimiter) throws IOException {
		ConcatPairInterpreter<K, V> entryInterpreter = new ConcatPairInterpreter<>('=', Interpreters.getDefaultEncoder(keyClazz), Interpreters.getDefaultEncoder(valueClazz));
		Store<K> store = new KeyIndexedFileStore<>(new IndexedEagerFileStore(backingFile, delimiter), (k, v) -> entryInterpreter.decodeKey(v));
		return new LazySyncedMap<>(inner, store, entryInterpreter);
	}

	public LazySyncedMap(Map<K, V> inner, Store<K> persistedStore, Interpreter<Pair<K, V>, String> entryInterpreter) {
		super(inner);
		this.inner = inner;
		this.persistedStore = persistedStore;
		this.entryInterpreter = entryInterpreter;
	}

	public void reflect(K key) throws IOException {
		this.persistedStore.write(key, entryInterpreter.encode(new Pair<>(key, inner.get(key))));
	}

	public void flush() throws IOException {
		Map<K, String> entries = new HashMap<>();
		for(Map.Entry<K, V> en : inner.entrySet()) {
			entries.put(en.getKey(), entryInterpreter.encode(new Pair<>(en.getKey(), en.getValue())));
		}
		this.persistedStore.writeAll(entries);
	}

	public void sync() throws IOException {
		Map<K, String> entries = this.persistedStore.readAll();
		for(Map.Entry<K, String> en : entries.entrySet()) {
			inner.put(en.getKey(), entryInterpreter.decode(en.getValue()).getSecond());
		}
	}

}

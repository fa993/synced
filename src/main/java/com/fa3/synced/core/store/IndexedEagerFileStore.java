package com.fa3.synced.core.store;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class IndexedEagerFileStore implements Store<Integer>{

	private RandomAccessFile backingStore;
	private String delimiter;

	public IndexedEagerFileStore(String backingStoreFileName) throws IOException {
		this.backingStore = new RandomAccessFile(backingStoreFileName, "rws");
	}

	public IndexedEagerFileStore(File backingStoreFile) throws IOException {
		this.backingStore = new RandomAccessFile(backingStoreFile, "rws");
	}

	public IndexedEagerFileStore(String backingStoreFileName, String delimiter) throws IOException {
		this.delimiter = delimiter;
		this.backingStore = new RandomAccessFile(backingStoreFileName, "rws");
	}

	public IndexedEagerFileStore(File backingStoreFile, String delimiter) throws IOException {
		this.delimiter = delimiter;
		this.backingStore = new RandomAccessFile(backingStoreFile, "rws");
	}

	@Override
	public void write(Integer index, String data) throws IOException {
		List<String> cache = getAll();
		if(index < cache.size() && data.equals(cache.get(index))) {
			return;
		} else if(index == cache.size()) {
			cache.add(data);
		} else if(index > cache.size()) {
			throw new IOException("Key does not exist");
		} else {
			cache.set(index, data);
		}
		writeAll(cache.iterator());
	}

	@Override
	public String read(Integer index) throws IOException {
		List<String> cache = getAll();
		return index < cache.size() ? cache.get(index): null;
	}

	@Override
	public Map<Integer, String> readAll() throws IOException {
		List<String> cache = getAll();
		return IntStream.range(0, cache.size()).boxed().collect(Collectors.toMap(Function.identity(), cache::get));
	}

	// TODO: 12/10/23 This straight up ignores the integer key lol, sorts in ascending order and writes all to file
	@Override
	public void writeAll(Map<? extends Integer, String> data) throws IOException {
		writeAll(data.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(Map.Entry::getValue).iterator());
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	private void writeAll(Iterator<String> cache) throws IOException {
		seekToBeginning();
		backingStore.setLength(backingStore.getFilePointer());
		while (cache.hasNext()) {
			String line = cache.next();
			backingStore.write(((line == null ? "" : line) + "\n").getBytes(StandardCharsets.UTF_8));
		}
	}

	private List<String> getAll() throws IOException {
		seekToBeginning();
		String tmp;
		List<String> cache = new ArrayList<>();
		while((tmp = backingStore.readLine()) != null) {
			cache.add(tmp.trim());
		}
		return cache;
	}

	private void seekToBeginning() throws IOException {
		this.backingStore.seek(0);
		if(this.delimiter != null) {
			String line = "";
			while (line != null && !line.trim().equals(delimiter)) {
				line = this.backingStore.readLine();
			}
		}
	}


}

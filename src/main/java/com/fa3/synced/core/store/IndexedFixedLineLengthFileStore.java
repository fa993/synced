package com.fa3.synced.core.store;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IndexedFixedLineLengthFileStore implements Store<Integer> {

	private RandomAccessFile backingStore;
	private int maxLineLength;
	private String delimiter;

	public IndexedFixedLineLengthFileStore(String backingStoreFileName, int maxLineLength) throws FileNotFoundException {
		this.backingStore = new RandomAccessFile(backingStoreFileName, "rws");
		this.maxLineLength = maxLineLength;
	}

	public IndexedFixedLineLengthFileStore(File backingStoreFile, int maxLineLength) throws FileNotFoundException {
		this.backingStore = new RandomAccessFile(backingStoreFile, "rws");
		this.maxLineLength = maxLineLength;
	}

	public IndexedFixedLineLengthFileStore(String backingStoreFileName, int maxLineLength, String delimiter) throws FileNotFoundException{
		this(backingStoreFileName, maxLineLength);
		this.delimiter = delimiter;
	}

	public IndexedFixedLineLengthFileStore(File backingStoreFile, int maxLineLength, String delimiter) throws FileNotFoundException{
		this(backingStoreFile, maxLineLength);
		this.delimiter = delimiter;
	}

	@Override
	public void write(Integer index, String data) throws IOException {
		if(data.length() > maxLineLength) {
			throw new IOException("Line length " + data.length() + " greater than max allowed (" + maxLineLength + ")");
		}
		data = padWithWhiteSpace(data);
		seekToLineExact(index);
		backingStore.write(data.getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public String read(Integer index) throws IOException {
		seekToLine(index);
		String line = this.backingStore.readLine();
		return line == null ? null : line.trim();
	}

	@Override
	public Map<Integer, String> readAll() throws IOException {
		seekToLine(0);
		String tmp;
		List<String> cache = new ArrayList<>();
		while((tmp = backingStore.readLine()) != null) {
			cache.add(tmp.trim());
		}
		return IntStream.range(0, cache.size()).boxed().collect(Collectors.toMap(Function.identity(), cache::get));
	}

	// TODO: 12/10/23 This straight up ignores the integer key lol, sorts in ascending order and writes all to file
	@Override
	public void writeAll(Map<? extends Integer, String> data) throws IOException {
		seekToLine(0);
		backingStore.setLength(backingStore.getFilePointer());
		Stream<? extends Map.Entry<? extends Integer, String>> dataStream = data.entrySet().stream().sorted(Map.Entry.comparingByKey());
		for (Iterator<? extends Map.Entry<? extends Integer, String>> it = dataStream.iterator(); it.hasNext(); ) {
			String line = it.next().getValue();
			if(line.length() > maxLineLength) {
				throw new IOException("Line length " + line.length() + " greater than max allowed (" + maxLineLength + ")");
			}
			line = padWithWhiteSpace(line);
			backingStore.write(line.getBytes(StandardCharsets.UTF_8));
		}
		backingStore.setLength(backingStore.getFilePointer());
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	private void seekToLine(int lineNo) throws IOException {
		this.backingStore.seek(0);
		if(this.delimiter != null) {
			String line = "";
			while (!delimiter.equals(line.trim())) {
				line = this.backingStore.readLine();
				if(line == null) {
					return;
				}
			}
		}
		for(int i = 0; i < lineNo; i++) {
			this.backingStore.readLine();
		}
	}

	private void seekToLineExact(int lineNo) throws IOException {
		this.backingStore.seek(0);
		if(this.delimiter != null) {
			String line = "";
			while (!delimiter.equals(line.trim())) {
				line = this.backingStore.readLine();
				if(line == null) {
					return;
				}
			}
		}
		for(int i = 0; i < lineNo; i++) {
			String line = this.backingStore.readLine();
			if (line == null && i != lineNo - 1) {
				throw new IOException("Key does not exist");
			}
		}
	}

	private String padWithWhiteSpace(String data) {
		StringBuilder dataBuilder = new StringBuilder(data == null ? "" : data);
		dataBuilder.append(" ".repeat(Math.min(maxLineLength, maxLineLength - dataBuilder.length())));
		dataBuilder.append("\n");
		return dataBuilder.toString();
	}

}

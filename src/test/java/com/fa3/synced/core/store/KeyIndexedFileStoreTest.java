package com.fa3.synced.core.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class KeyIndexedFileStoreTest {

	@Test
	public void test(@TempDir Path dirPath) throws IOException {
		IndexedFixedLineLengthFileStore ifs = new IndexedFixedLineLengthFileStore(new File(dirPath.toFile(), "test1.txt"), 120);
		KeyIndexedFileStore<String> fs = new KeyIndexedFileStore<>(ifs, (idx, val) -> val.substring(0, 2));

		assertEquals(Collections.emptyMap(), fs.readAll());

		fs.write("as", "asd");
		fs.write("ds", "dsfg");
		fs.write("ut", "utubfbfb");

		assertEquals("asd", fs.read("as"));

		assertThrows(IOException.class, () -> fs.read("notpreset"));

		Map<String, String> actual = new HashMap<>();

		actual.put("as", "asd");
		actual.put("ds", "dsfg");
		actual.put("ut", "utubfbfb");

		Map<String, String> entires = fs.readAll();

		assertEquals(actual, entires);

		entires.put("an", "anotherone");
		entires.put("ds", "dschange");

		fs.writeAll(entires);

		assertEquals(entires, fs.readAll());
	}

}

package com.fa3.synced.core.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class IndexedFixedLineLengthStoreTest {

	@Test
	public void test(@TempDir Path dirPath) throws IOException {
		IndexedFixedLineLengthFileStore ifs = new IndexedFixedLineLengthFileStore(new File(dirPath.toFile(), "test1.txt"), 120);
		assertEquals(Collections.emptyMap(), ifs.readAll());

		ifs.write(0, "Asd");
		assertEquals("Asd", ifs.read(0));

		List<String> items = new ArrayList<>();
		items.add("asd");
		items.add("fds");
		items.add("yyh");

		Map<Integer, String> entires=  IntStream.range(0, items.size()).boxed().collect(Collectors.toMap(Function.identity(), items::get));
		ifs.writeAll(entires);

		assertEquals(entires, ifs.readAll());

		assertEquals("yyh", ifs.read(2));

		assertNull(ifs.read(5));

		assertThrows(IOException.class, () -> ifs.write(5, "tyr"));
		assertNull(ifs.read(3));

		ifs.write(3, "tyr");

		ifs.write(1, "nono");
		assertEquals("nono", ifs.read(1));

		entires.put(3, "tyr");
		entires.put(1, "nono");

		assertEquals(entires, ifs.readAll());
	}

}

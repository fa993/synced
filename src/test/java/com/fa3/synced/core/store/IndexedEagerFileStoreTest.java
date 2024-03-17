package com.fa3.synced.core.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class IndexedEagerFileStoreTest {

	@Test
	public void test(@TempDir Path dirPath) throws IOException {
		IndexedEagerFileStore ifs = new IndexedEagerFileStore(new File(dirPath.toFile(), "test1.txt"));
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

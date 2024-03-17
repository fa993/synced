package com.fa3.synced.collections;

import com.fa3.synced.core.interpreters.Interpreters;
import com.fa3.synced.core.store.IndexedEagerFileStore;
import com.fa3.synced.core.store.IndexedFixedLineLengthFileStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class LazySyncedListTest {

	@Test
	public void testEnvList(@TempDir Path dirPath) throws IOException {

		File f = new File(dirPath.toFile(), "test1.txt");

		PrintWriter pr = new PrintWriter(new FileOutputStream(f));

		pr.write("asdadfadsfsfff\n");
		pr.write("------\n");

		pr.flush();

		LazySyncedList<String> sLst = new LazySyncedList<>(new ArrayList<>(),new IndexedEagerFileStore(f, "------"), Interpreters.getDefaultEncoder(String.class));

		sLst.add("asd");
		sLst.add("qwerty");

		sLst.flush();

		LazySyncedList<String> sLstOther = new LazySyncedList<>(new ArrayList<>(),new IndexedEagerFileStore(f, "------"), Interpreters.getDefaultEncoder(String.class));

		sLstOther.sync();

		assertEquals(sLst, sLstOther);

		sLstOther.set(1, "fsdfsdfsdf");
		sLstOther.reflect(1);

		sLst.sync();

		assertEquals(sLstOther, sLst);

	}


}

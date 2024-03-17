package com.fa3.synced.collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class LazySyncedMapTest {

	@Test
	public void testFixedEnvMap(@TempDir Path dirPath) throws IOException {

		File f = new File(dirPath.toFile(), "test1.txt");

		PrintWriter pr = new PrintWriter(new FileOutputStream(f));

		pr.write("asdadfadsfsfff\n");
		pr.write("------\n");

		pr.flush();

		LazySyncedMap<String, String> sMap = LazySyncedMap.getFixedMapEnvLike(new HashMap<>(), String.class, String.class, f, "------");

		sMap.put("asd", "sdfklsmdf");
		sMap.reflect("asd");

		LazySyncedMap<String, String> sMapOther = LazySyncedMap.getFixedMapEnvLike(new HashMap<>(), String.class, String.class, f, "------");

		sMapOther.sync();

		assertEquals("sdfklsmdf", sMapOther.get("asd"));

		sMapOther.put("tgtgt", "sdfksdf");
		sMapOther.put("yth", "fmfdfkldf");
		sMapOther.put("asd", "dsa");

		sMapOther.flush();

		sMap.sync();

		assertEquals(sMapOther, sMap);
	}

	@Test
	public void testEagerEnvMap(@TempDir Path dirPath) throws IOException {

		File f = new File(dirPath.toFile(), "test1.txt");

		PrintWriter pr = new PrintWriter(new FileOutputStream(f));

		pr.write("asdadfadsfsfff\n");
		pr.write("------\n");

		pr.flush();

		LazySyncedMap<String, String> sMap = LazySyncedMap.getEagerMapEnvLike(new HashMap<>(), String.class, String.class, f, "------");

		sMap.put("asd", "sdfklsmdf");
		sMap.reflect("asd");

		LazySyncedMap<String, String> sMapOther = LazySyncedMap.getEagerMapEnvLike(new HashMap<>(), String.class, String.class, f, "------");

		sMapOther.sync();

		assertEquals("sdfklsmdf", sMapOther.get("asd"));

		sMapOther.put("tgtgt", "sdfksdf");
		sMapOther.put("yth", "fmfdfkldf");
		sMapOther.put("asd", "dsa");

		sMapOther.flush();

		sMap.sync();

		assertEquals(sMapOther, sMap);
	}

	@Test
	public void testEagerEnvMapEmptyStore(@TempDir Path dirPath) throws IOException {

		File f = new File(dirPath.toFile(), "test1.txt");

//		PrintWriter pr = new PrintWriter(new FileOutputStream(f));
//
//		pr.write("asdadfadsfsfff\n");
//		pr.write("------\n");
//
//		pr.flush();

		LazySyncedMap<String, String> sMap = LazySyncedMap.getEagerMapEnvLike(new HashMap<>(), String.class, String.class, f, "------");

		sMap.sync();
		assertTrue(sMap.isEmpty());
	}

	@Test
	public void testFixedEnvMapEmptyStore(@TempDir Path dirPath) throws IOException {

		File f = new File(dirPath.toFile(), "test1.txt");

//		PrintWriter pr = new PrintWriter(new FileOutputStream(f));
//
//		pr.write("asdadfadsfsfff\n");
//		pr.write("------\n");
//
//		pr.flush();

		LazySyncedMap<String, String> sMap = LazySyncedMap.getFixedMapEnvLike(new HashMap<>(), String.class, String.class, f, "------");

		sMap.sync();
		assertTrue(sMap.isEmpty());
	}


}

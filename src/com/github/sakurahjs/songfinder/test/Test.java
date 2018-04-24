package com.github.sakurahjs.songfinder.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import com.github.sakurahjs.songfinder.test.SongModel;
import com.github.sakurahjs.songfinder.ky.KySongFinder;
import com.github.sakurahjs.songfinder.tj.TjMonthlyNewSongCollector;
import com.github.sakurahjs.songfinder.tj.TjMonthlyPopularSongCollector;
import com.github.sakurahjs.songfinder.tj.TjSongFinder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Test {
	private TjMonthlyNewSongCollector tjMonthlyNewSongCollector;
	private TjMonthlyPopularSongCollector tjMonthlyPopularSongCollector;

	private TjSongFinder tjSongFinder;
	private KySongFinder kySongFinder;
	
	private Gson gson;
	private TypeToken<List<SongModel>> typeToken;
	
	public Test() {
		tjMonthlyNewSongCollector = new TjMonthlyNewSongCollector();
		tjMonthlyPopularSongCollector = new TjMonthlyPopularSongCollector();
		
		tjSongFinder = new TjSongFinder();
		kySongFinder = new KySongFinder();
		
		gson = new Gson();
		typeToken = new TypeToken<List<SongModel>>() {};
	}
	
	//@org.junit.Test
	public void testTjMonthlyNewSongCollector() throws IOException {
		String json = tjMonthlyNewSongCollector.execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}

	//@org.junit.Test
	public void testTjMonthlyPopularSongCollector() throws IOException {
		String json = tjMonthlyPopularSongCollector.execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testTjSongFinder_default() throws IOException {
		String json = tjSongFinder.search("박하사탕").execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertEquals("박하사탕", results.get(0).getTitle());
	}
		
	//@org.junit.Test
	public void testTjSongFinder_byTitle_withHighPrecision() throws IOException {
		String json = tjSongFinder.search("박하사탕").byTitle().withHighPrecision().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertEquals("박하사탕", results.get(0).getTitle());
	}
	
	//@org.junit.Test
	public void testTjSongFinder_byTitle_withLowPrecision() throws IOException {
		String json = tjSongFinder.search("박하사탕").byTitle().withLowPrecision().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testTjSongFinder_bySongNumber_withHighPrecision() throws IOException {
		String json = tjSongFinder.search("9699").bySongNumber().withHighPrecision().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertEquals("박하사탕", results.get(0).getTitle());
	}
	
	//@org.junit.Test
	public void testTjSongFinder_bySongNumber_withLowPrecision() throws IOException {
		String json = tjSongFinder.search("9699").bySongNumber().withLowPrecision().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		System.out.println(json);
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testTjSongFinder_bySinger_withHighPrecision() throws IOException {
		String json = tjSongFinder.search("윤도현밴드").bySinger().withHighPrecision().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testTjSongFinder_bySinger_withLowPrecision() throws IOException {
		String json = tjSongFinder.search("윤도현밴드").bySinger().withLowPrecision().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testTjSongFinder_byLyricist_withHighPrecision() throws IOException {
		String json = tjSongFinder.search("강은경").byLyricist().withHighPrecision().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testTjSongFinder_byLyricist_withLowPrecision() throws IOException {
		String json = tjSongFinder.search("강은경").byLyricist().withLowPrecision().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testTjSongFinder_bySongWriter_withHighPrecision() throws IOException {
		String json = tjSongFinder.search("이경섭").bySongWriter().withHighPrecision().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testTjSongFinder_bySongWriter_withLowPrecision() throws IOException {
		String json = tjSongFinder.search("이경섭").bySongWriter().withLowPrecision().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testKySongFinder_default() throws IOException {
		String json = kySongFinder.search("박하사탕").execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		System.out.println(json);
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testKySongFinder_noLyrics() throws IOException {
		String json = kySongFinder.search("박하사탕").noLyrics().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testKySongFinder_byTitle() throws IOException {
		String json = kySongFinder.search("박하사탕").byTitle().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testKySongFinder_byTitleWithHighPrecision() throws IOException {
		String json = kySongFinder.search("박하사탕").byTitleWithHighPrecision().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testKySongFinder_bySongNumber() throws IOException {
		String json = kySongFinder.search("7640").bySongNumber().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertEquals("박하사탕", results.get(0).getTitle());
	}
	
	//@org.junit.Test
	public void testKySongFinder_bySinger() throws IOException {
		String json = kySongFinder.search("김흥국").bySinger().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	//@org.junit.Test
	public void testKySongFinder_byLyricist() throws IOException {
		String json = kySongFinder.search("강은경").byLyricist().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		
		assertTrue(results.size() > 0);
	}
	
	@org.junit.Test
	public void testKySongFinder_bySongWriter() throws IOException {
		String json = kySongFinder.search("이경섭").bySongWriter().execute();
		List<SongModel> results = gson.fromJson(json, typeToken.getType());
		System.out.println(json);
		assertTrue(results.size() > 0);
	}
}

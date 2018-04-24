package com.github.sakurahjs.songfinder.tj;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.sakurahjs.songfinder.SongBase;

public class TjSongFinder extends SongBase {
	private String searchType = "";
	private String searchText = "";
	private String searchAccuracy = "";
	private int page = 0;
	
	@Override
	protected void init() {
		conn = null;
		songCountInRequest = 0;
		maxSongCountInRequest = 100;
		
		documentSelector = TjConstants.DOCUMENT_SELECTOR;
		
		searchType = TjConstants.SEARCH_TYPE_TITLE;
		searchText = "";
		searchAccuracy = TjConstants.SEARCH_PRECISION_LOW;
		page = 0;
	}
	
	public TjSongFinder search(String searchText) {
		this.searchText = searchText;
		
		return this;
	}
	
	public TjSongFinder withLowPrecision() {
		searchAccuracy = TjConstants.SEARCH_PRECISION_LOW;
		
		return this;
	}
	
	public TjSongFinder withHighPrecision() {
		searchAccuracy = TjConstants.SEARCH_PRECISION_HIGH;
		
		return this;
	}
	
	public TjSongFinder byTitle() {
		searchType = TjConstants.SEARCH_TYPE_TITLE;
		
		return this;
	}
	
	public TjSongFinder bySinger() {
		searchType = TjConstants.SEARCH_TYPE_SINGER;
		
		return this;
	}
	
	public TjSongFinder byLyricist() {
		searchType = TjConstants.SEARCH_TYPE_LYRICIST;
		
		return this;
	}
	
	public TjSongFinder bySongWriter() {
		searchType = TjConstants.SEARCH_TYPE_SONG_WRITER;
		
		return this;
	}
	
	public TjSongFinder bySongNumber() {
		searchType = TjConstants.SEARCH_TYPE_SONG_NUMBER;
		
		return this;
	}
	
	/*
	 * Not supported.
	 * In the tj web page, you can search songs by lyrics without spacebar.
	 * If you type spacebar in search text, you would see database error messages in the web page.
	 * */
//	public TjSongFinder byLyrics() {
//		searchType = TjConstants.SEARCH_TYPE_LYRICS;
//		
//		return this;
//	}
	
	@Override
	protected void createRequest() {
		conn = Jsoup.connect(TjConstants.SEARCH_URL);
		conn.postDataCharset("utf-8");
		conn.data("searchOrderItem", "");
		conn.data("searchOrderType", "");
		conn.data("strCond", searchAccuracy);
		conn.data("strType", searchType);
		conn.data("strText", searchText);
		conn.data("intPage", (++page) + "");
		conn.data("strSize01", "100");
	}

	@Override
	protected Document sendRequest() throws IOException {
		return conn.post();
	}
}

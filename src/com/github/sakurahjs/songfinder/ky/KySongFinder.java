package com.github.sakurahjs.songfinder.ky;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.sakurahjs.songfinder.Escaper;
import com.github.sakurahjs.songfinder.SongBase;

public class KySongFinder extends SongBase {
	protected String searchType = "";
	protected String searchText = "";
	
	protected int page = 0;
	protected boolean withLyrics = true;
	
	public KySongFinder() {
		init();
	}
	
	@Override
	protected void init() {
		conn = null;
		songCountInRequest = 0;
		maxSongCountInRequest = 20;
		page = 0;
		withLyrics = true;
		
		this.searchType = KyConstants.SEARCH_TYPE_TITLE;
		
		documentSelector = KyConstants.DOCUMENT_SELECTOR;
	}
	
	public KySongFinder search(String searchText) throws UnsupportedEncodingException {
		this.searchText = URLEncoder.encode(searchText, "EUC-KR");
		return this;
	}
	
	public KySongFinder bySongNumber() {
		this.searchType = KyConstants.SEARCH_TYPE_SONG_NUMBER;
		return this;
	}
	
	public KySongFinder byTitle() {
		this.searchType = KyConstants.SEARCH_TYPE_TITLE;
		return this;
	}
	
	/*
	 * Not supported.
	 * It works only without spacebar in search text. Not usable.
	 */
//	public KySongFinder byLyrics() {
//		this.searchType = KyConstants.SEARCH_TYPE_LYRICS;
//		return this;
//	}
	
	public KySongFinder bySongWriter() {
		this.searchType = KyConstants.SEARCH_TYPE_SONG_WRITER;
		return this;
	}
	
	public KySongFinder byLyricist() {
		this.searchType = KyConstants.SEARCH_TYPE_LYRICIST;
		return this;
	}
	
	public KySongFinder bySinger() {
		this.searchType = KyConstants.SEARCH_TYPE_SINGER;
		return this;
	}
	
	public KySongFinder byTitleWithHighPrecision() {
		this.searchType = KyConstants.SEARCH_TYPE_TITLE_HIGH_PRECISION;
		return this;
	}
	
	public KySongFinder noLyrics() {
		this.withLyrics = false;
		return this;
	}
	
	@Override
	protected void createRequest() {
		String url = KyConstants.SEARCH_URL + "?page="+(++page)+"&sch_sel="+searchType+"&sch_txt="+searchText;
		conn = Jsoup.connect(url);
	}
	
	@Override
	protected Document sendRequest() throws IOException {
		Document result = null;
	
		try {
			result = conn.get();
		} catch (SocketTimeoutException ste) {
			System.out.println("KySongFinder.sendRequest() : "+ste.getMessage());
			System.out.println("retry");
			result = sendRequest();
		}
		
		return result;
	}
	
	@Override
	protected String createJsonElement(Elements e) {
		int number = Integer.parseInt( Escaper.html( e.get(0).selectFirst("em").text() ) );
		String title = e.get(1).selectFirst("[title]").attr("title");
		
		String lyrics = "";
		if (withLyrics) {
			lyrics = e.get(5).selectFirst(".lyricsCont").text();
			//cut off the title from lyrics because the lyrics contains the title at the head.
			lyrics = Escaper.doubleQuotes( lyrics.substring(title.length(), lyrics.length()).trim() );
		}
		
		//escape double quotes in title
		title = Escaper.doubleQuotes(title);
		String singer = Escaper.doubleQuotes(e.get(2).selectFirst("a").text());
		String songWriterAndLyricist = Escaper.doubleQuotes(e.get(3).text());

		return "{"+"\"number\" : "+number+", \"title\" : \""+title+"\", \"singer\" : \""+singer+"\", \"songWriterAndLyricist\" : \""+songWriterAndLyricist+ ( (withLyrics) ? "\", \"lyrics\" : \""+lyrics : "" ) +"\"}"; 
	}
	
	
}

package com.github.sakurahjs.songfinder.tj;

import java.io.IOException;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.github.sakurahjs.songfinder.Escaper;
import com.github.sakurahjs.songfinder.SongBase;

public class TjMonthlyPopularSongCollector extends SongBase {
	protected int year;
	protected int month;
	
	@Override
	protected void init() {
		conn = null;
		songCountInRequest = 0;
		maxSongCountInRequest = 100000;
		
		documentSelector = TjConstants.DOCUMENT_SELECTOR;
		
		Calendar now = Calendar.getInstance();
		year = now.get(Calendar.YEAR);
		month = now.get(Calendar.MONTH) + 1;
	}

	@Override
	protected void createRequest() {
		String url = TjConstants.MONTHLY_POPULAR_SONGS_URL;
		url = url.replace("YYYY", year+"");
		url = url.replace("MMMM", month+"");
		
		conn = Jsoup.connect(url);
	}

	@Override
	protected Document sendRequest() throws IOException {
		return conn.get();
	}
	
	@Override
	protected String createJsonElement(Elements e) {
		int rank = Integer.parseInt( Escaper.html( e.get(0).text() ) );
		int number = Integer.parseInt( Escaper.html( e.get(1).text() ) );
		String title = Escaper.doubleQuotes( e.get(2).text() );
		String singer = Escaper.doubleQuotes( e.get(3).text() );
		
		return "{"+"\"rank\": "+rank+", \"number\" : "+number+", \"title\" : \""+title+"\", \"singer\" : \""+singer+"\"}";
	}
}

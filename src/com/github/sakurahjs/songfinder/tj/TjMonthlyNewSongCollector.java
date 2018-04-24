package com.github.sakurahjs.songfinder.tj;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.github.sakurahjs.songfinder.SongBase;

public class TjMonthlyNewSongCollector extends SongBase {

	@Override
	protected void init() {
		conn = null;
		songCountInRequest = 0;
		maxSongCountInRequest = 100000;
		
		documentSelector = TjConstants.DOCUMENT_SELECTOR;
	}

	@Override
	protected void createRequest() {
		conn = Jsoup.connect(TjConstants.NEW_SONGS_URL);
	}

	@Override
	protected Document sendRequest() throws IOException {
		return conn.get();
	}

}

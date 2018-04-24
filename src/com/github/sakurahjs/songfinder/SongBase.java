package com.github.sakurahjs.songfinder;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class SongBase {
	protected Connection conn;
	protected int songCountInRequest;
	protected int maxSongCountInRequest;
	protected String documentSelector;
	
	public SongBase() {
		init();
	}
	
	protected abstract void init();
	protected abstract void createRequest();
	protected abstract Document sendRequest() throws IOException;
	
	protected String parse(Document doc) {
		songCountInRequest = 0;
		Elements trElements = doc.select(documentSelector);
		
		if (trElements.size() < 1) return "";
		
		trElements.remove(0);
		
		StringBuffer result = new StringBuffer();
		for (Element trElement : trElements) {
			Elements tdElements = trElement.select("td");
			
			if (tdElements.size() < 2) {
				break;
			}
			
			result.append(createJsonElement(tdElements));
			songCountInRequest++;
		}
		
		return result.toString();
	}
	
	protected String createJsonElement(Elements e) {
		int number = Integer.parseInt( Escaper.html( e.get(0).text() ));
		String title = Escaper.doubleQuotes( e.get(1).text() );
		String singer = Escaper.doubleQuotes( e.get(2).text() );
		String lyricist = Escaper.doubleQuotes( e.get(3).text() );
		String songWriter = Escaper.doubleQuotes( e.get(4).text() );
		
		return "{"+"\"number\" : "+number+", \"title\" : \""+title+"\", \"singer\" : \""+singer+"\", \"lyricist\" : \""+lyricist+"\", \"songWriter\" : \""+songWriter+"\"}";
	}
	
	public String execute() throws IOException {
		StringBuffer json = new StringBuffer("[");
		
		while(true) {
			createRequest();
			Document doc = sendRequest();
			String parsed = parse(doc);			
			json.append(parsed);
			
			if (songCountInRequest < maxSongCountInRequest) {
				break;
			}
		}
		
		json.append("]");
		init();
		
		return json.toString().replaceAll("\\}\\{", "}, {");
	}
}

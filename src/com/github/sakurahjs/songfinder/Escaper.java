package com.github.sakurahjs.songfinder;

public class Escaper {
	public static String html(String origin) {
		return origin.replaceAll("</?\\w+((\\s+\\w+(\\s*=\\s*(?:\".*?\"|'.*?'|[\\^'\">\\s]+))?)+\\s*|\\s*)/?>", "");
	}
	
	public static String doubleQuotes(String origin) {
		return origin.replaceAll("\"", "\\\\\\\\\\\\\"");
	}
}

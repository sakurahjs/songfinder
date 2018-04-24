package com.github.sakurahjs.songfinder.tj;

public class TjConstants {
	public static final String SEARCH_TYPE_TITLE = "1";
	public static final String SEARCH_TYPE_SINGER = "2";
	public static final String SEARCH_TYPE_LYRICIST = "4";
	public static final String SEARCH_TYPE_SONG_WRITER = "8";
	public static final String SEARCH_TYPE_SONG_NUMBER = "16";
	public static final String SEARCH_TYPE_LYRICS = "32";
	
	public static final String SEARCH_PRECISION_LOW = "0";
	public static final String SEARCH_PRECISION_HIGH = "1";
	
	public static final String SEARCH_URL = "http://www.tjmedia.co.kr/tjsong/song_search_list.asp";
	public static final String NEW_SONGS_URL = "http://www.tjmedia.co.kr/tjsong/song_monthNew.asp";
	public static final String MONTHLY_POPULAR_SONGS_URL = "http://www.tjmedia.co.kr/tjsong/song_monthPopular.asp?SYY=YYYY&amp;SMM=MMMM&amp;SDD=01";
	
	public static final String DOCUMENT_SELECTOR = "#BoardType1 table tbody tr";
}

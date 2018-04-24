package com.github.sakurahjs.songfinder.test;

public class SongModel {
	private int rank;
	private int number;
	private String title;
	private String singer;
	private String lyricist;
	private String songWriter;
	private String songWriterAndLyricist;
	private String lyrics;
	
	public SongModel() { 	}

	public SongModel(int rank, int number, String title, String singer, String lyricist, String songWriter,
			String songWriterAndLyricist, String lyrics) {
		super();
		this.rank = rank;
		this.number = number;
		this.title = title;
		this.singer = singer;
		this.lyricist = lyricist;
		this.songWriter = songWriter;
		this.songWriterAndLyricist = songWriterAndLyricist;
		this.lyrics = lyrics;
	}



	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}
	
	public String getLyricist() {
		return lyricist;
	}
	
	public void setLyricist(String lyricist) {
		this.lyricist = lyricist;
	}

	public String getSongWriter() {
		return songWriter;
	}

	public void setSongWriter(String songWriter) {
		this.songWriter = songWriter;
	}
	
	public String getSongWriterAndLyricist() {
		return songWriterAndLyricist;
	}

	public void setSongWriterAndLyricist(String songWriterAndLyricist) {
		this.songWriterAndLyricist = songWriterAndLyricist;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	
	public String toString() {
		return "[ rank : "+this.rank+", number : " + this.number + ", title : " + this.title + ", singer : " + this.singer + ", lyricist : " + this.lyricist + ", songWriter : " + this.songWriter + ", songWriterAndLyricist : " + this.songWriterAndLyricist + ", lyrics : " + this.lyrics +  " ]";
	}
}

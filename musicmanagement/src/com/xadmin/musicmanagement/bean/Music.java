package com.xadmin.musicmanagement.bean;

public class Music {
	private int id;
	private String title;
	private String Singers;
	private String Country;
	
	public Music(String title,String singers, String country) {
		super();
		this.title = title;
		Singers = singers;
		Country = country;
	}
	public Music(int id, String title, String singers, String country) {
		super();
		this.id = id;
		this.title = title;
		Singers = singers;
	
		Country = country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSingers() {
		return Singers;
	}
	public void setSingers(String singers) {
		Singers = singers;
	}
	
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	
	 
	
}

package zad3;

import javax.swing.Icon;

public class Book {
	public String author;
	public String title;
	public double price;
	public Icon cover;
	
	public Book(String author, String title, double price, Icon cover) {
		this.author = author;
		this.title = title;
		this.price = price;
		this.cover = cover;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public double getPrice() {
		return price;
	}
	
	public Icon getCover() {
		return cover;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setCover(Icon cover) {
		this.cover = cover;
	}
}

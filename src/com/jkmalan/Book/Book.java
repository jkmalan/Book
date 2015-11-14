package com.jkmalan.Book;

public class Book {
	
	private String title;
	private Page[] pages;
	private Page currentPage;
	private int currentPageNumber;
	
	public Book(String title, int numPages)
	{
		
		this.title = title;
		pages = new Page[numPages];
		currentPageNumber = 0;
		
		for(int i = 0; i < pages.length; i++)
			pages[i] = new Page();
		
		currentPage = pages[currentPageNumber];
		
	}
	
	public void setTitle(String in)
	{
		
		title = in;
		
	}
	
	public String getTitle()
	{
		
		return title;
		
	}
	
	public Page getCurrentPage()
	{
		
		return currentPage;
		
	}
	
	public void flipForwards()
	{
		
		if(currentPageNumber != pages.length-1)
			currentPage = pages[++currentPageNumber];
		
	}
	
	public void flipBackwards()
	{
		
		if(currentPageNumber != 0)
			currentPage = pages[--currentPageNumber];
		
	}
	
	public void flipToPage(int i)
	{
		
		currentPage = pages[(currentPageNumber = i)];
		
	}
	
	public String toString()
	{
		
		String output = "";
		
		for(int i = 0; i < pages.length; i++)
			output += (i+1) + ". " + pages[i] + "\n";
		
		return output;
		
	}
	
}

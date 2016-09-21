package com.jkmalan.Book;

public class Client {

	byte me;
	String cheese;
	int eger;
	double jeopardy;
	long john_silver;
	
	public static void main(String[] args)
	{
		
		Book book1 = new Book("gr8 gatsbi", 10);
		
		for(int i = 0;i < 10; i++)
		{
			
			book1.getCurrentPage().setContents("gr8 m8");
			book1.flipForwards();
			
		}
		
		System.out.println(book1);
		
	}
	
}

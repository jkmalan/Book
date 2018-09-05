package com.jkmalan.Book;

public class Client {

    byte me;
    String cheese;
    int eger;
    double jeopardy;
    long john_silver;

    public static void main(String[] args)
    {

        Book book = new Book("gr8 gatsbi", 10);

        for(int i = 0;i < 10; i++)
        {

            book.getCurrentPage().setContents("gr8 m8");
            book.flipForwards();

        }

        System.out.println(book);

    }
	
}

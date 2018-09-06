package com.jkmalan.Book;

import java.io.Serializable;
import java.util.ArrayList;

public class Book extends ArrayList<Page> implements Serializable{
	
    private String title;
    private int pageNum;

    public Book(String title, int numPages)
    {

        super(numPages);
        
        for(int i = 0; i < numPages; i++)
        {
            
            this.add(new Page());
            
        }
        
        this.title = title;
        pageNum = 0;

    }

    public void setTitle(String in){title = in;}
    public String getTitle(){return title;}

    public Page getCurrentPage(){return get(pageNum);}

    public void flipForwards(){

        pageNum = pageNum >= size() ? size() : pageNum+1;

    }

    public void flipBackwards(){

        pageNum = pageNum <= 0 ? 0 : pageNum-1;

    }

    public Page flipToPage(int i){

        pageNum = i;
        return get(pageNum);

    }
	
}

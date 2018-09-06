package com.jkmalan.Book;

import java.io.Serializable;
import java.util.ArrayList;

public class Book extends ArrayList<Page> implements Serializable{
	
    private String title;
    private int index;

    public Book(String title, int numPages)
    {

        super(numPages);
        
        for(int i = 0; i < numPages; i++)
        {
            
            this.add(new Page());
            
        }
        
        this.title = title;
        index = 0;

    }

    public void setTitle(String in){title = in;}
    public String getTitle(){return title;}

    public Page getCurrentPage(){return get(index);}
    public int getCurrentPageNumber(){return index;}
    
    public void flipForwards(){

        if(index >= size()-1)
            index = size()-1;
        else
            index++;
        
    }

    public void flipBackwards(){

        if(index <= 0)
            index = 0;
        else
            index--;
        
    }

    public Page flipToPage(int i){

        index = i;
        return get(index);

    }
	
}

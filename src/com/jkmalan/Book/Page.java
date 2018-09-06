package com.jkmalan.Book;

import java.io.Serializable;

public class Page implements Serializable{

    private String contents;

    public Page()
    {

        this("");

    }

    public Page(String in)
    {

        contents = in;

    }

    public void setContents(String in)
    {

        contents = in;

    }

    public String getContents()
    {

        return contents;

    }

    public boolean equals(Page other)
    {

        return this.getContents().equals(other.getContents());

    }

    public String toString()
    {

        return contents;

    }
	
}

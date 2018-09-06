/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkmalan.App;

import com.jkmalan.Book.Book;

/**
 * This class handles all interactions 
 * between background data and the GUI
 * 
 * @author Kuba
 */
public class DataManager {
    
    final App mainApp;
    
    Book book;
    
    DataManager(App app){
        
        mainApp = app;
        
    }
    
    public Book getData(){
    
        return book;
    
    }
    
    public void setData(Book data){
        
        book = data;
        
    }
    
}

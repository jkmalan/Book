/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkmalan.App;

import com.jkmalan.Book.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This class handles all interactions between
 * the background data and the any files in the
 * system.
 * 
 * @author Kuba
 */
public class FileManager {
    
    final App mainApp;
    
    FileManager(App app){
        
        mainApp = app;
        
    }
    
    public Book loadDataFromFile(File file) throws FileNotFoundException, IOException {
        
        if(file == null)
            return null;
        
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        
        Book data = null;
        
        try{
        
            data = (Book)ois.readObject();
        
        }catch(ClassNotFoundException e){
            
            System.out.println("Error: " + e.getMessage());
            
        }
        
        return data;
        
    }
    
}

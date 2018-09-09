/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkmalan.App;

import static com.jkmalan.App.Constants.*;
import com.jkmalan.Book.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    
    /**
     * Reads an object file and loads it into the application.
     * 
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public Book loadDataFromFile(File file) throws FileNotFoundException, IOException {
        
        if(file == null)
            return null;
        
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        
        Book data = null;
        
        try{
        
            data = (Book)ois.readObject();
        
        }catch(ClassNotFoundException cnfe){
            
            System.out.println("Error: " + cnfe.getMessage());
            
        }finally{
        
            ois.close();
        
        }
        
        return data;
        
    }
    
    /**
     * 
     * @param fileName
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveDataToFile(String fileName) throws FileNotFoundException, IOException {
    
        //String fileName = mainApp.getDataManager().getData().getTitle().replaceAll(" ", "_");
        
        String fullFileName = DATA_PATH + fileName + EXTENSION;
        
        System.out.println(fullFileName);
        
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fullFileName)));
        
        oos.writeObject(mainApp.getDataManager().getData());
        
        oos.close();
        
    }
    
}

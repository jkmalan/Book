/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkmalan.App;

import com.jkmalan.Book.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.stage.FileChooser;

/**
 * Handles all interactions between 
 * the user and the GUI.
 * 
 * @author Kuba
 */
public class Controller {
    
    final App mainApp;
    
    Controller(App app){
        
        mainApp = app;
        
    }
    
    public void handleFileButtonClicked(){
        
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("./"));
        
        File file = fc.showOpenDialog(mainApp.getMainStage());
        
        Book data = null; 
        
        try{
        
            data = mainApp.getFileManager().loadDataFromFile(file);
        
        }catch(FileNotFoundException fe){
        
            System.out.println("The file could not be found!");
        
        }catch(IOException ioe){
            
            System.out.println("IOException: " + ioe.getMessage());
            
        }
        
        if(data != null){
        
            mainApp.getDataManager().setData(data);
            mainApp.updateGUI();
        
        }else{
            
            System.out.println("Error! The file could not be opened or read.");
            
        }
        
    }
    
    public void handleNextButtonClicked(){
        
        mainApp.getDataManager().getData().flipForwards();
        mainApp.updateGUI();
        
    }
    
    public void handlePrevButtonClicked(){
        
        mainApp.getDataManager().getData().flipBackwards();
        mainApp.updateGUI();
        
    }
    
}

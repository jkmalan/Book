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
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
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
            mainApp.disableNavButtons(false);
            mainApp.disableEditableButton(false);
            mainApp.disableSaveButton(false);
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
 
    public void handleNewButtonClicked(){
        
        //TODO
        
        Alert alert = new Alert(AlertType.CONFIRMATION, "Create a new book? The currently loaded book will not be saved", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if(alert.getResult() == ButtonType.YES){
            
            //Clear out the GUI and set everything to be editable
            //Pop up an input dialog?
            //TODO
            System.out.println("Do stuff");
            
        }else{
            
            System.out.println("No was pressed.");
            
        }
        
    }
    
    public void handleEditButtonClicked(){
        
        //Flips the mode of this application
        mainApp.setEditable(!mainApp.getEditable());
        
    }
    
    public void handleSaveButtonClicked(){
    
        Alert alert = new Alert(AlertType.WARNING, "Warning: Saved changes cannot be undone. Still save?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        
        Optional<String> result;
        
        if(alert.getResult() == ButtonType.YES){
            
            TextInputDialog inputDialog = new TextInputDialog("test");
            inputDialog.setTitle("File Name");
            inputDialog.setHeaderText("Enter a name for your .book file (without extension)");
            inputDialog.setContentText("File Name:");

            result = inputDialog.showAndWait();
            
            if(!result.isPresent())
                return;
            
            try{
            
                mainApp.getFileManager().saveDataToFile(result.get());
            
            }catch(FileNotFoundException fe){
                
                System.out.println("The destination file could not be found!");
                
            }catch(IOException ioe){
                
                System.out.println("IOException: " + ioe.getMessage());
                
            }
            
        }
        
    }
    
}

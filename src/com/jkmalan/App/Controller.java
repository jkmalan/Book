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
        fc.setInitialDirectory(new File("./data/"));
        
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
        
            System.out.println("File " + file.getName() + " successfully loaded");
            
        }else{
            
            System.out.println("Error! The file could not be opened or read.");
            
        }
        
    }
    
    public void handleNextButtonClicked(){
        
        mainApp.getDataManager().saveDataFromGUI();
        mainApp.getDataManager().getData().flipForwards();
        mainApp.updateGUI();
        
    }
    
    public void handlePrevButtonClicked(){
        
        mainApp.getDataManager().saveDataFromGUI();
        mainApp.getDataManager().getData().flipBackwards();
        mainApp.updateGUI();
        
    }
 
    public void handleNewButtonClicked(){
        
        //TODO
        
        Alert alert = new Alert(AlertType.CONFIRMATION, "Create a new book? The currently loaded book will not be saved", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if(alert.getResult() == ButtonType.YES){
            
            int newPageCount = 0;
            String title = "";
            
            TextInputDialog inputDialog = new TextInputDialog();
            Optional<String> result;
            
            //Read title
            do{
            
                inputDialog.setTitle("Title");
                inputDialog.setHeaderText("Enter a title for the new book");
                inputDialog.setContentText("Title:");

                result = inputDialog.showAndWait();
            
                if(!result.isPresent())
                    continue;
                
            }while(!titleIsValid(result.get()));
               
            title = result.get();
            
            //Read page count            
            do{
            
                inputDialog.setTitle("Page Count");
                inputDialog.setHeaderText("Enter the number of pages for the new book");
                inputDialog.setContentText("Page Count:");
                
                result = inputDialog.showAndWait();
                
                if(!result.isPresent())
                    continue;
                
            }while(!pageCountIsValid(result.get()));
            
            newPageCount = Integer.valueOf(result.get());
            
            mainApp.getDataManager().setData(new Book(title, newPageCount));
            mainApp.updateGUI();
            mainApp.disableNavButtons(false);
            mainApp.disableSaveButton(false);
            mainApp.disableEditableButton(false);
            
        }else{
            
            System.out.println("No was pressed.");
            
        }
        
    }
    
    /**
     * 
     * @param title
     * @return 
     */
    private boolean titleIsValid(String title){

        return title != null && !title.isEmpty();

    }
    
    /**
     * 
     * @param pageCount
     * @return 
     */
    private boolean pageCountIsValid(String pageCount){
    
        try{
            
            int temp = Integer.valueOf(pageCount);
            //Check if the number is below 0
            if(temp <= 0)
                return false;
            
        }catch(NumberFormatException nfe){
            
            //Return false since the string isnt a number
            return false;
            
        }
        
        //If none of the conditions were tripped, the pagenumber is fine
        return true;
    
    }
    
    public void handleEditButtonClicked(){
        
        //Flips the mode of this application
        mainApp.setEditable(!mainApp.getEditable());
        
    }
    
    public void handleSaveButtonClicked(){
    
        Alert alert = new Alert(AlertType.WARNING, "Warning: Saved changes cannot be undone. Still save?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        
        if(alert.getResult() == ButtonType.YES){
            
            TextInputDialog inputDialog = new TextInputDialog("test");
            inputDialog.setTitle("File Name");
            inputDialog.setHeaderText("Enter a name for your .book file (without extension)");
            inputDialog.setContentText("File Name:");

            Optional<String> result = inputDialog.showAndWait();
            
            if(!result.isPresent())
                return;
            
            try{
            
                mainApp.getFileManager().saveDataToFile(result.get());
                System.out.println("File saved to ./data/" + result.get());
                
            }catch(FileNotFoundException fe){
                
                System.out.println("The destination file could not be found!");
                
            }catch(IOException ioe){
                
                System.out.println("IOException: " + ioe.getMessage());
                
            }
            
        }
        
    }
    
}

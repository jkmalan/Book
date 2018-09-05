package com.jkmalan.Book;

import static com.jkmalan.Book.Constants.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Client extends Application{
    
    private TextArea pageContents;
    private Label pageNum, sizeLbl, titleLbl;
    private Button prevButton, nextButton;
    private Stage mainStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    
        //Application starts here
        mainStage = primaryStage;
        
        initComponents();
        setupGUI();
        setupHandlers();
        
        mainStage.show();
        
    }
    
    //Initializes the puzzle pieces for this GUI
    private void initComponents(){
        
        mainStage.setTitle(APP_TITLE);
        
        pageContents = new TextArea("");
        pageContents.setEditable(false);
        pageContents.setPrefSize(200, 200);
        
        pageNum = new Label(NUM_LABEL_DEFAULT_TEXT);
        pageNum.setMinWidth(20);
        
        prevButton = new Button(PREV_BUTTON_DEFAULT_TEXT);
        nextButton = new Button(NEXT_BUTTON_DEFAULT_TEXT);
        
        prevButton.setMinSize(20, 20);
        nextButton.setMinSize(20, 20);
        
        sizeLbl = new Label();
        titleLbl = new Label(TITLE_LABEL_DEFAULT_TEXT);
        
    }
    
    //Puts the puzzle pieces together
    private void setupGUI(){
        
        VBox mainPane = new VBox();
        mainPane.getChildren().add(sizeLbl);
        //mainPane.setBorder(createBorder(Color.PURPLE));
        
        StackPane topStackPane = new StackPane();
        topStackPane.setAlignment(Pos.CENTER);
        topStackPane.getChildren().add(titleLbl);
        
        System.out.println((double)DEFAULT_WINDOW_HEIGHT * .28);
        
        topStackPane.setPrefSize(mainPane.getWidth(), DEFAULT_WINDOW_HEIGHT*.13);
        //topStackPane.setBorder(createBorder(Color.BLUE));
        
        HBox middleBox = new HBox(3);
        
        StackPane s1 = new StackPane(), s2 = new StackPane(), s3 = new StackPane();
        
        s1.setAlignment(Pos.CENTER_RIGHT);
        s2.setAlignment(Pos.CENTER);
        s3.setAlignment(Pos.CENTER_LEFT);
        
        s1.getChildren().add(prevButton);
        s2.getChildren().add(pageContents);
        s3.getChildren().add(nextButton);
        
        s1.setPrefWidth(DEFAULT_WINDOW_WIDTH*.15);
        s2.setPrefWidth(DEFAULT_WINDOW_WIDTH*.7);
        s3.setPrefWidth(DEFAULT_WINDOW_WIDTH*.15);
        
        middleBox.setPrefSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT*.7);
        
        middleBox.getChildren().addAll(s1, s2, s3);
        //middleBox.setBorder(createBorder(Color.RED));
        
        StackPane bottomStackPane = new StackPane();
        bottomStackPane.setAlignment(Pos.CENTER);
        bottomStackPane.getChildren().add(pageNum);
        //bottomStackPane.setBorder(createBorder(Color.GREEN));
        
        bottomStackPane.setPrefSize(mainPane.getWidth(), DEFAULT_WINDOW_HEIGHT*.13);
        
        mainPane.getChildren().addAll(topStackPane, middleBox, bottomStackPane);
        
        mainStage.setHeight(DEFAULT_WINDOW_HEIGHT);
        mainStage.setWidth(DEFAULT_WINDOW_WIDTH);
        mainStage.setResizable(false);
        
        mainStage.setScene(new Scene(mainPane));
        
        updateSizeLabel();
        
    }
    
    //Used for debugging purposes to figure out where sections of the GUI start and end
    private Border createBorder(Color color){
        
        return new Border(
                new BorderStroke(
                        Paint.valueOf(color + ""), 
                        BorderStrokeStyle.SOLID, 
                        CornerRadii.EMPTY, 
                        BorderWidths.DEFAULT));
        
    }
    
    //Sets up any event handlers or listeners we need
    private void setupHandlers(){
        
        mainStage.widthProperty().addListener((obs, oldval, newval) -> {
        
            updateSizeLabel();
        
        });
        
        mainStage.widthProperty().addListener((obs, oldval, newval) -> {
        
            updateSizeLabel();
        
        });
        
    }
    
    @Override
    public void stop(){
        
        //Code to execute when the exit button here
        
    }

    //Updates the size label. Happens syncronously with any other threads doing work
    private void updateSizeLabel(){
        
        Platform.runLater(() -> {
            
                sizeLbl.setText(mainStage.getWidth() + " x " + mainStage.getHeight());
            
        });
        
    }
    
    //Not used
    public static void main(String[] args){launch(args);}
    
}

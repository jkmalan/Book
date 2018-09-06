package com.jkmalan.App;

import static com.jkmalan.App.Constants.*;
import com.jkmalan.Book.Book;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
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

public class App extends Application{
    
    private TextArea pageContents;
    private Label pageNum, sizeLbl, titleLbl;
    private Button prevButton, nextButton, loadFileButton;
    private Stage mainStage;
    
    private DataManager data;
    private FileManager file;
    
    /**
     * This is what is executed at the start of the program.
     * 
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
    
        //Application starts here
        mainStage = primaryStage;
        
        data = new DataManager(this);
        file = new FileManager(this);
        
        initComponents();
        setupGUI();
        setupHandlers();
        
        mainStage.show();
        
    }
    
    /**
     * Initializes the puzzle pieces for this GUI.
     */
    private void initComponents(){
        
        mainStage.setTitle(APP_TITLE);
        
        pageContents = new TextArea("");
        pageContents.setEditable(false);
        pageContents.setPrefSize(200, 200);
        
        pageNum = new Label(NUM_LABEL_DEFAULT_TEXT);
        pageNum.setMinWidth(20);
        
        prevButton = new Button(PREV_BUTTON_DEFAULT_TEXT);
        nextButton = new Button(NEXT_BUTTON_DEFAULT_TEXT);
        loadFileButton = new Button(LOAD_FILE_BUTTON_DEFAULT_TEXT);
        
        
        prevButton.setMinSize(20, 20);
        nextButton.setMinSize(20, 20);
        
        sizeLbl = new Label();
        titleLbl = new Label(TITLE_LABEL_DEFAULT_TEXT);
        
    }
    
    /**
     * Puts the puzzle pieces together.
     */
    private void setupGUI(){
        
        VBox mainPane = new VBox();
        mainPane.setPadding(new Insets(5));
        mainPane.getChildren().addAll(/*sizeLbl, */loadFileButton);
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
    
    /**
     * Used for debugging purposes to figure out where sections of the GUI start and end
     * 
     * @param color
     * @return 
     */
    private Border createBorder(Color color){
        
        return new Border(
                new BorderStroke(
                        Paint.valueOf(color + ""), 
                        BorderStrokeStyle.SOLID, 
                        CornerRadii.EMPTY, 
                        BorderWidths.DEFAULT));
        
    }
    
    /**
     * Sets up any handlers we need
     */
    private void setupHandlers(){
        
        Controller controller = new Controller(this);
        
        loadFileButton.setOnMouseClicked(e -> {
        
            controller.handleFileButtonClicked();
        
        });
        
        nextButton.setOnMouseClicked(e -> {
        
            controller.handleNextButtonClicked();
        
        });
        
        prevButton.setOnMouseClicked(e -> {
        
            controller.handlePrevButtonClicked();
        
        });
        
    }

    /**
     * Updates the size label. Happens synchronously with any other threads doing work
     */
    private void updateSizeLabel(){
        
        Platform.runLater(() -> {
            
                sizeLbl.setText(mainStage.getWidth() + " x " + mainStage.getHeight());
            
        });
        
    }
    
    public void updateGUI(){
        
        Platform.runLater(() -> {
        
            pageContents.clear();
            pageNum.setText("");
            titleLbl.setText("");
            
            pageNum.setText(data.getData().getCurrentPageNumber() + 1 +"");
            pageContents.setText(data.getData().getCurrentPage().getContents());
            titleLbl.setText(data.getData().getTitle());
            
        });
        
    }
    
    public FileManager getFileManager(){return file;}
    public DataManager getDataManager(){return data;}
    public Stage getMainStage(){return mainStage;}
    
    //Not used
    public static void main(String[] args) throws Exception{
        
        launch(args);
        
        //The code below was used to create a sample book to open
        
        /*
        System.out.println("Hello world");
        
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("./data/book1.book")));
        
        Book book = new Book("This is the title of the book", 5);
        
        book.get(0).setContents("This is the first page.");
        book.get(1).setContents("This is the second page.");
        book.get(2).setContents("This is the third page.");
        book.get(3).setContents("This is the fourth page.");
        book.get(4).setContents("This is the fifth page.");
        
        oos.writeObject(book);
        
        System.exit(0);
        */
    }
    
}

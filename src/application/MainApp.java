package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;

import application.controllers.GameLoop;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    
    @FXML
    private BorderPane pane;
    
    Group group = new Group();
    Group snakeGroup = new Group();;
    Group frogGroup = new Group();
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();
        showPersonOverview();      	   
        primaryStage.show() ; 
           
        showPlayground(12, 12);     
        new GameLoop(snakeGroup, frogGroup, rootLayout).start();
      
    }
    
    
    private void showPlayground(int weight, int height) {
    	int x = 0;
    	int y = 0;
    	
    	for(int i = 0 ; i < weight; i++) {  
            for(int j = 0 ; j < height; j++) {
            	Rectangle rectangle = new Rectangle();  
	            rectangle.setX(x) ; 
	            rectangle.setY(y) ;
	            y+=30;
	            rectangle.setWidth(30); 
	            rectangle.setHeight(30);  

	            rectangle.setFill(Paint.valueOf("WHITE"));
	            rectangle.setStrokeWidth(1);
	            rectangle.setStroke(Paint.valueOf("Black"));
	            
	            group.getChildren().add(rectangle);
            }
            x+=30;
            y = 0;
           } 
    }
    

    /**
     * Инициализирует корневой макет.
     */
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
           
            

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Показывает в корневом макете сведения об адресатах.
     */
    public void showPersonOverview() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/Playground.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            pane = (BorderPane) personOverview.getChildren().get(4);
            pane.getChildren().add(group);
            pane.getChildren().add(snakeGroup);
            pane.getChildren().add(frogGroup);
            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает главную сцену.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

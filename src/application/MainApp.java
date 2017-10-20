package application;

import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.Color;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    Group group = new Group();
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
        
        
//        int j = 10;
//        for(int i = 0 ; i < 10; i++) {
//        Rectangle rectangle = new Rectangle() ;  
//        rectangle.setX(0 + j) ; 
//        rectangle.setY(75.0f) ; 
//        rectangle.setWidth(30.0f) ; 
//        rectangle.setHeight(15.0f) ;  
//        
//        group.getChildren().add(rectangle);
//        j += 50;
//        }     
//        
//        
//        //Creating a scene object 
//       // Scene scene = new Scene(root, 600, 300) ;  
//        
//        j = 10;
//        for(int i = 0 ; i < 10; i++) {
//            Rectangle rectangle = new Rectangle() ;  
//            rectangle.setX(0 + j) ; 
//            rectangle.setY(115) ; 
//            rectangle.setWidth(15) ; 
//            rectangle.setHeight(15.0f) ;  
//            
//            group.getChildren().add(rectangle);
//            j += 50;
//            }     
//        Rectangle rectangle = new Rectangle() ;  
//        rectangle.setX(0) ; 
//        rectangle.setY(175.0f) ; 
//        rectangle.setWidth(30.0f) ; 
//        rectangle.setHeight(15.0f) ;  
//        
//        group.getChildren().add(rectangle);
//    
//        
//        //Setting title to the Stage 
//        
//           
//        //Adding scene to the stage 
//       // primaryStage.setScene(scene) ; 
//           
//        //Displaying the contents of the stage 
        primaryStage.show() ; 
        showPlayground(25, 25);
        
      
    }
    
    
    private void showMenu() {
    	    
    }
    
    private void showPlayground(int weight, int height) {
    	int x = 0;
    	int y = 0;
    	
    	for(int i = 0 ; i < weight; i++) {  
            for(int j = 0 ; j < height; j++) {
            	Rectangle rectangle = new Rectangle();  
	            rectangle.setX(x) ; 
	            rectangle.setY(y) ;
	            y+=15;
	            rectangle.setWidth(15); 
	            rectangle.setHeight(15);  

	            rectangle.setFill(Paint.valueOf("WHITE"));
	            rectangle.setStrokeWidth(1);
	            rectangle.setStroke(Paint.valueOf("Black"));
	            
	            
	            group.getChildren().add(rectangle);
            }
            x+=15;
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
            rootLayout.getChildren().add(group);

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
           
            
            
            //Creating a scene object 
            
            
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

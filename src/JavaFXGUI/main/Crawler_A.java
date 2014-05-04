/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXGUI.main;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 *
 * @author sick
 */
public class Crawler_A {
     private static void initAndShowGUI() {
        // This method is invoked on the EDT thread
        JFrame frame = new JFrame("Swing and JavaFX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
       
        frame.setSize(300, 200);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    
      
        

        

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
       });
    }

    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
         Scene scene = createScene();
        fxPanel.setScene(scene);
        

        
        
    }

    private static Scene createScene() {
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root, Color.ALICEBLUE);
        final Text  text  =  new  Text();
        
        text.setX(40);
        text.setY(100);
        text.setFont(new Font(25));
        text.setText("Welcome JavaFX!");
      //  TableView v=new TableView();
        
        //root.getChildren().add(text);
        
        //Image image = new Image("file:///home/sick/Downloads/111.png");
       // final ImageView iv1 = new ImageView();
        //iv1.setImage(image);
        

        final ContextMenu cm = new ContextMenu();
        MenuItem cmItem1 = new MenuItem("Copy Image");
        cmItem1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
              //  Clipboard clipboard = Clipboard.getSystemClipboard();
               // ClipboardContent content = new ClipboardContent();
               // content.putImage(pic.getImage());
               // clipboard.setContent(content);
                ;
                                           }
                            });

        cm.getItems().add(cmItem1);
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED,
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            if (e.getButton() == MouseButton.SECONDARY)  
                cm.show(text,e.getScreenX(), e.getScreenY());
              //  System.out.print("((((((((((((((((((((((((");
        }
                        });

        
        //root.getChildren().add(iv1);
        root.getChildren().add(text);
        return (scene);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
            }
        });
    }
}

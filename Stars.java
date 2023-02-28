package assignment01_000315902;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import static javafx.application.Application.launch;


/**
 * Using JavaFX a graphical interface is created. Program used to create
 * star constellation on Java FX canvas. User is prompted for number of stars and x and y
 * co-ordinates. Constellation is nameable.Graphics Context used to draw lines and stars.
 *
 * @author Armand Amores ID: 000315902
 * Date: 30/01/2023
 * Class: COMP-10062
 */
public class Stars extends Application {

    /**
     * Start method (use this instead of main).
     *
     * @param stage The FX stage to draw on
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        Canvas canvas = new Canvas(1000, 700); // Set canvas Size in Pixels
        root.getChildren().add(canvas);
        stage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // YOUR CODE STARTS HERE

        //How to make dialog box, string of how many stars parsed into int data type
        int numberOfStars = 0;
        String constellation = JOptionPane.showInputDialog("Create a Constellation. " + "Enter the number of stars: ");

        //If user enters invalid character, try initial input, catch exception
        try {
            numberOfStars = Integer.parseInt(constellation);
        } catch (NumberFormatException e) {
            constellation = JOptionPane.showInputDialog("Please enter an integer.\nCreate a Constellation. " + "Enter the number of stars: ");
            numberOfStars = Integer.parseInt(constellation); }

        //Set window title
        String title = JOptionPane.showInputDialog("Name your Constellation: ");
        stage.setTitle(title);

        //How to make array, one for x and y up to numberOfStars
        int[] xCoord = new int [numberOfStars];
        int[] yCoord = new int [numberOfStars];

        //for numberOfStars, input boxes will appear asking for x,y values. Single String input is split into 2 variables
        //and parsed into integers. Each value gets added to x and y array above.
        for (int i = 0; i < numberOfStars; i++) {
            String coord = "";
            String[] parts = {};
            // if user enters values outside of range 2, invalid response try again
            while(parts.length !=2 ) {
                coord = JOptionPane.showInputDialog(("Enter coordinates of star " + (i+1)+ " eg. (x,y)"));
                parts = coord.split(",");
                if(parts.length != 2) {
                    JOptionPane.showMessageDialog(null, "Invalid characters. Please enter integers in (x,y) format.");
                }
            }
            //split method saved to parts individually
            String x = parts[0];
            String y = parts[1];

            //parts x and y to be converted to integer, set variables so that they are within scope of try and catch
            int xPos = 0;
            int yPos = 0;

            // if user enters a invalid character within x,y values
            try {
                xPos = Integer.parseInt(x);
                yPos = Integer.parseInt(y);
            } catch (NumberFormatException e) {
                coord = JOptionPane.showInputDialog(("Invalid character, Use Integers. \nEnter coordinates of star " + (i+1)+ " eg. (x,y)"));
                parts = coord.split(",");
                x = parts[0];
                y = parts[1];
                xPos = Integer.parseInt(x);
                yPos = Integer.parseInt(y);

            }


            //Setting x values to width of canvas, values out of range: invalid ask again
            if ((xPos > 0) && (xPos < 1000)) {
                xCoord[i] = xPos;
            } else {
                coord = JOptionPane.showInputDialog(("Please enter x value within range (0-1000) \nEnter coordinates of star " + (i+1)+ " eg. (x,y)"));
                parts = coord.split(",");
                x = parts[0];
                xCoord[i] = Integer.parseInt(x);
            }
            //Setting y values to height of canvas
            if ((yPos > 0) && (yPos < 700) ){
                yCoord[i] = yPos;
            } else {
                coord = JOptionPane.showInputDialog(("Please enter y value within range (0-700)\nEnter coordinates of star " + (i+1)+ " eg. (x,y)"));
                parts = coord.split(",");
                y = parts[1];
                yCoord[i] = Integer.parseInt(y);
            }
        }


        //Prints lines of constellation
        for (int i = 0; i < numberOfStars-1; i++) {
            int o = 2; // offset to centre star
            gc.setStroke(Color.YELLOWGREEN);
            gc.strokeLine((xCoord[i] + o),(yCoord[i] + o),(xCoord[i+1] + o),(yCoord[i+1] + o));

        }



        //Prints coordinate star
        for (int i = 0; i < numberOfStars; i++) {
            gc.setFill(Color.YELLOWGREEN);
            gc.fillOval(xCoord[i],yCoord[i],5,5 );
        }

        //Connect the first star with the last star
        gc.strokeLine(xCoord[0], yCoord[0], xCoord[numberOfStars-1], yCoord[numberOfStars -1]);

        //Display random generated stars
        for (int i = 0; i <= 1000; i++) {
            double x = Math.random() * canvas.getWidth();
            double y = Math.random() * canvas.getHeight();
            double size = Math.random() * 5;

            gc.setFill(Color.WHITE);
            gc.fillOval(x, y, size, size);
        }

        //Prints name of program and creator in top left corner
        gc.setFill(Color.WHITE);
        gc.fillText("\nStar Program\nBy: Armand Amores",1,1);

        //Prints Array in terminal, useful to understand how for loop corresponds with index
        System.out.println("x: " + Arrays.toString(xCoord));
        System.out.println("y: " + Arrays.toString(yCoord));


        // YOUR CODE STOPS HERE
        stage.show();
    }

    /**
     * The actual main method that launches the app.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}

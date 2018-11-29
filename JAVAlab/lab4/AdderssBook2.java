
import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AdderssBook2 extends Application {
	 // Specify the size of five string fields in the record
	  final static int NAME_SIZE = 32;
	  final static int STREET_SIZE = 32;
	  final static int CITY_SIZE = 20;
	  final static int STATE_SIZE = 2;
	  final static int ZIP_SIZE = 5;
	  final static int RECORD_SIZE =
	    (NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);

	  // Access address.dat using RandomAccessFile
	  private RandomAccessFile raf;

	  // Text fields
	  private TextField jtfName = new TextField();
	  private TextField jtfStreet = new TextField();
	  private TextField jtfCity = new TextField();
	  private TextField jtfState = new TextField();
	  private TextField jtfZip = new TextField();

	  // Buttons
	  private Button jbtAdd = new Button("Add");
	  private Button jbtFirst = new Button("First");
	  private Button jbtNext = new Button("Next");
	  private Button jbtPrevious = new Button("Previous");
	  private Button jbtLast = new Button("Last");

@Override // Override the start method in the Application class
  	public void start(Stage primaryStage) {
		 // Open or create a random access file
	    try {
	      raf = new RandomAccessFile("address.dat", "rw");
	    }
	    catch(IOException ex) {
	      System.out.print("Error: " + ex);
	      System.exit(0);
	    }
		
	    // Pane p1 for holding labels Name, Street, and City
	    GridPane p1 = new GridPane();
	   	p1.add(new Label("Name"),0,0);
	    p1.add(new Label("Street"),0,1);
	    p1.add(new Label("City"),0,2);
	    p1.setVgap(20);
	    
	    // Pane jpState for holding state
	    BorderPane jpState = new BorderPane();
	    jpState.setLeft(new Label("State"));
	    jpState.setCenter(jtfState);

	    // Pane jpZip for holding zip
	    BorderPane jpZip = new BorderPane();
	  	jpZip.setLeft(new Label("Zip"));
	    jpZip.setCenter(jtfZip);
	    
	    // Pane p2 for holding jpState and jpZip
	    BorderPane p2 = new BorderPane();
	    p2.setLeft(jpState);
	    p2.setCenter(jpZip);

	    // Panel p3 for holding jtfCity and p2
	    BorderPane p3 = new BorderPane();
	    p3.setCenter(jtfCity);
	    p3.setRight(p2);

	    // Pane p4 for holding jtfName, jtfStreet, and p3
	    GridPane p4 = new GridPane();
	    p4.add(jtfName,0,0);
	    p4.add(jtfStreet,0,1);
	    p4.add(p3,0,2);
	    p4.setVgap(5);

	    // Place p1 and p4 into jpAddress
	    BorderPane jpAddress = new BorderPane();
	    jpAddress.setLeft(p1);
	    jpAddress.setCenter(p4);

	    // Set the panel with line border
	  // jpAddress.setBorder(new BevelBorder(BevelBorder.RAISED));

	    // Add buttons to a pane
	    FlowPane jpButton = new FlowPane();
	    jpButton.getChildren().add(jbtAdd);
	    jpButton.getChildren().add(jbtFirst);
	    jpButton.getChildren().add(jbtNext);
	    jpButton.getChildren().add(jbtPrevious);
	    jpButton.getChildren().add(jbtLast);
	    jpButton.setHgap(30);
	    jpButton.setPadding(new Insets(10,10,10,50));
	    
	    // Add jpAddress and jpButton to the pane
	    BorderPane pane = new BorderPane();
	    pane.setCenter(jpAddress);
	    pane.setBottom(jpButton);
   
	    //create and register the handler with lambda expression
	    jbtAdd.setOnAction(e -> writeAddress());
	    
	    jbtFirst.setOnAction(e -> {
	          try {
	            if (raf.length() > 0) readAddress(0);
	          }
	          catch (IOException ex) {
	            ex.printStackTrace();
	          }
	      });
	    
	    jbtNext.setOnAction(e -> {
	         try {
	            long currentPosition = raf.getFilePointer();
	            if (currentPosition < raf.length())
	              readAddress(currentPosition);
	          }
	          catch (IOException ex) {
	            ex.printStackTrace();
	          }
	      });
	     
	    jbtPrevious.setOnAction(e -> {
	    	try {
	            long currentPosition = raf.getFilePointer();
	            if (currentPosition - 2 * RECORD_SIZE > 0)
	              // Why 2 * 2 * RECORD_SIZE? See the follow-up remarks
	              readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
	            else
	              readAddress(0);
	          }
	          catch (IOException ex) {
	            ex.printStackTrace();
	          }
	      });
	    
	      jbtLast.setOnAction(e -> {
	          try {
	            long lastPosition = raf.length();
	            if (lastPosition > 0)
	              // Why 2 * RECORD_SIZE? See the follow-up remarks
	              readAddress(lastPosition - 2 * RECORD_SIZE);
	          }
	          catch (IOException ex) {
	            ex.printStackTrace();
	          }
			});	      
	    	      // Display the first record if exists
	      try {
	        if (raf.length() > 0) readAddress(0);
	      }
	      catch (IOException ex) {
	        ex.printStackTrace();
	      }
	   
	// place it in the scene
	    Scene scene = new Scene(pane);
	    primaryStage.setTitle("Addressbook"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
  }

  

	/** Write a record at the end of the file */
	public void writeAddress() {
	  try {
	    raf.seek(raf.length());
	    FixedLengthStringIO.writeFixedLengthString(
	      jtfName.getText(), NAME_SIZE, raf);
	    FixedLengthStringIO.writeFixedLengthString(
	      jtfStreet.getText(), STREET_SIZE, raf);
	    FixedLengthStringIO.writeFixedLengthString(
	      jtfCity.getText(), CITY_SIZE, raf);
	    FixedLengthStringIO.writeFixedLengthString(
	      jtfState.getText(), STATE_SIZE, raf);
	    FixedLengthStringIO.writeFixedLengthString(
	      jtfZip.getText(), ZIP_SIZE, raf);
	  }
	  catch (IOException ex) {
	    ex.printStackTrace();
	  }
	}
	
	/** Read a record at the specified position */
	public void readAddress(long position) throws IOException {
	  raf.seek(position);
	  String name = FixedLengthStringIO.readFixedLengthString(
	    NAME_SIZE, raf);
	  String street = FixedLengthStringIO.readFixedLengthString(
	    STREET_SIZE, raf);
	  String city = FixedLengthStringIO.readFixedLengthString(
	    CITY_SIZE, raf);
	  String state = FixedLengthStringIO.readFixedLengthString(
	    STATE_SIZE, raf);
	  String zip = FixedLengthStringIO.readFixedLengthString(
	    ZIP_SIZE, raf);
	
	  jtfName.setText(name);
	  jtfStreet.setText(street);
	  jtfCity.setText(city);
	  jtfState.setText(state);
	  jtfZip.setText(zip);
	}
	
	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */

	public static void main(String[] args) { 
	  launch(args);
	
	}
}

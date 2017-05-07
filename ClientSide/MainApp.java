package sam.tsunami;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sam.tsunami.view.GuiOverviewController;

public class MainApp extends Application {
	// Static fields

	// Instance fields
	private Stage primaryStage;
    private BorderPane rootLayout;
    private static MySocketUtility msu;




    public static void main(String[] args)
    {
    	msu = new MySocketUtility("192.168.0.3", 58999);

    	launch(args);

    }

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tsunami");

        initRootLayout();

    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/GuiOverview.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            GuiOverviewController controller = loader.getController();
             controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    // Handles fire, start, and stopping of waves
    public void fireCommand(String command)
    {
    	msu.sendData(command);
    	System.out.println(command);
    }


}
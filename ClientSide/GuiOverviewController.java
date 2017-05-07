/**
 * Controller for SceneBuilder FXML file */

package sam.tsunami.view;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import sam.tsunami.MainApp;

public class GuiOverviewController {

	private MainApp mainApp;

	private double standingFrequency = 45;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="closeApplication"
    private MenuItem closeApplication; // Value injected by FXMLLoader

    @FXML // fx:id="aboutButton"
    private MenuItem aboutButton; // Value injected by FXMLLoader

    @FXML // fx:id="tabPane"
    private TabPane tabPane; // Value injected by FXMLLoader

    @FXML // fx:id="HomeTab"
    private Tab homeTab; // Value injected by FXMLLoader

    @FXML // fx:id="SingleWaveHomeTabButton"
    private Button singleWaveHomeTabButton; // Value injected by FXMLLoader

    @FXML // fx:id="StandingWaveHomeTabButton"
    private Button standingWaveHomeTabButton; // Value injected by FXMLLoader

    @FXML // fx:id="SingleTab"
    private Tab singleTab; // Value injected by FXMLLoader

    @FXML // fx:id="largeWaveRadioButton"
    private RadioButton largeWaveRadioButton; // Value injected by FXMLLoader

    @FXML // fx:id="RadioGroup"
    private ToggleGroup radioGroup; // Value injected by FXMLLoader

    @FXML // fx:id="smallWaveRadioButton"
    private RadioButton smallWaveRadioButton; // Value injected by FXMLLoader

    @FXML // fx:id="SingleFireButton"
    private Button singleFireButton; // Value injected by FXMLLoader

    @FXML // fx:id="StandingTab"
    private Tab standingTab; // Value injected by FXMLLoader

    @FXML // fx:id="slider"
    private Slider slider; // Value injected by FXMLLoader

    @FXML // fx:id="startStandingButton"
    private Button startStandingButton; // Value injected by FXMLLoader

    @FXML // fx:id="stopStandingButton"
    private Button stopStandingButton; // Value injected by FXMLLoader

    @FXML // fx:id="CameraTab"
    private Tab cameraTab; // Value injected by FXMLLoader

    public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}

    @FXML
    void dislpayAboutText(ActionEvent event) {

    }

    @FXML
    void handleCloseApplication(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void handleSingleFireButton(ActionEvent event) {
    	String command = largeWaveRadioButton.isSelected() ? "large" : "small";
    	// Console for debug
	    mainApp.fireCommand(command);
    }

    @FXML
    void handleSingleWaveHomeTabButton(ActionEvent event) {
    	tabPane.getSelectionModel().select(singleTab);
    	singleTab.setDisable(false);
    }

    @FXML
    void handleStandingWaveHomeTabButton(ActionEvent event) {
    	tabPane.getSelectionModel().select(standingTab);
    	standingTab.setDisable(false);
    }

    @FXML
    void handleStandingFireButton(ActionEvent event) {
    	// Console debug
    	singleTab.setDisable(true);
    	homeTab.setDisable(true);
    	getSliderValue();
    	mainApp.fireCommand("stand" + Double.toString(standingFrequency));
    }

    @FXML
    private void getSliderValue()
    {
    	this.standingFrequency = slider.getValue();
    }

    @FXML
    void handleStopStandingButton(ActionEvent event) {
    	// Console debug
    	mainApp.fireCommand("stop0");
    	//timerFunction();
    	homeTab.setDisable(false);
    	singleTab.setDisable(false);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert closeApplication != null : "fx:id=\"closeApplication\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert aboutButton != null : "fx:id=\"aboutButton\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert homeTab != null : "fx:id=\"HomeTab\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert singleWaveHomeTabButton != null : "fx:id=\"SingleWaveHomeTabButton\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert standingWaveHomeTabButton != null : "fx:id=\"StandingWaveHomeTabButton\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert singleTab != null : "fx:id=\"SingleTab\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert largeWaveRadioButton != null : "fx:id=\"largeWaveRadioButton\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert radioGroup != null : "fx:id=\"RadioGroup\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert smallWaveRadioButton != null : "fx:id=\"smallWaveRadioButton\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert singleFireButton != null : "fx:id=\"SingleFireButton\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert standingTab != null : "fx:id=\"StandingTab\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert slider != null : "fx:id=\"slider\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert startStandingButton != null : "fx:id=\"startStandingButton\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert stopStandingButton != null : "fx:id=\"stopStandingButton\" was not injected: check your FXML file 'GuiOverview.fxml'.";
        assert cameraTab != null : "fx:id=\"CameraTab\" was not injected: check your FXML file 'GuiOverview.fxml'.";

    }
}

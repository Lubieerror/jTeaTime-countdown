package su.lubieerror.jteatime.ui;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import su.lubieerror.jteatime.logic.CountDown;

/**
 * @author lubieerror
 *         <p>
 *         Class was made for mainView.fxml control.
 *         Allows to change clock's time.
 *         It's controling input.
 */

public class mvController {
    // things imported from XML
    @FXML
    public Label clock;
    @FXML
    private Button bBoldWater;
    @FXML
    private Button bWater;
    @FXML
    private Button bTea;
    @FXML
    private Button bAll;

    private CountDown countDown;

    private CountDown.WaitType actualJob;

    // Clock setter
    public void setClock(String text) {
        clock.setText(text);
    }

    private void start() {
        clockCursorWait(true);
        countDown = new CountDown(this);
        bBoldWater.setDisable(true);
        bWater.setDisable(true);
        bTea.setDisable(true);
        bAll.setDisable(false);
    }

    private void makingWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Finished!");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setResizable(false);
        alert.alertTypeProperty();

        Stage parentStage = (Stage) clock.getScene().getWindow();
        parentStage.setAlwaysOnTop(true);
        alert.initOwner(parentStage);

        switch (actualJob) {
            case COLD_FULL:
                alert.setHeaderText("Water is ready!");
                break;
            case FOR_WATER:
                alert.setHeaderText("Water is tea-friendly!");
                break;
            case FOR_TEA:
                alert.setHeaderText("Tea is ready to use Sir! Get some sugar and enjoy your drink.");
                break;
            case FOR_ALL:
                alert.setHeaderText("Task canceled!");
                break;
            default:
                alert.setHeaderText("Something is ready but I don't know what D:");
                break;
        }

        alert.showAndWait();
        parentStage.setAlwaysOnTop(false);
    }

    public void stop() {
        javafx.application.Platform.runLater(this::makingWindow);

        clockCursorWait(false);
        bBoldWater.setDisable(false);
        bWater.setDisable(false);
        bTea.setDisable(false);
        bAll.setDisable(true);
    }

    // should be turn on when countdown starts
    private void clockCursorWait(boolean isTrue) {
        if (isTrue) {
            clock.setCursor(Cursor.WAIT);
        } else {
            clock.setCursor(Cursor.DEFAULT);
        }
    }

    public void boldWaterClicked() {
        start();
        actualJob = CountDown.WaitType.COLD_FULL;
        countDown.start(CountDown.WaitType.COLD_FULL);
    }

    public void waterClicked() {
        start();
        actualJob = CountDown.WaitType.FOR_WATER;
        countDown.start(CountDown.WaitType.FOR_WATER);
    }

    public void teaClicked() {
        start();
        actualJob = CountDown.WaitType.FOR_TEA;
        countDown.start(CountDown.WaitType.FOR_TEA);
    }

    public void allClicked() {
//        start();
        actualJob = CountDown.WaitType.FOR_ALL;
//        countDown.start(CountDown.WaitType.FOR_ALL);
        countDown.reset();
    }
}

package su.lubieerror.jteatime.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

class FinishedWaitingController {
    @FXML
    private TextArea textArea;

    public void setText(String text) {
        textArea.setText(text);
    }

    @FXML
    private void closeWindow(ActionEvent e) {
        ((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
    }

}

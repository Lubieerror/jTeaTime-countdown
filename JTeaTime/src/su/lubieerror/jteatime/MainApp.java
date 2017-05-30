package su.lubieerror.jteatime;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by:
 * @author lubieerror
 *
 *  https://github.com/Lubieerror
 */

public class MainApp extends Application {
    private static final String VERSION = "1.1";

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/mainView.fxml"));

        window.setTitle("JTeaTime v" + VERSION + " Beta");
        window.getIcons().add(new Image(getClass().getResourceAsStream("res/tea-icon.png")));
        window.setScene(new Scene(root, 300, 400));
        window.getScene().getStylesheets().add("su/lubieerror/jteatime/ui/style.css");
        window.setResizable(false);
//        window.setOnCloseRequest(event -> Platform.exit());
        window.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package sample.LoadingPage;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class TestingService extends Service<Scene> {
    @Override
    protected Task<Scene> createTask() {
        return new Task<Scene>() {
            @Override
            protected Scene call() throws Exception {
                Label label = new Label("WaingScene");
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(label);

                return new Scene(stackPane, 400, 500);
            }
        };
    }
}

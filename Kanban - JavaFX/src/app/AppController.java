package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;


public class AppController implements Initializable{

    private EditTask editTask;
    private AddNewTask addsNewTask;
    public MenuItem closeID;
    public Menu aboutID;

    public ObservableList<Resource> toDoItems = FXCollections.observableArrayList();
    public ObservableList<Resource> inProgressItems = FXCollections.observableArrayList();
    public ObservableList<Resource> doneItems = FXCollections.observableArrayList();

    public ListView<Resource> toDoID = new ListView<>(toDoItems);
    public ListView<Resource> inProgressID = new ListView<>(inProgressItems);
    public ListView<Resource> doneID = new ListView<>(doneItems);

    public AppController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
                                              // About Author
        Label about = new Label("About");
        about.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information about author");
                alert.setHeaderText("Author : Fabiola Dąbroś");
                alert.showAndWait();
            }
        });
        aboutID.setGraphic(about);
        // dodanie list
        toDoID.setItems(toDoItems);
        inProgressID.setItems(inProgressItems);
        doneID.setItems(doneItems);

        // przenoszenie miedzy listami przy pomocy strzalki
        toDoID.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case RIGHT:
                            if (!toDoID.getItems().isEmpty()) {
                                inProgressID.getItems().add(toDoID.getItems().get(toDoID.getFocusModel().getFocusedIndex()));
                                toDoID.getItems().remove(toDoID.getItems().get(toDoID.getFocusModel().getFocusedIndex()));
                            }
                            break;
                        case E:
                            if (toDoID.getSelectionModel().getSelectedIndex() >= 0) {
                                int index = toDoID.getSelectionModel().getSelectedIndex();
                                editTask.load(toDoID.getItems().get(index), index);
                                editTask.whichList = 1;    // ustawienie listy na 1
                            }

                    }
                }
        );
        inProgressID.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT:
                    if (!inProgressID.getItems().isEmpty()) {
                        doneID.getItems().add(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                        inProgressID.getItems().remove(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                    }
                    break;
                case LEFT:
                    if (!inProgressID.getItems().isEmpty()) {
                        toDoID.getItems().add(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                        inProgressID.getItems().remove(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                    }
                    break;
            }
        });
        doneID.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    if (!doneID.getItems().isEmpty()) {
                        inProgressID.getItems().add(doneID.getItems().get(doneID.getFocusModel().getFocusedIndex()));
                        doneID.getItems().remove(doneID.getItems().get(doneID.getFocusModel().getFocusedIndex()));
                    }
                    break;
            }
        });

        // ustawienie kolorow priorytetow
        toDoID.setCellFactory(new Callback<ListView<Resource>, ListCell<Resource>>() {
            @Override public ListCell<Resource> call (ListView<Resource> list) {
                return new ColorRectCell();
            }
        });
        inProgressID.setCellFactory(new Callback<ListView<Resource>,ListCell<Resource>>() {
            @Override
            public ListCell<Resource> call(ListView<Resource> list) {
                return new ColorRectCell();
            }}
        );
        doneID.setCellFactory(new Callback<ListView<Resource>,ListCell<Resource>>() {
            @Override
            public ListCell<Resource> call(ListView<Resource> list) {
                return new ColorRectCell();
            }}
        );

    }

    public void setAddNewTask(AddNewTask addNewTask) {
        this.addsNewTask = addNewTask; }

    public void setEditTask(EditTask editTask){
        this.editTask = editTask;
    }

    @FXML            // File -> Close
    public void closeApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML           // zaladowanie fxml do dodania nowego zadania
    public void addNewTaskButton(){
         addsNewTask.load();
   }
          // usuwanie lub edycja zadan
    @FXML
    public void editTODO(ActionEvent actionEvent) {
        if (toDoID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = toDoID.getSelectionModel().getSelectedIndex();
             editTask.load(toDoID.getItems().get(index), index);
              editTask.whichList = 1;    // ustawienie listy na 1
        }
    }
    public void deleteTODO(ActionEvent actionEvent) {
        if (toDoID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = toDoID.getSelectionModel().getSelectedIndex();
            toDoID.getItems().remove(index);
        }
    }
    public void editInProgress(ActionEvent actionEvent) {
        if (inProgressID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = inProgressID.getSelectionModel().getSelectedIndex();
            editTask.load(inProgressID.getItems().get(index), index);
            editTask.whichList = 2;   // ustawienie listy na 2
        }
    }
    public void deleteInProgress(ActionEvent actionEvent) {
        if (inProgressID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = inProgressID.getSelectionModel().getSelectedIndex();
            inProgressID.getItems().remove(index);
        }
    }
    public void editDone(ActionEvent actionEvent) {
        if (doneID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = doneID.getSelectionModel().getSelectedIndex();
            editTask.load(doneID.getItems().get(index), index);
            editTask.whichList = 3;   // ustawienie listy na 3
        }
    }
    public void deleteDone(ActionEvent actionEvent) {
        if (doneID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = doneID.getSelectionModel().getSelectedIndex();
            doneID.getItems().remove(index);
        }
    }

// przenoszenie miedzy listami

    public void dragDetectedTODO(DragEvent dragEvent) {

       // Dragboard db= st ;
//        Dragboard db = toDoID.startDragAndDrop(TransferMode.ANY);
////        ClipboardContent cb = new ClipboardContent();
////        cb.putString(toDoID.getAccessibleText());
////        db.setContent(cb);
////        mouseEvent.consume();
//        ObservableList<String> items = getListView().getItems();
//        Dragboard dragboard = toDoID.startDragAndDrop(TransferMode.MOVE);
//        ClipboardContent content = new ClipboardContent();
//        content.putString(getItem());


    }

    public void dragDroppedInProgress(DragEvent dragEvent) {

        //ObservableList str = dragEvent.getDragboard().getDragView();
       //inProgressID.setItems(str);
       // inProgressID.getItems().add(toDoID.getItems().get(toDoID.getFocusModel().getFocusedIndex()));
    }

    public void dragOverInProgress(DragEvent dragEvent) {
        if(dragEvent.getDragboard().hasString()){
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    // dodanie elipsy przed tytulem zadania
    static class ColorRectCell extends ListCell<Resource> {
        @Override
        public void updateItem(Resource item, boolean empty) {
            super.updateItem(item, empty);

            if (item != null) {
                setText(getItem().getTitle());
                Ellipse rect = new Ellipse(5,10,5,10);
                rect.setFill(Color.web(getItem().getPriorityColor()));
                setGraphic(rect);
                Tooltip tooltip = new Tooltip();     // stworzenie tooltipa do wyswietlania tekstu
                tooltip.setText(getItem().getText());
                setTooltip(tooltip);

            } else {
                setText(null);
                setGraphic(null);
                setTooltip(null);
            }
        }}
}

package main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Controller {

    Kw kw;
    Alert alert;
    private String controlNumber;

    @FXML
    private TextField kwNumberField;
    @FXML
    private TextField controlNumberField;

    public void initialize(){
        kw = new Kw();
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Bląd wejścia danych");
        alert.setContentText("Numer Księgi Wieczystej składa się z 12 znaków. \n" +
                "Użyj wielkich liter. Wprowadź bez znaku / \n" +
                "Przykładowy format:  KR1P12345678 ");
    }

    private void isNext (boolean next){
        if(setKwNumber()){
            controlNumber = String.valueOf(kw.isNextControlDigit(next));
            setControlNumberField();
            kwNumberField.setText(kw.printKwNumber());
        }
    }

    @FXML
    public boolean setKwNumber(){
        controlNumber = String.valueOf(kw.getControlDigit(kwNumberField.getText()));
        switch (controlNumber) {
            case "-1":
                alert.setHeaderText("Użyto nieprawidłowych znaków!");
                alert.showAndWait();
                return false;
            case "-2":
                alert.setHeaderText("Wprowadzony nr jest za krótki!");
                alert.showAndWait();
                return false;
            case "-3":
                alert.setHeaderText("Wprowadzony nr jest za długi!");
                alert.showAndWait();
                return false;
            default:
                setControlNumberField();
                return true;
        }
    }

    @FXML
    private void setControlNumberField (){
        controlNumberField.setText(controlNumber);
        controlNumberField.setStyle("-fx-text-inner-color: red");
        controlNumberField.setDisable(false);
    }

    @FXML
    public void nextKwNumber(){
        isNext(true);
    }

    @FXML
    public void previousKwNumber (){
        isNext(false);
    }

    @FXML
    public void onFieldClick (){
        controlNumberField.setDisable(true);
    }
}

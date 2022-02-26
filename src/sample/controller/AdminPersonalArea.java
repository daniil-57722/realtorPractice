package sample.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import sample.Constants;
import sample.DBHandler;
import sample.Main;
import sample.Swap;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPersonalArea {
    private String needQuerry, offerQuerry;
    private DBHandler dbHandler;
    private ObservableList<ObservableList> data;
    @FXML private TableView<ObservableList> listTable;
    @FXML private Button newRealtorBtn;
    @FXML private Button showRealtorBtn;
    @FXML private Button showTaskButton;
    @FXML private Button showUserBtn;
    @FXML private Button deleteBtn;
    @FXML private Button exitBtn;
    @FXML private Button showDealsBtn;
    String querry = "Select * from ";
    String table = "users";
    public void initialize(){
        showUserBtn.setOnAction(event ->{
            try {
                table = Constants.USER_TABLE;
                Main.fill(querry + table, listTable);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        showDealsBtn.setOnAction(event -> {
            table = Constants.DEAL_TABLE;
            String dealquerry = "select id, realty, price, offerer, needer, realtor from "+ table;
            try{
                Main.fill(dealquerry, listTable);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        showRealtorBtn.setOnAction(event -> {
            table = Constants.REALTOR_TABLE;
            try{
                Main.fill(querry+table, listTable);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        showTaskButton.setOnAction(event -> {
            if (showTaskButton.getText().equals("Показать предложения")){
                table = Constants.OFFER_TABLE;
                showTaskButton.setText("Показать потребности");
            }
            else {
                table = Constants.NEED_TABLE;
                showTaskButton.setText("Показать предложения");
            }
            try {
                Main.fill(querry+table, listTable);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        deleteBtn.setOnAction(event ->{
            ObservableList selected = listTable.getSelectionModel().getSelectedItem();
            listTable.getItems().remove(selected);
            String id = String.valueOf(selected.get(0));
            dbHandler = new DBHandler();
                dbHandler.deleteRow(table, id);
        });
        exitBtn.setOnAction(event ->{
            exitBtn.getScene().getWindow().hide();
            try {
                Swap.openAnotherWindow("view/authorization.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        newRealtorBtn.setOnAction(event ->{
            try {
                Swap.openAnotherWindow("view/newRealtor.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

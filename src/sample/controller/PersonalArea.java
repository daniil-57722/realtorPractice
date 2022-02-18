package sample.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import sample.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalArea {
    User user;
    DBHandler dbHandler;

    @FXML TableView<ObservableList> NeedTable;
    @FXML private Button createNeed;
    @FXML private Button deleteBtn;
    @FXML private Button createOffer;
    @FXML private Label nameField;
    @FXML private Button showButton;
    @FXML private Button showDealsBtn;
    @FXML private Button exitBtn;

    private ObservableList<ObservableList> data;
    String needQuerry;
    String offerQuerry;
    String activeQuerry;

public void initData(User user){
    this.user = user;
    nameField.setText(user.getName());
    needQuerry = "SELECT id, realty as недвижимость, minprice, maxprice, realtor, adress FROM " + Constants.NEED_TABLE +
            " WHERE " + Constants.CLIENT + " = '" + user.getName() + "' AND " + Constants.USER_PHONE + "=" + user.getPhone();
    offerQuerry = "SELECT id, realty, price, realtor, adress FROM " + Constants.OFFER_TABLE +
            " WHERE " + Constants.CLIENT + " = '" + user.getName() + "' AND " + Constants.USER_PHONE + "=" + user.getPhone();
    activeQuerry = offerQuerry;
    try {
        System.out.println(activeQuerry);
        Main.fill(activeQuerry, NeedTable);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
}
public void initialize(){
    createOffer.setOnAction(event -> {
        try {
            Swap.openCreateOfferWindow("view/createOffer.fxml", user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    exitBtn.setOnAction(event ->{
        exitBtn.getScene().getWindow().hide();
        try {
            Swap.openAnotherWindow("view/authorization.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    createNeed.setOnAction(event -> {
        try {
            Swap.openCreateNeedWindow("view/createNeed.fxml", user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    showButton.setOnAction(event ->{
        if (activeQuerry.equals(needQuerry)){
            activeQuerry = offerQuerry;
            showButton.setText("Показать потребности"); }
        else{
            activeQuerry = needQuerry;
            showButton.setText("Показать предложения"); }
        try {
            Main.fill(activeQuerry, NeedTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace(); }
    });
    deleteBtn.setOnAction(event ->{
        ObservableList selected = NeedTable.getSelectionModel().getSelectedItem();
        NeedTable.getItems().remove(selected);
        String id = String.valueOf(selected.get(0));
        dbHandler = new DBHandler();
        if (activeQuerry.contains("minprice")){
        dbHandler.deleteRow(Constants.NEED_TABLE, id);}
        else{
            dbHandler.deleteRow(Constants.OFFER_TABLE, id);
        }
    });
    showDealsBtn.setOnAction(event ->{
        try {
            Swap.openDealsWindow("view/deals.fxml", user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
}
}
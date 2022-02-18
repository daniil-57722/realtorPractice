package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Constants;
import sample.DBHandler;
import sample.User;
import sample.objects.Offer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateOfferController {
    User user;
    @FXML private ComboBox<?> realtorsList;
    @FXML private Button createButton;
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField priceField;
    @FXML private TextField typeOfRealty;
    @FXML private Label successLabel;

    DBHandler dbHandler;
    ObservableList realtors = FXCollections.observableArrayList();
    public void initData(User user) {
        nameField.appendText(user.getName());
        this.user = user;
        phoneField.appendText(user.getPhone());
    }
    public void initialize() throws SQLException {
        dbHandler = new DBHandler();
        ResultSet resultSet = dbHandler.querry("Select * from " + Constants.REALTOR_TABLE);
        while (resultSet.next()) {
            realtors.add(resultSet.getString("id") + ") " + resultSet.getString("firstname")
                    + " " + resultSet.getString("patronymic"));
        }
        realtorsList.setItems(realtors);
        createButton.setOnAction(event -> {
            String realtor = null;
            dbHandler = new DBHandler();
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String realty = typeOfRealty.getText().trim();
            realtor = realtorsList.getSelectionModel().getSelectedItem().toString().trim();
            String price = priceField.getText().trim();
            String success = isFill(name, realtor, phone, realty, price);
            if (success.equals("Успех")){
                int priceInt = Integer.parseInt(price);
                Offer offer = new Offer(name, phone, realty, priceInt, realtor.substring(realtor.indexOf(")")+2),realtor.substring(0,realtor.indexOf(")")) );
                System.out.println(realtor.substring(realtor.indexOf(")"))+" "+realtor.substring(0,realtor.indexOf(")")));
                dbHandler.addOffer(offer);
            }
            else {
                successLabel.setText(success);
            }
        });
    }
    public String isFill (String name, String realtor, String phone, String realty, String price){
        dbHandler = new DBHandler();
        	if (realtor != null){
            	if (name.equals("") || phone.equals("") || realty.equals("") || price.equals("")) {
                	return("Заполните поля!"); }
            	else {
            	    try {
                	int priceInt = Integer.parseInt(price);}
            	    catch (Exception e){
            	        return "Укажите цену цифрами"; }
                	return("Успех"); }
            	}
        	else {
            	return("Выберите риэлтора");
        	}
    }
}


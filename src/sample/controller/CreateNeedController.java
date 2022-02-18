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
import sample.objects.Need;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateNeedController {

    @FXML private Button createButton;
    @FXML private TextField adressField;
    @FXML private TextField maxPriceField;
    @FXML private TextField minPriceField;
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private ComboBox<?> realtorsList;
    @FXML private Label successLabel;
    @FXML private TextField typeOfRealty;
    User user;
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
                    + " " + resultSet.getString("patronymic"));}

            realtorsList.setItems(realtors);
            createButton.setOnAction(event -> {
                int minPriceInt = 0;
                int maxPriceInt = 0;
                String realtor = null;
                String realtorid = null;
                dbHandler = new DBHandler();
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String realty = typeOfRealty.getText().trim();
                String adress = adressField.getText().trim();
                try {
                    realtor = realtorsList.getSelectionModel().getSelectedItem().toString().trim();
                    realtorid = realtor.substring(0,realtor.indexOf(")"));
                    realtor = realtor.substring(realtor.indexOf(")")+2);
                } catch (Exception e) {
                    successLabel.setText("Выберите риэлтора"); }
                String minPrice = minPriceField.getText().trim();
                String maxPrice = maxPriceField.getText().trim();
                System.out.println(minPrice+" " +maxPrice);
                if (name.equals("") || phone.equals("") || realty.equals("")
                        || minPrice.equals("") || maxPrice.equals("") || adress.equals(" ")) {
                    successLabel.setText("Заполните поля!");
                } else {
                    try{
                    minPriceInt = Integer.parseInt(minPrice);
                    maxPriceInt = Integer.parseInt(maxPrice);}
                    catch(Exception e){
                        successLabel.setText("Укажите цену цифрами");
                    }
                    Need need = new Need(name, phone, realty, minPriceInt, maxPriceInt, realtor, realtorid, adress);
                    dbHandler.addNeed(need);
                    createButton.getScene().getWindow().hide();
                }
            });
        }
    }


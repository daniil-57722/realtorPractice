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
import sample.objects.User;
import sample.objects.Need;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateNeedController {

    @FXML private Button createButton;
    @FXML private TextField adressField;
    @FXML private TextField maxPriceField;
    @FXML private TextField minPriceField;
    @FXML private TextField nameField;
    @FXML private ComboBox<?> realtorsList;
    @FXML private Label successLabel;
    @FXML private TextField typeOfRealty;

    User user;
    DBHandler dbHandler;
    ObservableList realtors = FXCollections.observableArrayList();

    /**
     * preloading
     * @param user current user
     */
    public void initData(User user) {
        nameField.appendText(user.getName());
        this.user = user;
    }
    /**
     * init for controller
     */
    public void initialize() throws SQLException {
        dbHandler = DBHandler.getDBHandler();
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
                dbHandler = DBHandler.getDBHandler();
                String name = nameField.getText().trim();
                String realty = typeOfRealty.getText().trim();
                String adress = adressField.getText().trim();
                try {
                    realtor = realtorsList.getSelectionModel().getSelectedItem().toString().trim();
                    realtorid = realtor.substring(0,realtor.indexOf(")"));
                    realtor = realtor.substring(realtor.indexOf(")")+2);
                } catch (Exception e) {
                    successLabel.setText("Выберите риэлтора");
                }
                String minPrice = minPriceField.getText().trim();
                String maxPrice = maxPriceField.getText().trim();
                System.out.println(minPrice+" " +maxPrice);
                if (name.equals("") || realty.equals("")
                        || minPrice.equals("") || maxPrice.equals("") || adress.equals(" ")) {
                    successLabel.setText("Заполните поля!");
                } else {
                    if (realtor != null) {
                        try {
                            minPriceInt = Integer.parseInt(minPrice);
                            maxPriceInt = Integer.parseInt(maxPrice);
                        } catch (Exception e) {
                            successLabel.setText("Укажите цену цифрами");
                        }
                        Need need = new Need(user.getId(), realty, minPriceInt, maxPriceInt,
                                Integer.parseInt(realtorid), adress);
                        dbHandler.addNeed(need);
                    }
                }
            });
        }
    }

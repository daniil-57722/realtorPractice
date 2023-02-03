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
import sample.objects.Offer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateOfferController {
    User user;
    String realtor;
    @FXML private ComboBox<?> realtorsList;
    @FXML private Button createButton;
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField priceField;
    @FXML private TextField typeOfRealty;
    @FXML private Label successLabel;
    @FXML private TextField adressField;
    @FXML private Label commissionLabel;
    @FXML private Button countButton;

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
                    + " " + resultSet.getString("patronymic")); }
        realtorsList.setItems(realtors);


        countButton.setOnAction(event -> {
        if (!priceField.getText().trim().equals("") && realtorsList.getSelectionModel().getSelectedItem()!= null){
            realtor = realtorsList.getSelectionModel().getSelectedItem().toString();
            String realtorId = realtor.substring(0,realtor.indexOf(")"));
            try {
                resultSet.afterLast();
                while (resultSet.previous()) {
                    if (resultSet.getString("id").equals(realtorId)) {
                        System.out.println("1");
                        int comission = resultSet.getInt("comission") * Integer.parseInt(priceField.getText());
                        commissionLabel.setText(String.valueOf(comission/100));
                        break;
                    }
                }
            } catch (SQLException throwables) {
                commissionLabel.setText("Произошла ошибка");
                throwables.printStackTrace();
            }
            catch (NumberFormatException e){
                commissionLabel.setText("Укажите цену \n цифрами");
            }
        }
        else {
            commissionLabel.setText("Заполните поля"); }
        });
    }

    /**
     * check for fields filled
     * @param name
     * @param realtor
     * @param realty
     * @param price
     * @param address
     * @return
     */
    public String isFill (String name, String realtor, String realty, String price, String address){
        dbHandler = DBHandler.getDBHandler();
        if (!(realtor != null && !realtor.equals("")))
            return ("Выберите риэлтора");
        if (name.equals("") || realty.equals("") || price.equals("") || address.equals(""))
            return("Заполните поля!");
        else {
            try {Integer.parseInt(price);
            }catch (Exception e){
                return "Укажите цену цифрами"; }
            return("Успех");
        }
    }
}


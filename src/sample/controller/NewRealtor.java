package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Constants;
import sample.DBHandler;
import sample.objects.Realtor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewRealtor {
    @FXML private Button backButton;
    @FXML private TextField comission;
    @FXML private Button createButton;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField loginField;
    @FXML private TextField passwordField;
    @FXML private TextField patronymicField;
    @FXML private Label successLabel;
    /**
     * init for controller
     */
    public void initialize() {
        createButton.setOnAction(event -> {
            DBHandler dbHandler = DBHandler.getDBHandler();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String patronymic = patronymicField.getText();
            String com = comission.getText();
            String login = loginField.getText();
            String password = passwordField.getText();

            if (login.length() <= 6 && password.length() <= 8) {
                successLabel.setText("Длинна пароля более 8 символов, длина логина от 6");
            } else {
                ResultSet success = dbHandler.checkLoginAndPass(login, password, Constants.REALTOR_TABLE);
                try {
                    if (success.next()) {
                        successLabel.setText("Данные логин и пароль уже используются");
                    } else {
                        Realtor realtor = new Realtor(firstName, lastName, patronymic, com, login, password);
                        dbHandler.newRealtor(realtor);
                        successLabel.setText("Регистрация прошла успешно");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        backButton.setOnAction(event ->{
            backButton.getScene().getWindow().hide();
        });
    }
}

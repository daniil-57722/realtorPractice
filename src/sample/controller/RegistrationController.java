package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.*;
import sample.objects.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationController {

    @FXML private Button logInButton;
    @FXML private TextField registrationEmailField;
    @FXML private TextField registrationLoginField;
    @FXML private TextField registrationNameField;
    @FXML private PasswordField registrationPasswordField;
    @FXML private TextField registrationPhoneField;
    @FXML private Button signUpButton;
    @FXML private Label SuccesLabel;

    /**
     * init for controller
     */
    @FXML
    void initialize() {
        signUpButton.setOnAction(event ->{
            DBHandler dbHandler = DBHandler.getDBHandler();
            String name = registrationNameField.getText();
            String phone = registrationPhoneField.getText();
            String email = registrationEmailField.getText();
            String login = registrationLoginField.getText();
            String password = registrationPasswordField.getText();

            if (login.length()<=6 && password.length()<=8){
                SuccesLabel.setText("Длинна пароля более 8 символов, длина логина от 6");
            }
            else{
            ResultSet success = dbHandler.checkLoginAndPass(login, password, Constants.USER_TABLE);
            try {
                if (success.next()){
                    SuccesLabel.setText("Данные логин и пароль уже используются");
                }
                else{
                    User user = new User (name, phone, email, login, password);
                        dbHandler.signUpUser(user);
                        SuccesLabel.setText("Регистрация прошла успешно");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        });

        logInButton.setOnAction(event -> {
            logInButton.getScene().getWindow().hide();
            Swap.openAnotherWindow("view/authorization.fxml");
        });
    }
}


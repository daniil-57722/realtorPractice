package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.*;
import sample.objects.Realtor;
import sample.objects.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthController {
    String table;

    @FXML private TextField authorizationLogInField;
    @FXML private PasswordField authorizationPasswordField;
    @FXML private Button signUpButton;
    @FXML private Button logInButton;
    @FXML private Label ErrLabel;
    @FXML private RadioButton realtorCheck;
    @FXML private RadioButton adminCheck;
    @FXML
    void initialize(){
        ToggleGroup group = new ToggleGroup();
        realtorCheck.setToggleGroup(group);
        adminCheck.setToggleGroup(group);
        //действия при нажатии на кнопку Зарегистрироваться
        signUpButton.setOnAction(event -> {
            signUpButton.getScene().getWindow().hide();
            try {
                Swap.openAnotherWindow("view/registration.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //действия при нажатии на кнопку Войти
        logInButton.setOnAction(event -> {
            if(realtorCheck.isSelected()){
                table = Constants.REALTOR_TABLE; }
            else if(adminCheck.isSelected()){
                table = Constants.ADMIN_TABLE; }
            else {
                table = Constants.USER_TABLE;}
            String login = authorizationLogInField.getText().trim();
            String password = authorizationPasswordField.getText().trim();
            if (!login.equals("")||!password.equals("")){
                DBHandler dbHandler = new DBHandler();
                ResultSet result = dbHandler.checkLoginAndPass(login, password, table);
                try {
                    if (result.next()){
                        if (table.equals(Constants.USER_TABLE)){
                        User user = new User(result.getString(1), result.getString(2), result.getString(3),
                                result.getString(4), result.getString(5), result.getString(6));
                            signUpButton.getScene().getWindow().hide();
                            Swap.openPersonalAreaWindow("view/personalArea.fxml", user);
                        }
                        else if (table.equals(Constants.REALTOR_TABLE)){
                            Realtor realtor = new Realtor(result.getString(1),result.getString(2),
                                    result.getString(3), result.getString(4),
                                    result.getString(5), result.getString(6),
                                    result.getString(7));
                            signUpButton.getScene().getWindow().hide();
                            Swap.openPersonalAreaWindow("view/realtorPersonalArea.fxml", realtor);
                        }
                        else {
                            signUpButton.getScene().getWindow().hide();
                            Swap.openAnotherWindow("view/adminPersonalArea.fxml");
                        }
                    }
                    else{ErrLabel.setText("Неправильный логин или пароль");}
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace(); }
            }
            else {
                ErrLabel.setText("Заполните поля");
            }
        });
    }
}
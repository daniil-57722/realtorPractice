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
    String table = Constants.USER_TABLE;;

    @FXML private TextField authorizationLogInField;
    @FXML private PasswordField authorizationPasswordField;
    @FXML private Button signUpButton;
    @FXML private Button logInButton;
    @FXML private Label ErrLabel;
    @FXML private CheckBox realtorCheck;
    @FXML
    /**
     * init for controller
     */
    void initialize(){
        //действия при нажатии на кнопку Зарегистрироваться
        signUpButton.setOnAction(event -> {
            signUpButton.getScene().getWindow().hide();
            Swap.openAnotherWindow("view/registration.fxml");
        });
        //действия при нажатии на кнопку Войти
        logInButton.setOnAction(event -> {
            if(realtorCheck.isSelected()){
                table = Constants.REALTOR_TABLE; }
            String login = authorizationLogInField.getText().trim();
            String password = authorizationPasswordField.getText().trim();
            if (!login.equals("")||!password.equals("")){
                DBHandler dbHandler = DBHandler.getDBHandler();
                ResultSet result = dbHandler.checkLoginAndPass(login, password, table);
                try {
                    if (result.next()){
                        if (table.equals(Constants.USER_TABLE)){
                        User user = new User(result.getString(1), result.getString(2), result.getString(3));
                            signUpButton.getScene().getWindow().hide();
                            Swap.openPersonalAreaWindow("view/personalArea.fxml", user);
                        }
                        else if (table.equals(Constants.REALTOR_TABLE)&&result.getInt("role_id")==1){
                            Realtor realtor = new Realtor(result.getString(1),result.getString(2),result.getString(3));
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
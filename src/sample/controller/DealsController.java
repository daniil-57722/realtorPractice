package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.*;
import sample.objects.Realtor;
import sample.objects.User;

import java.sql.SQLException;

public class DealsController {
    Realtor realtor;
    User user;
    private DBHandler dbHandler;

    @FXML private TableView<ObservableList> dealTable;
    @FXML private Button exitBtn;
    @FXML private Button deleteBtn;
    @FXML private Label nameField;

    public void initData(Realtor realtor) {
        deleteBtn.setOpacity(1);
        deleteBtn.setDisable(false);
        this.realtor = realtor;
        nameField.setText(realtor.getName());
        String querry = "select id, realty, price, offerer, needer, realtor from "+ Constants.DEAL_TABLE+ " where realtorid = " + realtor.getId();
        try{
            Main.fill(querry, dealTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void initData(User user) {

        this.user = user;
        nameField.setText(user.getName());
        String querry = "select id, realty, price, offerer, needer, realtor from "+ Constants.DEAL_TABLE+
                " where needer = '" + user.getName() + " " + user.getPhone() + "' OR offerer = '" + user.getName() +" "+ user.getPhone() + "'";
        System.out.println(querry);
        try{
            Main.fill(querry, dealTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
@FXML void initialize(){
    deleteBtn.setOnAction(event ->{
        System.out.println("1");
        ObservableList selected = dealTable.getSelectionModel().getSelectedItem();
        dealTable.getItems().remove(selected);
        String id = String.valueOf(selected.get(0));
        dbHandler = new DBHandler();
        String table = Constants.DEAL_TABLE;
        dbHandler.deleteRow(table, id);
    });
    exitBtn.setOnAction(event ->{
        exitBtn.getScene().getWindow().hide();
    });

}
}

package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import sample.Constants;
import sample.DBHandler;
import sample.Main;
import sample.Swap;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class AdminPersonalArea {
    String querry;
    private DBHandler dbHandler;
    @FXML private TableView<ObservableList> dealTable;
    @FXML private Button deleteBtn;
    @FXML private Button exitBtn;
    @FXML private TableView<ObservableList> needTable;
    @FXML private Button newRealtorBtn;
    @FXML private TableView<ObservableList> offerTable;
    @FXML private TableView<ObservableList> realtorTable;
    @FXML private TableView<ObservableList> userTable;
    @FXML private TabPane tablePane;
    String dealquerry;
    String offerquerry;
    String simpleQuerry = "Select * from ";
    String table = "users";
    String needquerry;

    /**
     * init for controller
     */
    public void initialize(){
        Map<String, TableView<ObservableList>> map = Map.of("users", userTable, "offers", offerTable, "needs", needTable, "realtor", realtorTable, "deals", dealTable);
        dealquerry = "select id, realty as недвижимость, price as цена, " +
                "(SELECT concat (username, ' ',phone) FROM users WHERE offererid = id) as оферент, " +
                "(SELECT concat (username, ' ',phone) FROM users WHERE neederid = id) as заказчик, " +
                "(SELECT concat (firstname, ' ',lastname) FROM realtor WHERE id = realtorid) as риелтор, " +
                "address as адрес from deals";
        offerquerry = "SELECT id, (SELECT username FROM users WHERE clientid = id) as клиент, " +
                "(SELECT phone FROM users WHERE clientid = id) as 'номер тел', realty as недвижимость, " +
                "(SELECT concat (firstname, ' ',lastname) FROM realtor WHERE id = realtorid) as риелтор, " +
                "address as адрес, price as цена FROM offers";
        needquerry = "SELECT id, (SELECT username FROM users WHERE clientid = id) as клиент, " +
                "(SELECT phone FROM users WHERE clientid = id) as 'номер тел', realty as недвижимость, " +
                "(SELECT concat (firstname, ' ',lastname) FROM realtor WHERE id = realtorid) as риелтор," +
                " address as адрес, minprice as 'мин. цена', maxprice as 'макс. цена' FROM needs";
        fillTables();
        deleteBtn.setOnAction(event ->{
            TableView<ObservableList> current = map.get(tablePane.getSelectionModel().getSelectedItem().getId());
            ObservableList selected = current.getSelectionModel().getSelectedItem();
            current.getItems().remove(selected);
            String id = String.valueOf(selected.get(0));
            dbHandler = DBHandler.getDBHandler();
            dbHandler.deleteRow(tablePane.getSelectionModel().getSelectedItem().getId(), id);
        });
        exitBtn.setOnAction(event ->{
            exitBtn.getScene().getWindow().hide();
            Swap.openAnotherWindow("view/authorization.fxml");
        });
        newRealtorBtn.setOnAction(event ->{
            Swap.openAnotherWindow("view/newRealtor.fxml");
        });
    }

    /**
     * Method for base filled Tables
     */
    public void fillTables(){
        try {
            Main.fill(needquerry, needTable);
            Main.fill(offerquerry, offerTable);
            Main.fill(dealquerry, dealTable);
            Main.fill(simpleQuerry + "users", userTable);
            Main.fill(simpleQuerry + "realtor", realtorTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package sample.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import sample.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.objects.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class PersonalArea {
    User user;
    DBHandler dbHandler;
    @FXML TableView<ObservableList> needsTable;
    @FXML TableView<ObservableList> offersTable;
    @FXML TableView<ObservableList> dealsTable;
    @FXML private Button createNeed;
    @FXML private Button deleteBtn;
    @FXML private Button createOffer;
    @FXML private Label nameField;
    @FXML private Button exitBtn;
    @FXML private TabPane tablesPane;
    Map<String, TableView<ObservableList>> map;

    /**
     * preloading
     * @param user current user
     */
    public void initData(User user) {
        this.user = user;
        nameField.setText(user.getName());
        String needQuerry = "SELECT id, realty as недвижимость, minprice as 'мин. цена', maxprice as 'макс. цена'," +
                " (SELECT concat (firstname, ' ',lastname) FROM realtor WHERE id = realtorid) as риэлтор, " +
                "address as адрес FROM " + Constants.NEED_TABLE + " WHERE " + Constants.CLIENTID + " = " + user.getId();
        String offerQuerry = "SELECT id, realty as недвижимость, price as цена, (SELECT concat (firstname, ' ',lastname) FROM" +
                " realtor WHERE id = realtorid) as риэлтор, address as адрес FROM " + Constants.OFFER_TABLE + " WHERE "
                + Constants.CLIENTID + " = " + user.getId();
        String dealQuery = "select realty as недвижимость, price as цена, " +
                "(SELECT concat (username, ' ',phone) FROM users WHERE offererid = id) as оферент, " +
                "(SELECT concat (username, ' ',phone) FROM users WHERE neederid = id) as заказчик, " +
                "(SELECT concat (firstname, ' ',lastname) FROM realtor WHERE id = realtorid) as риелтор, " +
                "address as адрес from " + Constants.DEAL_TABLE + " where neederid = " + user.getId() +
                " OR offererid = " + user.getId();
        try {
//            Main.fill(needQuerry, needsTable);
//            Main.fill(offerQuerry, offersTable);
            Main.fill(dealQuery, dealsTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * init for controller
     */
    public void initialize() {
        map = Map.of("needs", needsTable, "offers", offersTable, "deals", dealsTable);
        createOffer.setOnAction(event -> {
            Swap.openCreateOfferWindow("view/createOffer.fxml", user);
        });
        exitBtn.setOnAction(event -> {
            exitBtn.getScene().getWindow().hide();
            Swap.openAnotherWindow("view/authorization.fxml");
        });
        createNeed.setOnAction(event -> {
            Swap.openCreateNeedWindow("view/createNeed.fxml", user);
        });
        dealsTable.setOnMouseClicked(e -> deleteBtn.setDisable(true));
        offersTable.setOnMouseClicked(e -> deleteBtn.setDisable(false));
        needsTable.setOnMouseClicked(e -> deleteBtn.setDisable(false));
        deleteBtn.setOnAction(event -> {
            String tab = tablesPane.getSelectionModel().getSelectedItem().getId();
            System.out.println(tab);
            ObservableList selected = map.get(tab).getSelectionModel().getSelectedItem();
            map.get(tab).getItems().remove(selected);
            String id = String.valueOf(selected.get(0));
            dbHandler = DBHandler.getDBHandler();
            dbHandler.deleteRow(tab, id);
        });
    }
}
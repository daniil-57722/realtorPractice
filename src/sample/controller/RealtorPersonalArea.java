package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.*;
import sample.objects.Deal;
import sample.objects.Realtor;
import java.sql.SQLException;

public class RealtorPersonalArea {
    private Realtor realtor;
    private String needQuerry, offerQuerry, dealsQuerry;
    private DBHandler dbHandler;
    @FXML private TableView<ObservableList> needsTable;
    @FXML private TableView<ObservableList> offersTable;
    @FXML private TableView<ObservableList> dealsTable;
    @FXML private Button createDealBtn;
    @FXML private Label nameField;
    @FXML private Button refreshBtn;
    @FXML private Button exitBtn;
    @FXML private Button deleteBtn;

    /**
     * preloading
     * @param realtor
     */
    public void initData(Realtor realtor){
        this.realtor = realtor;
        nameField.setText(realtor.getName());
        needQuerry = "SELECT id,(SELECT id FROM users WHERE clientid = id) as 'id клиента', (SELECT username FROM users WHERE clientid = id) as клиент, (SELECT phone FROM users WHERE " +
                "clientid = id) as 'номер тел', realty as недвижимость, address as адрес, minprice as 'мин. цена', maxprice" +
                " as 'макс. цена' FROM " + Constants.NEED_TABLE + " WHERE " + Constants.REALTORID + " = " + realtor.getId();
        offerQuerry = "SELECT id, (SELECT id FROM users WHERE clientid = id) as 'id клиента', (SELECT username FROM users WHERE clientid = id) as клиент,(SELECT phone FROM users" +
                " WHERE clientid = id) as 'номер тел', realty as недвижимость, address as адрес, price as цена FROM " +
                Constants.OFFER_TABLE + " where " + Constants.REALTORID + " = " + realtor.getId();
        dealsQuerry = "select id, realty as недвижимость, price as цена, " +
                "(SELECT concat (username, ' ',phone) FROM users WHERE offererid = id) as оферент, " +
                "(SELECT concat (username, ' ',phone) FROM users WHERE neederid = id) as заказчик, " +
                "(SELECT concat (firstname, ' ',lastname) FROM realtor WHERE id = realtorid) as риелтор, " +
                "address as адрес from " + Constants.DEAL_TABLE+ " where realtorid = " + realtor.getId();
        fillTables();
    }
    /**
     * init for controller
     */
    public void initialize(){
        createDealBtn.setOnAction(event ->{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Выберите потребность и предложение для создания сделки");
            alert.showAndWait();
            ObservableList selectedNeed = needsTable.getSelectionModel().getSelectedItem();
            needsTable.getItems().remove(selectedNeed);
            ObservableList selectedOffer = offersTable.getSelectionModel().getSelectedItem();
            offersTable.getItems().remove(selectedOffer);
            Deal deal = new Deal(
                    selectedNeed.get(1).toString(),
                    selectedOffer.get(1).toString(),
                    selectedNeed.get(4).toString(),
                    selectedOffer.get(5).toString(),
                    selectedOffer.get(6).toString(),
                    Integer.parseInt(realtor.getId()));
            dbHandler = DBHandler.getDBHandler();
            try {
                dbHandler.addDeal(deal);
                dbHandler.deleteRow("needs", selectedNeed.get(0).toString());
                dbHandler.deleteRow("offers", selectedOffer.get(0).toString());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });
        exitBtn.setOnAction(event ->{
            exitBtn.getScene().getWindow().hide();
            Swap.openAnotherWindow("view/authorization.fxml");
        });
        dealsTable.setOnMouseClicked(event->deleteBtn.setDisable(false));
        needsTable.setOnMouseClicked(event->deleteBtn.setDisable(true));
        offersTable.setOnMouseClicked(event->deleteBtn.setDisable(true));
        refreshBtn.setOnAction(event ->fillTables());
        deleteBtn.setOnAction(event->dbHandler.deleteRow("deals", String.valueOf(dealsTable.getSelectionModel().getSelectedItem().get(0))));
    }
    public void fillTables(){
        try {
            Main.fill(needQuerry, needsTable);
            Main.fill(offerQuerry, offersTable);
            Main.fill(dealsQuerry, dealsTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
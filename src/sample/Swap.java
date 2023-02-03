package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.controller.*;
import sample.objects.Realtor;
import sample.objects.User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * form transfec class
 */
public class Swap {
    static public void openAnotherWindow(String name){
        try {
            Parent root = FXMLLoader.load(Main.class.getResource(name));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image("sample/assets/home.jpg"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static public void openPersonalAreaWindow(String name, User user) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.getIcons().add(new Image("sample/assets/home.jpg"));
        stage.setScene(new Scene(loader.load()));
        PersonalArea personalArea = loader.getController();
        personalArea.initData(user);
        stage.show();
    }
    static public void openCreateOfferWindow(String name, User user){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image("sample/assets/home.jpg"));
            stage.setScene(new Scene(loader.load()));
            CreateOfferController createOfferController = loader.getController();
            createOfferController.initData(user);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static public void openCreateNeedWindow(String name, User user){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
            Stage stage = new Stage();
            stage.setResizable(false);
//            stage.getIcons().add(new Image("sample/assets/home.jpg"));
            stage.setScene(new Scene(loader.load()));
            CreateNeedController createNeedController = loader.getController();
            createNeedController.initData(user);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static public void openPersonalAreaWindow(String name, Realtor realtor) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("sample/assets/home.jpg"));
        stage.setScene(new Scene(loader.load()));
        RealtorPersonalArea realtorPersonalArea = loader.getController();
        realtorPersonalArea.initData(realtor);
        stage.setResizable(false);
        stage.show();
    }
}

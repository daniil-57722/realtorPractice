package sample;

import sample.objects.*;
import java.sql.*;

public class DBHandler extends Config {
    Connection dbConnection;
    Statement statement;
    ResultSet result = null;
    private DBHandler(){}
    private static DBHandler dbHandler;
    //паттерн Singleton
    public static DBHandler getDBHandler(){
        if(dbHandler == null){
            dbHandler = new DBHandler();
        }
        return dbHandler;
    }
    //Установка соединения с бд
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        if(dbConnection==null){
            String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        }
        return dbConnection;
    }
    //Метод, поверяющий есть ли такой логин и пароль в бд
    public ResultSet checkLoginAndPass(String login, String password, String table) {
        String request = "SELECT * FROM " + table + " WHERE login = '" + login + "' AND password = '" + password + "'";
        try {
            statement = getDbConnection().createStatement();
            result = statement.executeQuery(request);
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
    //Регистрация пользователя
    public void signUpUser(User user) {
        String request = "INSERT INTO " + Constants.USER_TABLE +
                "(" + Constants.USER_NAME + "," + Constants.USER_PHONE +
                "," + Constants.USER_EMAIL + "," + Constants.USER_LOGIN +
                "," + Constants.USER_PASSWORD + ")VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //Создание предложения
    public void addOffer(Offer offer) {
        String request = "INSERT INTO " + Constants.OFFER_TABLE +
                "(" + Constants.CLIENTID + "," + Constants.REALTY +
                "," + Constants.PRICE + "," + Constants.REALTORID +
                ","+ Constants.ADRESS + ")VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, offer.getClient());
            preparedStatement.setString(2, offer.getType());
            preparedStatement.setInt(3, offer.getPrice());
            preparedStatement.setInt(4, offer.getRealtorid());
            preparedStatement.setString(5, offer.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //Создание потребности
    public void addNeed(Need need) {
        String request = "INSERT INTO " + Constants.NEED_TABLE +
                "(" + Constants.CLIENTID + "," + Constants.REALTY +
                "," + Constants.MINPRICE + "," + Constants.MAXPRICE +
                "," + Constants.REALTORID + "," + Constants.ADRESS + ")VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, need.getClient());
            preparedStatement.setString(2, need.getType());
            preparedStatement.setInt(3, need.getMinPrice());
            preparedStatement.setInt(4, need.getMaxPrice());
            preparedStatement.setInt(5, need.getRealtorid());
            preparedStatement.setString(6, need.getAdress());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //Создание сделки
    public void addDeal(Deal deal) throws SQLException, ClassNotFoundException {
        String request = "INSERT INTO " + Constants.DEAL_TABLE +
                "(" + Constants.REALTY + "," + Constants.PRICE +
                "," + Constants.OFFERER + "," + Constants.NEEDER +
                "," + Constants.REALTORID + "," + Constants.ADRESS +
                ")VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, deal.getRealty());
            preparedStatement.setInt(2, Integer.parseInt(deal.getPrice()));
            preparedStatement.setInt(3, Integer.parseInt(deal.getIdOfferer()));
            preparedStatement.setInt(4, Integer.parseInt(deal.getIdNeeder()));
            preparedStatement.setInt(5, deal.getRealtorId());
            preparedStatement.setString(6, deal.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    // удаление по id
    public void deleteRow(String table, String id) {
        try {
            statement = getDbConnection().createStatement();
            statement.executeUpdate("DELETE FROM " + table + " WHERE id = " + id);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    // Выполнение несложных запросов
    public ResultSet querry(String querry) {
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(querry,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = preparedStatement.executeQuery(querry);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
    //Добавление риелтора
    public void newRealtor(Realtor realtor) {
        String request = "INSERT INTO " + Constants.REALTOR +
                "(" + Constants.FIRSTNAME + "," + Constants.LASTNAME +
                "," + Constants.PATRONYMIC + "," + Constants.COMISSION +
                "," + Constants.USER_LOGIN + "," + Constants.USER_PASSWORD +
                ")VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, realtor.getName());
            preparedStatement.setString(2, realtor.getLastname());
            preparedStatement.setString(3, realtor.getPatronymic());
            preparedStatement.setString(4, realtor.getComission());
            preparedStatement.setString(5, realtor.getLogin());
            preparedStatement.setString(6, realtor.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}

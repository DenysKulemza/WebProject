package db;

import db.entity.Student;
import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private static DBManager instance;

    public static synchronized DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager(){}

    private static final String URL = "jdbc:mysql://localhost:3306/education?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Gladiator228";

    private static final String SELECT = "SELECT * FROM student";

    private static final String SELECT_ONE = "SELECT * FROM student WHERE email=?";

    private static final String DELETE = "DELETE FROM student WHERE id=?";

    private static final String INSERT = "INSERT INTO student (id, email, name, Surname) values(?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE student SET id=?, email=?, name=?, Surname=? WHERE id=?";

    public static ArrayList<Student> select(){

        ArrayList<Student> students = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT);
                while (resultSet.next()){
                    String email = resultSet.getString(2);
                    String name = resultSet.getString(3);
                    String surName = resultSet.getString(4);
                    Student student = new Student(name, surName, email);
                    students.add(student);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return students;
    }

    public static boolean selectOne(String email) {
        String login = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){

                try(PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ONE)){
                    preparedStatement.setString(1, email);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        login = resultSet.getString(2);
                    }
                    if(email.equals(login)){
                        return true;
                    }
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    public static int insert(Student student) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){

                try(PreparedStatement preparedStatement = conn.prepareStatement(INSERT)) {
                    preparedStatement.setInt(1, student.getId());
                    preparedStatement.setString(2, student.getEmail());
                    preparedStatement.setString(3, student.getName());
                    preparedStatement.setString(4, student.getSurName());

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    public static int update(Student student) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){

                try(PreparedStatement preparedStatement = conn.prepareStatement(UPDATE)){
                    preparedStatement.setString(3, student.getEmail());
                    preparedStatement.setString(2, student.getName());
                    preparedStatement.setInt(1, student.getId());

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    public static int delete(int id) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){

                try(PreparedStatement preparedStatement = conn.prepareStatement(DELETE)){
                    preparedStatement.setInt(1, id);

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

}

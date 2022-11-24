package com.efisioterapia.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.efisioterapia.login.bean.LoginBean;

public class LoginDao {
	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/efisioterapiatest?useSSL=false", "postgres", "postgres");

            PreparedStatement preparedStatement = connection
            .prepareStatement("SELECT * FROM usuario WHERE login = ? and senha = ? ")) {
            preparedStatement.setString(1, loginBean.getLogin());
            preparedStatement.setString(2, loginBean.getSenha());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {

        	printSQLException(e);
        }
        return status;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}

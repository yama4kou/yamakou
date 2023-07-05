package com.starsoft1.bms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.starsoft1.bms.model.UserModel;

@Repository
public class UserDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sampleDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public void saveUser(UserModel user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // データベースへの接続
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // SQL文の準備と実行
            String sql = "INSERT INTO user (companyName, departmentName, lastName, firstName, lastNameKana, firstNameKana, email, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getCompanyName());
            statement.setString(2, user.getDepartmentName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastNameKana());
            statement.setString(6, user.getFirstNameKana());
            statement.setString(7, user.getEmail());
            statement.setString(8, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("データベースエラー: " + e.getMessage());
        } finally {
            // リソースの解放
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public UserModel getUserByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserModel user = null;

        try {
            // データベースへの接続
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL文の準備と実行
            String sql = "SELECT * FROM user WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setCompanyName(resultSet.getString("companyName"));
                user.setDepartmentName(resultSet.getString("departmentName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastNameKana(resultSet.getString("lastNameKana"));
                user.setFirstNameKana(resultSet.getString("firstNameKana"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("データベースエラー: " + e.getMessage());
        } finally {
            // リソースの解放
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
    
    public void updateUser(UserModel user, UserModel newUser) {
    	
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // データベースへの接続
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL文の準備と実行
            String sql = "UPDATE user SET companyName = ?, departmentName = ?, lastName = ?, firstName = ?, lastNameKana = ?, " +
                    "firstNameKana = ?, email = ?, password = ? WHERE Id = ?";
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, newUser.getCompanyName());
            statement.setString(2, newUser.getDepartmentName());
            statement.setString(3, newUser.getLastName());
            statement.setString(4, newUser.getFirstName());
            statement.setString(5, newUser.getLastNameKana());
            statement.setString(6, newUser.getFirstNameKana());
            statement.setString(7, newUser.getEmail());
            statement.setString(8, newUser.getPassword());
            statement.setInt(9, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("データベースエラー: " + e.getMessage());
        } finally {
            // リソースの解放
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deleteUser(UserModel user) {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// データベースへの接続
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// SQL文の準備と実行
			String sql = "DELETE FROM user WHERE Id = ?";

			statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("データベースエラー: " + e.getMessage());
		} finally {
			// リソースの解放
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }

}

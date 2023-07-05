package com.starsoft1.bms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.starsoft1.bms.model.UserModel;

@Repository
public class LoginDAOImpl implements LoginDAO {

    private final JdbcTemplate jdbcTemplate;

    public LoginDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @SuppressWarnings("deprecation")
	@Override
    public UserModel getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
    }

    private static class UserRowMapper implements RowMapper<UserModel> {
        @Override
        public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserModel userModel = new UserModel();
            userModel.setId(rs.getInt("id")); 
            userModel.setEmail(rs.getString("email"));
            userModel.setPassword(rs.getString("password"));
            // 他のユーザープロパティを必要に応じて設定します
            return userModel;
        }
    }
}
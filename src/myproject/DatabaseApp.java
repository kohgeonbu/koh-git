package myproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseApp {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/my_database";
        String username = "root";
        String password = "root";
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("MariaDB에 성공적으로 연결되었습니다!");
            
            
            String insertQuery = "INSERT INTO 회원 (userName, userEmail) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, "홍길동");
                preparedStatement.setString(2, "hong@example.com");
                preparedStatement.executeUpdate();
                System.out.println("데이터 삽입 성공!");
            }
            
            String selectQuery = "SELECT * FROM 회원";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("데이터 조회 결과:");
                while (resultSet.next()) {
                    int userID = resultSet.getInt("userID");
                    String userName = resultSet.getString("userName");
                    String userEmail = resultSet.getString("userEmail");
                    System.out.println("ID: " + userID + ", 이름: " + userName + ", 이메일: " + userEmail);
                }
            }

        } catch (SQLException e) {
            System.err.println("MariaDB 연결 실패: " + e.getMessage());
        }
    }
}
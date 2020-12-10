/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.UserAccount;
import beans.Camera;
/**
 *
 * @author lucaf
 */
public class DBUtils {

    public static UserAccount findUser(Connection conn, //
            String userName, String password) throws SQLException {

        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a " //
                + " where a.User_Name = ? and a.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String gender = rs.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }

    public static UserAccount findUser(Connection conn, String userName) throws SQLException {

        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "//
                + " where a.User_Name = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }
    
    public static List<Camera> queryCamera(Connection conn) throws SQLException {
        String sql = "Select a.Ip, a.Username, a.Password from Camera a ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Camera> list = new ArrayList<>();
        while (rs.next()) {
            String ip = rs.getString("Ip");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            Camera camera = new Camera();
            camera.setIp(ip);
            camera.setUsername(username);
            camera.setPassword(password);
            list.add(camera);
        }
        return list;
    }
    
    public static Camera findCamera(Connection conn, String ip) throws SQLException {
        String sql = "Select a.Ip, a.Username, a.Password from Camera a where a.Ip=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, ip);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            Camera camera = new Camera(ip, username, password);
            return camera;
        }
        return null;
    }
    
    public static void updateCamera(Connection conn, Camera camera) throws SQLException {
        String sql = "Update Camera set Username=?, Password=? where Ip=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, camera.getUsername());
        pstm.setString(2, camera.getPassword());
        pstm.setString(3, camera.getIp());
        pstm.executeUpdate();
    }
    
    public static void insertCamera(Connection conn, Camera camera) throws SQLException {
        String sql = "Insert into Camera(Ip, Username, Password) values (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, camera.getIp());
        pstm.setString(2, camera.getUsername());
        pstm.setString(3, camera.getPassword());

        pstm.executeUpdate();
    }
    
    public static void deleteCamera(Connection conn, String ip) throws SQLException {
        String sql = "Delete From Camera where Ip= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, ip);

        pstm.executeUpdate();
    }
}

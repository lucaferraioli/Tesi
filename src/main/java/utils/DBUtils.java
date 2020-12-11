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
        String sql = "Select a.Name, a.Ip, a.Username, a.Password, a.Port, a.Url from Camera a ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Camera> list = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("Name");
            String ip = rs.getString("Ip");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            String port = rs.getString("Port");
            String url = rs.getString("Url");
            Camera camera = new Camera();
            camera.setName(name);
            camera.setIp(ip);
            camera.setUsername(username);
            camera.setPassword(password);
            camera.setPort(port);
            camera.setUrl(url);
            list.add(camera);
        }
        return list;
    }

    public static Camera findCamera(Connection conn, String name) throws SQLException {
        String sql = "Select a.Name, a.Ip, a.Username, a.Password, a.Port, a.Url from Camera a where a.Name=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {

            String ip = rs.getString("Ip");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            String port = rs.getString("Port");
            String url = rs.getString("Url");
            Camera camera = new Camera(name, ip, username, password, port, url);
            return camera;
        }
        return null;
    }

    public static void updateCamera(Connection conn, Camera camera) throws SQLException {
        String sql = "Update Camera set Ip=?, Username=?, Password=?, Port=?, Url=? where Name=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, camera.getIp());
        pstm.setString(2, camera.getUsername());
        pstm.setString(3, camera.getPassword());
        pstm.setString(4, camera.getPort());
        pstm.setString(5, camera.getUrl());
        pstm.setString(6, camera.getName());
        pstm.executeUpdate();
    }

    public static void insertCamera(Connection conn, Camera camera) throws SQLException {
        String sql = "Insert into Camera(Name, Ip, Username, Password, Port, Url) values (?,?,?,?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, camera.getName());
        pstm.setString(2, camera.getIp());
        pstm.setString(3, camera.getUsername());
        pstm.setString(4, camera.getPassword());
        pstm.setString(5, camera.getPort());
        pstm.setString(6, camera.getUrl());

        pstm.executeUpdate();
    }

    public static void deleteCamera(Connection conn, String name) throws SQLException {
        String sql = "Delete From Camera where Name= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, name);

        pstm.executeUpdate();
    }
}

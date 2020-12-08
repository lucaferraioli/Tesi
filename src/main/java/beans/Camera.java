/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author lucaf
 */
public class Camera {
    
    private String ip;
    private String username;
    private String password;
    
    public Camera() {

    }

    public Camera(String ip, String username, String password) {
        this.ip = ip;
        this.username = username;
        this.password = password;
    }

    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    
    
    
}



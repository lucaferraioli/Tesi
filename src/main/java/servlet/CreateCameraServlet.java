/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Camera;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.DBUtils;
import utils.MyUtils;

/**
 *
 * @author lucaf
 */
@WebServlet(name = "CreateCameraServlet", urlPatterns = {"/createCamera"})
public class CreateCameraServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CreateCameraServlet() {
        super();
    }

    // Show camera creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createCameraView.jsp");
        dispatcher.forward(request, response);
    }

    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(request);

        String ip = (String) request.getParameter("ip");
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");

        String errorString = null;

        Camera camera = new Camera(ip, username, password);
        
        // Camera Ip is the string literal [a-zA-Z_0-9] and dot
        // with at least 1 character
        String regex = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
 
        if (ip == null || !ip.matches(regex)) {
            errorString = "Camera ip invalid!";
        }
 

        if (errorString == null) {
            try {
                DBUtils.insertCamera(conn, camera);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("camera", camera);

         // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createCameraView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/camera");
        }
    }
}

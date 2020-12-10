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
@WebServlet(urlPatterns = {"/editCamera"})
public class EditCameraServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditCameraServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        
        String ip = (String) request.getParameter("ip");
        
        Camera camera = null;
        
        String errorString = null;
 
        try {
            camera = DBUtils.findCamera(conn, ip);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        // If no error.
        // The product does not exist to edit.
        // Redirect to cameraList page.
        if (errorString != null && camera == null) {
            response.sendRedirect(request.getServletPath() + "/camera");
            return;
        }
        
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("camera", camera);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editCameraView.jsp");
        dispatcher.forward(request, response);
         
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        
        String ip = (String) request.getParameter("ip");
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        
        Camera camera = new Camera(ip, username, password);
 
        String errorString = null;
        
        try {
            DBUtils.updateCamera(conn, camera);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("camera", camera);
        
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editCameraView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/camera");
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

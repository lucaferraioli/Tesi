/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

/**
 *
 * @author lucaf
 */
import beans.Camera;
import beans.UserAccount;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public HomeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check User has logged on
        UserAccount loginedUser = MyUtils.getLoginedUser(session);

        // Not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // Store info to the request attribute before forwarding.
        request.setAttribute("user", loginedUser);

        Connection conn = MyUtils.getStoredConnection(request);

        String errorString = null;
        List<Camera> list = null;

        try {
            list = DBUtils.queryCamera(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        for (Camera camera : list) {
            String url = " --intf dummy " + camera.getUrl() + " --sout=\"#transcode{vcodec=theo,acodec=vorb,ab=128,channels=2,samplerate=44100}:http{mux=ogg,dst=:" + camera.getPort() + "/" + camera.getName() + "}\" --sout-keep --loop";

            System.out.println(url);
            Runtime.getRuntime().exec("cmd /c start vlc" + url);

        }

        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("cameraList", list);

        // If the user has logged in, then forward to the page
        // /WEB-INF/views/homeView.jsp
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

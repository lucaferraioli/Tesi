<%-- 
    Document   : homeView
    Created on : 17 ago 2020, 13:34:12
    Author     : lucaf
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home Page</title>
    </head>
    <body>

        <jsp:include page="_header.jsp"></jsp:include>
        <jsp:include page="_menu.jsp"></jsp:include>

            <h3>Home Page</h3>

            <video width="500" controls autoplay>

                <source src="http://localhost:8082/camera1"
                        type="video/mp4">

                Sorry, your browser doesn't support embedded videos.
            </video>

        <jsp:include page="_footer.jsp"></jsp:include>

    </body>
</html>

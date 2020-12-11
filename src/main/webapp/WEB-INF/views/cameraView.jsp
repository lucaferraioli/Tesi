<%-- 
    Document   : cameraView
    Created on : 21 ago 2020, 11:45:32
    Author     : lucaf
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Camera</title>
    </head>
    <body>
        <jsp:include page="_header.jsp"></jsp:include>
        <jsp:include page="_menu.jsp"></jsp:include>
            <table border="1" cellpadding="5" cellspacing="1" >
                <tr>
                    <th>Name</th>
                    <th>Ip</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Port</th>
                    <th>Url</th>
                    <th>Edit</th>
                    <th>Delete</th>

                </tr>
            <c:forEach items="${cameraList}" var="camera" >
                <tr>
                    <td>${camera.name}</td>
                    <td>${camera.ip}</td>
                    <td>${camera.username}</td>
                    <td>${camera.password}</td>
                    <td>${camera.port}</td>
                    <td>${camera.url}</td>
                    <td>
                        <a href="editCamera?name=${camera.name}">Edit</a>
                    </td>
                    <td>
                        <a href="deleteCamera?name=${camera.name}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="createCamera" class="btn btn-info" role="button">Add Camera</a>

        <jsp:include page="_footer.jsp"></jsp:include>
    </body>
</html>

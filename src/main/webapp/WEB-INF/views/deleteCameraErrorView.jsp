<%-- 
    Document   : deleteCameraErrorView
    Created on : 8-dic-2020, 19.03.54
    Author     : lucaf
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Delete Camera</title>
 </head>
 
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
    
    <h3>Delete Camera</h3>
    
    <p style="color: red;">${errorString}</p>
    <a href="cameraList">Camera List</a>
    
    <jsp:include page="_footer.jsp"></jsp:include>
    
 </body>
</html>
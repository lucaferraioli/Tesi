<%-- 
    Document   : editCameraView
    Created on : 8-dic-2020, 18.33.13
    Author     : lucaf
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit Camera</title>
   </head>
   <body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Edit Camera</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty camera}">
         <form method="POST" action="${pageContext.request.contextPath}/editCamera">
            <input type="hidden" name="ip" value="${camera.ip}" />
            <table border="0">
               <tr>
                  <td>Ip</td>
                  <td style="color:red;">${camera.ip}</td>
               </tr>
               <tr>
                  <td>Username</td>
                  <td><input type="text" name="username" value="${camera.username}" /></td>
               </tr>
               <tr>
                  <td>Password</td>
                  <td><input type="text" name="password" value="${camera.password}" /></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/camera">Annulla</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body>
</html>
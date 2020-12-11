<%-- 
    Document   : createCameraView
    Created on : 22 ago 2020, 16:29:25
    Author     : lucaf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Camera</title>
    </head>
    <body>
        <jsp:include page="_header.jsp"></jsp:include>
        <jsp:include page="_menu.jsp"></jsp:include>

            <h3>Create Camera</h3>

            <p style="color: red;">${errorString}</p>
        
        <form method="POST" action="${pageContext.request.contextPath}/createCamera">
         <table border="0">
             <tr>
               <td>Name</td>
               <td><input type="text" name="name" value="${camera.name}" /></td>
            </tr>
            <tr>
               <td>Ip</td>
               <td><input type="text" name="ip" value="${camera.ip}" /></td>
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
               <td>Port</td>
               <td><input type="text" name="port" value="${camera.port}" /></td>
            </tr>
            <tr>
               <td>Url</td>
               <td><input type="text" name="url" value="${camera.url}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="${pageContext.request.contextPath}/camera">Annulla</a>
               </td>
            </tr>
         </table>
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
    </body>
</html>

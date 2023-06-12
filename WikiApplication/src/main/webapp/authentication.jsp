<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Wiki - Authentication</title>
	</head>
	<body>

		<!-- Database Connection -->
		<sql:setDataSource var="connecion" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/DB_WIKI" user="root" password="bit235mysql"/>
			
		<!-- Database Query for checking required user account is available -->
		<sql:query dataSource="${connecion}" var="users">SELECT count(*) as count FROM users WHERE username="${param.username}" && password="${param.password}"</sql:query>
			
		<c:forEach items="${users.rows}"  var="r"> 
			<c:choose>
				<c:when test="${r.count != 0}">    
					<!-- redirect to the success page if username and password are matched -->
				    <c:redirect url="/ArticleController?action=auth_all_articles"/>
				</c:when>    
				<c:otherwise>   
					<!-- redirect to the error page if username and password are not matched -->
					<c:redirect url="error_page.jsp"/>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
	</body>
</html>
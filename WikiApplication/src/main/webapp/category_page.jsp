<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wiki - Category</title>
		<style>
			nav {
			  width: 1200px;
			  margin: 0 auto;
			  height: 70px;
			}

			.navigation-bar {
			  background-color: #333;
			  height: 70px;
			  width: 100%;
			}
			
			.navigation-bar h1{
				float:left;
				margin: 0px;
				padding-top: 20px;
				color: white;
			}
			
			.navigation-bar ul {
			  padding: 0px;
			  margin: 0px;
			  display:inline-block;
			  vertical-align:top;
			  float: right;
			}
			
			.navigation-bar li {
			  list-style-type: none;
			  padding: 0px;
			  height: 24px;
			  margin-top: 4px;
			  margin-bottom: 4px;
			  display: inline;
			}
			
			.navigation-bar li a {
			  color: white;
			  font-size: 16px;
			  text-decoration: none;
			  line-height: 70px;
			  padding: 5px 15px;
			}
			
			.navigation-bar li a:hover {
			  opacity: 0.8;
			}
			
			.article_recent {
				font-size: 40px;
				margin: 28px;
				color: red;
			}
						
			.article-container {
			  display: flex;
			  flex-direction: row;
			}
			
			.article-box {
			  margin: 10px;
			  flex-grow: 1;
			  width: 100px;
			  border: 1px solid lightBlue;
			  padding: 5px;
			}
			
			
		</style>
</head>
<body>


	<div class="navigation-bar">
    	<nav>
      		<h1>Wiki Application</h1>

			<!-- Navigation Menu -->
		    <ul>
				<li><a href="${pageContext.request.contextPath}/ArticleController?action=list">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/ArticleController?action=all">Articles</a></li>		  
				<li><a href="${pageContext.request.contextPath}/CategoryController?action=list">Category</a></li>
				<li><a href="login.jsp">Admin</a></li>
		   </ul>
       </nav>
    </div>
    
    <!-- All Category List -->
	<c:forEach var="category" items="${listALLCategories}"> 
		<div class="article-container">
			<div class="article-box">
			<p><b><c:out value="${category.getCname()}"></c:out></b><br /><c:out value="${category.getCdescription()}"></c:out></p>
			</div>
		</div>
	</c:forEach>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Full Article</title>
		<style>
			body {
				margin: 0;
				padding: 0;
			}
		    .sidebar {
		      	width: 200px;
		      	height: 100vh;
		      	background-color: #f2f2f2;
		      	padding: 20px;
		      	float: left;
		      	position: fixed;
		    }
		
		    .brand-name{
		      	text-align: center;
		      	margin-bottom: 20px;
		    }
		
		    .nav-links {
		      	list-style-type: none;
		      	padding: 2%;
		      	margin-bottom: 20px;
		      	text-align: center;
		      	cursor: pointer;
		    }
		
		    .nav-links li {
		      	margin-bottom: 10px;
		      	padding: 5%;
		      	background: lightBlue;
		      	border-radius: 10px;
		    }
		    
		    .nav-links a {
		      	text-decoration: none;
		      	color: #333;
		    }
		    
		    .nav-links li:hover {
		    	background: #A7C7E7;
		    }
		    
		    .content {
		     	margin-left: 220px;
		      	padding: 30px;
		    }
		  
		</style>
	</head>
	<body>
		<div class="sidebar">
		    <div class="brand-name"><h1>Wiki</h1></div>
	
		    <ul class="nav-links">
		    	<li><a href="${pageContext.request.contextPath}/ArticleController?action=auth_all_articles">All Article</a></li>
		      	<li><a href="${pageContext.request.contextPath}/CategoryController?action=auth_categories">Create New Article</a></li>
		      	<li><a href="${pageContext.request.contextPath}/CategoryController?action=auth_list">Create New Category</a></li>
		      	<li><a href="${pageContext.request.contextPath}/ArticleController?action=auth_hidden_articles">Hidden Article List</a></li>
		      	<li><a href="${pageContext.request.contextPath}/ArticleController">Logout</a></li>
		    </ul>
	  	</div>

  		<div class="content">
 
 			<div class="article-container">
 				<h2>${article.getAtitle()}</h2>
 				<p>Category : ${article.getAcategory()}</p>
 				<p>Visibility : <c:choose><c:when test="${article.getApublic()}"> Public</c:when><c:otherwise>Private</c:otherwise></c:choose></p>
 				<p>Created Date: ${article.getAcreated_at()}</p><br />
 				<p>${article.getAcontent()}</p>
 			</div>

		
  		</div>

	</body>
</html>
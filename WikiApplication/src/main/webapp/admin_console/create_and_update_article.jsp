<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wiki - Manage Article</title>
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
      margin-left: 220px; /* Adjust this value to accommodate the sidebar width */
      padding: 30px;
    }
    
    .fields {
    	margin: 10px;
    }
    
    button {
    	padding: 10px 30px;
    	margin: 10px;
    	cursor: pointer;S
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
  	
  	<c:if test="${article == null}">
  		<h1>New Article</h1>
  		<form action="${pageContext.request.contextPath}/ArticleController?action=new" method="post">
  	</c:if>
  	<c:if test="${article != null}">
  		<h1>Update Article</h1>
  		<form action="${pageContext.request.contextPath}/ArticleController?action=auth_update" method="post">
  	</c:if>
  		<input type="hidden" name="id" value="${article.getAid()}" /><br />
  		
  		<div class="fields">
  			<label for="title">Title</label><br />
	  		<input style="width: 50%; padding: 1%;" type="text" name="title" placeholder="eg: New Treands" value="${article.getAtitle()}" required /><br />
  		</div>

	  	<div class="fields">
	  		<label for="category">Category</label><br />
		  	<select name="category" style="width: 30%; padding: 1%;" required>
			  	<c:forEach var="category" items="${listALLCategories}"> 
			  		<c:choose>
			  			<c:when test="${article != null && category.getCname() == article.getAcategory()}">
			  				<option value="${category.getCid()}" selected>${category.getCname()}</option>	
			  			</c:when>
			  			<c:otherwise>
			  				<option value="${category.getCid()}">${category.getCname()}</option>	
			  			</c:otherwise>
			  		</c:choose>	
				</c:forEach>
			</select><br />
		</div>
	  	
	  	<div class="fields">
		  	<label for="content">Content</label><br />
		  	<textarea name="content" style="width: 50%; padding: 1%;" rows="20">${article.getAcontent()}</textarea>
	  	</div>
	  	<div class="fields">
	  		<label for="visibility">Visibility</label><br />
		  	<select name="is_private" style="width: 30%; padding: 1%;" required>
		  		<c:choose>
			  		<c:when test="${article.getApublic()}">
			  			<option value="false">Private</option>
					<option value="true" selected>Public</option>
			  		</c:when>
			  		<c:otherwise>
			  			<option value="false" selected>Private</option>
						<option value="true">Public</option>		
			  		</c:otherwise>
			  	</c:choose>		
			</select><br />
	  	</div>
	  	
	  	<button type="submit" ><c:if test="${article == null}">
	  		Create
	  	</c:if>
	  	<c:if test="${article != null}">
	  		Update
	  	</c:if></button>
  	</form>
  </div>
</body>
</html>
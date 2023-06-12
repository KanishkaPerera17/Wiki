<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Category</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
      	display: grid;
  		grid-template-columns: 60% 40%;
    }
    
    .content-right {
    	padding: 5%;
    	width: 90%;
    	height: 80vh;
    	overflow:auto;
		background: lightGray;    
    }
    
    .category-box {
    	padding: 0 10px;
    	border: 1px solid white;
    	display: grid;
  		grid-template-columns: 90% 10%;
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
	  <div class="content-left">
	    	<h1>New Category</h1>
		  	<form action="${pageContext.request.contextPath}/CategoryController?action=new" method="post">
		  	
		  		<div class="fields">
		  			<label for="name">Category Name</label><br />
			  		<input style="width: 50%; padding: 1%;" type="text" name="name" placeholder="eg: New Treands" required /><br />
		  		</div>
		
			  	<div class="fields">
				  	<label for="description">Description</label><br />
				  	<textarea name="description" style="width: 80%; padding: 1%;" rows="20"></textarea>
			  	</div>
			  	
			  	<button type="submit" >Add</button>
		  	</form>
	  </div>
	  <div class="content-right">
	  		<h1>Category List</h1>
	  		
			<!-- All Category List -->
			<c:forEach var="category" items="${listALLCategories}"> 
				<div class="category-box">
					<h3>${category.getCname()}</h3>
					<c:if test="${category.getCname() != 'Unknown'}">
						<a onClick="deleteConfirmation('${pageContext.request.contextPath}/CategoryController?action=delete&id=${category.getCid()}')">
							<i class="fa fa-trash-o" style="font-size:24px; padding: 35%; color: red; cursor: pointer;"></i>
						</a>					
					</c:if>
				</div>
			</c:forEach>
	  </div>
  </div>
  
  <script>
		function deleteConfirmation(path) {
			var result = confirm("Are you sure to delete? This process cannot be undone!");
			if(result){
				location.href = path;
			}
		}
  </script>

</body>
</html>
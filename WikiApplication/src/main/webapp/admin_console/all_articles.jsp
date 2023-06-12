<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Wiki - Dashboard</title>
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
		  
		  	.article-container {
				display: flex;
				flex-direction: row;
				text-align: center;
			}
					
			.article-box {
				margin: 10px;
				flex-grow: 1;
				width: 100px;
				border: 1px solid lightBlue;
				padding: 10px;
			}
			
			.article-category {
				color: white;
				background: purple;
				width: fit-content;
				padding: 5px 25px;
				border-radius: 20px;  
				margin: 5px auto;
				font-size: 16px;
			}
					
			.article-content {
				line-height: 25px;
			    overflow: hidden;
			    text-overflow: ellipsis;
			    display: -webkit-box;
			    -webkit-line-clamp: 2;
			    line-clamp: 2; 
			    -webkit-box-orient: vertical;
			}
			
			.visibility {
				font-size: 14px;
				color: gray;
				text-decoration: italic;
			}	
			
			.main-panel, .main-panel a {
				text-decoration: none;
				font-size: 16px;
				color: gray;
				cursor: pointer;
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
  
	  		<h1>All Articles</h1>
	  		<c:forEach items="${AdminArticles}" var="articles"> 
		  		
					<div class="article-container">
						<div class="article-box">
							<h2><c:out value="${articles.getAtitle()}"></c:out>
							<span class="visibility">[<c:choose><c:when test="${articles.getApublic()}"> Public</c:when>
							<c:otherwise>Private</c:otherwise></c:choose> ]</span>
							<span class="main-panel"><br />
								<a href="${pageContext.request.contextPath}/ArticleController?action=auth_show_edit&id=${articles.getAid()}">Edit</a> | 
								<a onclick="deleteConfirmation('${pageContext.request.contextPath}/ArticleController?action=auth_delete_article&id=${articles.getAid()}&path=auth_all_articles')">Delete</a> | 
								<c:choose>
									<c:when test="${articles.getApublic()}"><a href="${pageContext.request.contextPath}/ArticleController?action=auth_show_hide&id=${articles.getAid()}&path=auth_all_articles">Hide</a></c:when>
									<c:otherwise><a href="${pageContext.request.contextPath}/ArticleController?action=auth_show_hide&id=${articles.getAid()}&path=auth_hidden_articles">Unhide</a></c:otherwise>
								</c:choose>
								
								
							</span></h2>
							<br /><span class="article-category"><c:out value="${articles.getAcategory()}"></c:out></span>					
							<p><span class="article-content"><c:out value="${articles.getAcontent()}"></c:out></span>
							<br /><a href="${pageContext.request.contextPath}/ArticleController?action=full_articles&id=${articles.getAid()}&path=admin_console/full_article.jsp">Read More</a></p>
							<p style="float: right; padding: 10px; background: lightGray;"><c:out value="${articles.getAcreated_at()}"></c:out></p>
						</div>
					</div>
			</c:forEach>
		
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
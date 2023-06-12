<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Full Article</title>
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
			  text-align:center;
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
			  text-align: center;
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
					
			.content {
		      	padding: 30px;
		      	height: 100%;
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
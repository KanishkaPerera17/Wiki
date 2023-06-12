<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Wiki - Home Page</title>
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
			
			.article_recent {
				font-size: 40px;
				margin: 28px;
				color: red;
				text-align:center;
			}
						
			.article-container {
			  display: flex;
			  flex-direction: row;
			}
			
			.article-box {
			  margin: 10px;
			  flex-grow: 1;
			  width: 100px;
			  text-align: center;
			  border: 1px solid lightBlue;
			}
			
			.article-content {
				line-height: 18px;
			  	max-height: 36px;
				overflow: hidden;
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
   
	<h2 class="article_recent">Recent Articles</h2>

	<!-- Display Recent articles -->
	<c:forEach var="articles" items="${listRecArticles}"> 
		<div class="article-container">
			<div class="article-box">
				<h2><c:out value="${articles.getAtitle()}"></c:out></h2>
				<p class="article-content"><c:out value="${articles.getAcontent()}"></c:out></p>
				<p><a href="${pageContext.request.contextPath}/ArticleController?action=full_articles&id=${articles.getAid()}&path=full_article.jsp">Read More</a></p>
			</div>
		</div>
	</c:forEach>

</body>
</html>
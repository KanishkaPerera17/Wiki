<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Wiki - Articles</title>
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
						
			.article-container {
			  display: flex;
			}
			
			.article-box {
			  margin: 10px;
			  flex-grow: 1;
			  border: 1px solid lightBlue;
			  padding: 0px 10px;
			}
			
			.article-category {
			  color: white;
			  background: purple;
			  width: fit-content;
			  padding: 5px 25px;
			  border-radius: 20px;
			  
			}
			
			.search {
			  width: 100%;
			  position: relative;
			  display: flex;
			  margin-top: 10px
			}
			
			.searchTerm {
			  width: 100%;
			  border: 3px solid lightBlue;
			  border-right: none;
			  padding: 5px;
			  height: 20px;
			  border-radius: 5px 0 0 5px;
			  outline: none;
			}
			
			.searchButton {
			  width: 100px;
			  height: 36px;
			  border: 1px solid lightBlue;
			  background: #00B4CC;
			  text-align: center;
			  color: white;
			  border-radius: 0 5px 5px 0;
			  cursor: pointer;
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

		<!-- Search By title -->
		<div class="search">
			<input type="text" class="searchTerm" placeholder="What are you looking for?" id="keyword">
		    <button type="submit" class="searchButton" onclick="getInputValue()">Search</button>
		</div>
		
		<script>
	      function getInputValue() {
	        let inputValue = document.getElementById("keyword").value;
	        window.location.replace('${pageContext.request.contextPath}/ArticleController?action=find&keyword='+inputValue);
	      }
	    </script>
	
		<!-- List all Articles -->
		<c:forEach var="articles" items="${listALLArticles}"> 
			<div class="article-container">
				<div class="article-box">
					<h2><c:out value="${articles.getAtitle()}"></c:out></h2>
					<div class="article-category"><c:out value="${articles.getAcategory()}"></c:out></div>
					<p><c:out value="${articles.getAcontent()}"></c:out></p>
					<p style="float: right; padding: 10px; background: lightGray;"><c:out value="${articles.getAcreated_at()}"></c:out></p>
				</div>
			</div>
		</c:forEach>
	</body>
</html>



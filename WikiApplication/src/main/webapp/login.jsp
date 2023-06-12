<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Wiki - Login</title>
		<style>
			.login_box {
				padding: 8%;
			}
			
			.login_box input {
				width: 250px;
				height: 40px;
				box-sizing: border-box;
			}
			
			.button {
				background: #ADD8E6;
				margin-top: 10px;
				border: none;	
				border-radius: 8px;		
				cursor: pointer;
			}
			
			.button:hover {
				background: #1E5162;
				color: white;		
			}
		</style>
	</head>
	<body>	
	
		<div class="login_box">
			<h1>Login</h1>
			
			<!-- Pass the data to the authentication page to check with database -->
			<form action="authentication.jsp" method="post">
			
			<label for="username">Username</label><br />
			<input type="text" name="username" required/><br /><br />
		
			<label for="password">Password</label><br />
			<input type="password" name="password" required/><br />
			
			<input class="button" type="submit" value="submit" />
			
			</form>	
		</div>

	</body>
</html>
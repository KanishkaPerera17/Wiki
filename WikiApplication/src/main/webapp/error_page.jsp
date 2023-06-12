<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Unauthorized Access</title>
		<style>
			.error_box {
				margin: 8%;
				margin-bottom: 0%;
				padding: 2%;
				border: 2px solid #dd2c00;
				text-align: center;
			}
			
			.error_box h1 {
				color: #dd2c00;
			}
			
		</style>
	</head>
	<body>
		<div class="error_box">
			<h1>Unauthorized Access</h1>
			<p>Your credentials are invalid. Try again with correct credentials</p>
			<a href="login.jsp"><p>Back to Login</p></a>
		</div>
	</body>
</html>
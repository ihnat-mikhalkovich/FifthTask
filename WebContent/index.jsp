<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>home</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div class="parant">
		<div class="block">
			<center>
				<form action="Controller" method="get">
					<input type="hidden" name="selectedPage" value="1"/>
					
					<input type="submit" value="SAX" name="parserType"/>
					<br/><br/>
					
					<input type="submit" value="StAX" name="parserType"/>
					<br/><br/>
					
					<input type="submit" value="DOM" name="parserType"/>
					<br/><br/>
				
				</form>
			</center>
		</div>
	</div>
</body>
</html>
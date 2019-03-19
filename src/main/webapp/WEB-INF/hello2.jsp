<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<h2>Hello World!!</h2>
<h2>${helloMessage}</h2>

<h2>Log lekker in met je userid, want dat weet je uit je hoofd.</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" name="username" placeholder="username">
    <input type="password" name="password" placeholder="password">
    <input type="submit" value="Login!" />
</form>
</body>
</html>

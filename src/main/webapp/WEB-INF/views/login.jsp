<html>
<head>
<title>LogIn!!</title>
</head>
<body>
    <p><span style="color: red; ">${errorMessage}</span></p>
    <form action="/login.do" method="POST">
        Name : <input name="name" type="text" /> Password : <input name="password" type="password" /> <input type="submit" />
    </form>
</body>
</html>
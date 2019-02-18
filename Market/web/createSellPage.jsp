<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sell</title>
        <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/sell-ajax.js" type="text/javascript"></script>
    </head>
    <body>
        <input type="button" id="home" value="Home" />
        <h1 id="msg">Please complete the inputs</h1>
        <form>
            Enter your name: <input type="text" id="seller" /> <br>
            Enter the item you want to sell: <input type="text" id="item" /> <br>
            Enter a description: <input type="text" id="description" /> <br>
            Enter your desired price: <input type="text" id="price" /> <br>
            <input type="button" id="sub" value="SUBMIT"/>
	</form>
    </body>
</html>

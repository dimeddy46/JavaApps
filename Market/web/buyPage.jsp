<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            table, th, td {
                border: 1px solid black;
            }   
            td a {
                 display:block;
                 width:100%;
            }
        </style>
        
        <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/buy-ajax.js" type="text/javascript"></script>
        <title>Sell listings</title>
    </head>
    <body>
        <input type="button" id="home" value="Home" />
        <h1>Items available to buy:</h1><br>

        <table id="princ">          
            <tr>
            <th>Item</th>
            <th>Sold by</th>
            <th>Price($)</th>
            <th>Listed on</th>
            <th>Buy</th>
            </tr>
        </table>
        <p id ="plm"></p>
    </body>
</html>

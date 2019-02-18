package Market;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/insertSell")
public class insertSell extends HttpServlet {
       private static final long serialVersionUID = 1L;
       static final String USER = "root";
       static final String PASS = "merepere";
       static final String DB_URL = "jdbc:derby://localhost:1527/MarketDB1";
       static int nrOrdin = 0;
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String seller1 = request.getParameter("sellerName");
                String item1 = request.getParameter("itemName");
                String desc1 = request.getParameter("desc");
		String price1 = request.getParameter("pricing");
                System.out.println(request.getRemoteAddr()+" SELLER:"+seller1+" ITEM:"+item1+" DESC:"+desc1+" PRICE:"+price1);
               try{ 
                   Random userID = new Random();
                   long b = new Date().getTime();
                   java.sql.Timestamp acum = new java.sql.Timestamp(b);
                   
                    Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);  
                    String SQL = "insert into LISTINGS(seller,item,description,price,userid,time) values(?,?,?,?,?,?)";
                    PreparedStatement stmt=conn.prepareStatement(SQL);  
                    stmt.setString(1,seller1);
                    stmt.setString(2,item1);
                    stmt.setString(3,desc1);
                    stmt.setFloat(4,Float.parseFloat(price1)); 
                    stmt.setInt(5,userID.nextInt(70000)); 
                    stmt.setTimestamp(6, acum);
                    stmt.executeUpdate();
                    conn.close();
                    nrOrdin++;
                } catch(SQLException ez){
                    System.out.println("Eroare "+ez.getMessage()); 
                }
                response.setContentType("text/html");
               response.getWriter().write("Completed!");
               
        }
}

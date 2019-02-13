
package Market;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/buyPrintListings")
public class buyPrintListings  extends HttpServlet {
       private static final long serialVersionUID = 1L;
       static final String USER = "root";
       static final String PASS = "merepere";
       static final String DB_URL = "jdbc:derby://localhost:1527/MarketDB1";
       static int nrOrdinComp = 0;
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               response.setContentType("text/html");   
               String tm = request.getParameter("tm");
               try{ 
                    if((insertSell.nrOrdin != nrOrdinComp || tm.equals("0")) && !tm.equals("2")){   // ------- afiseaza listari
                        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                        Statement stmt = conn.createStatement();                       
                        String seller1, item1, time2;
                        float price1;
                        String head = "<tr><th>Item</th><th>Sold by</th><th>Price($)</th><th>Listed on</th><th>Buy</th></tr>"; 
                        String sql = "SELECT seller,item,price,time FROM LISTINGS";
                        ResultSet rs = stmt.executeQuery(sql);     
                        while(rs.next()){
                            seller1 = rs.getString("seller");
                            item1 = rs.getString("item");       
                            price1 = rs.getFloat("price");
                            time2 = rs.getTimestamp("time").toString();
                            head += "<tr><td>"+item1+"</td><td>"+seller1+"</td><td>"+price1+"</td><td>"+time2.substring(0,19)+"</td>"+
                                    "<td><a href=\"#\" class=\"aff\" id="+rs.getRow()+">BUY</a></td></tr>";
                        }
                        rs.close();
                        conn.close();
                        response.getWriter().write(head);
                        nrOrdinComp = insertSell.nrOrdin;
                     } 
                    else if(tm.equals("2")){                               // --------------- apasare buy button
                            String seller = request.getParameter("seller"),item = request.getParameter("item");
                            if(seller.length() == 0 || item.length() == 0){
                                response.sendRedirect("http://localhost:8080/buyPage.jsp");
                                return;
                            }
                            System.out.println(seller+" "+item);
                            String sql = "delete from LISTINGS where seller=? and item=?";
                            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                            PreparedStatement stmt = conn.prepareStatement(sql);  
                            stmt.setString(1, seller);
                            stmt.setString(2, item);
                            if(stmt.executeUpdate() != 0){
                                Date d = new Date();
                                response.getWriter().write("You just bought: ");
                                System.out.println(d.getTime()+" ITEM: "+item+" vandut la "+request.getRemoteAddr()+" de catre "+seller);
                            }
                            else 
                                response.getWriter().write("This item isn't available anymore: ");
                            conn.close();
                            insertSell.nrOrdin++;
                    }
                    else response.getWriter().write("0");
                    
               } catch(SQLException ez ){System.out.println("EROARE !! : "+ez.toString()); }
               catch(NullPointerException z){response.sendRedirect("http://localhost:8080/buyPage.jsp");}

        }    
}
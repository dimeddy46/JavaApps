/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerHTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerHTTP {
    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
       try{ server = new ServerSocket(8080); } catch(IOException ex){
           System.out.println("SERVER ONLINE DEJA.");System.exit(1);
       }
       
       System.out.println("Listening for connection on port 8080 ...."); 
       while (true) { 
           Socket clientSocket = server.accept();
           System.out.println("Tocmai s-a conectat: "+clientSocket.getRemoteSocketAddress());
           Date today = new Date(); 
           String httpResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body><h1>"+today+
                   "</h1></body></html>";
           clientSocket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
           /*try{ InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream()); 
           BufferedReader reader = new BufferedReader(isr); String line = reader.readLine();
           while (!line.isEmpty()) { 
               System.out.println(line); 
               line = reader.readLine(); 
           }
           }catch(NullPointerException zx){System.out.println("nullptr "+ zx.toString());}*/


        }
      }
 /*   public static void main(String[] args) throws IOException {
       ServerSocket serverSocket = null;
       int port = 8080;
       try {
             serverSocket = new ServerSocket(8080); 
             System.out.println("Server deschis... Port:" + serverSocket.getLocalPort());
           } catch (IOException e) {
             System.out.println("Server deja online");
             System.exit(1);
         }
        String response = "<script type=\"text/javascript\">"
                +"function myFunction() {" 
                +"var x= document.getElementsByName(\"fname\")[0].value;"
                +"x+= \" \"+document.getElementsByName(\"lname\")[0].value; alert(x);}" 
                +"</script>";
        Socket clientSocket[] = new Socket[100];int count = 0;
        while(true){
         try {
            clientSocket[count] = serverSocket.accept();
            if(clientSocket[count] != null)                
                System.out.println("Tocmai s-a conectat:  " + clientSocket[count].getRemoteSocketAddress()+" nr."+count);
         } catch (IOException e) {
             System.err.println("Accept failed.");
             System.exit(1);
          }
            try{ 
                
                 PrintWriter out = new PrintWriter(clientSocket[count].getOutputStream());
                 out.println("HTTP/1.1 200 OK");
                 out.println("Content-Type: text/html");
                 out.println();
                 out.println("<p> Hello world </p> <h1>Why are you here Australia/Newtown?</h1> "+
                            "  First name: <input type=\"text\" name=\"fname\"><br>\n" +
                            "  Last name: <input type=\"text\" name=\"lname\"><br>\n" +
                            "  <button onclick=\"myFunction()\">Cltere me</button>"
                            );
                 
                 out.println(response);
                 out.flush();
                 out.close();
                 count++;if(count == 99)count = 0;
            }
            catch(IOException ex){
                System.out.println("eroare");
            }
        }
      }       */  
}

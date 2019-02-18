package Client;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client extends Thread{

    public static void main(String args[]) throws InterruptedException {
      String serverName = "localhost";
      int port = 8080;
      String mesaj= "";
      Scanner sc = new Scanner(System.in);
      while(true){
         try {
            System.out.println("Se asteapta conexiunea la " + serverName + " : " + port);
           Socket client = new Socket(serverName, port);
         
            System.out.println("Conectat la server! USER: "+client.getLocalPort()+
                    "\nComenzi disponibile: /all [msg], /pm [user] [msg],/exit\nScrie un mesaj: " );
         
         // ------------------ CITIRE MESAJ DE LA SERVER --------------
            Thread inp = new Thread() { 
            @Override
            public void run(){
                try {
                     DataInputStream in;
                     String msg;
                     while(true){
                        in = new DataInputStream(client.getInputStream());
                        msg = in.readUTF();
                        if(msg.equals("/stop")){System.out.println("Clientul a fost inchis de server.");System.exit(0);}
                        if(msg.startsWith("~"))
                        System.out.println(msg.substring(1)); 
                        else System.out.println("SERVER:" +msg); 
                     }
                 } catch (IOException ex) {
                        if(!client.isClosed()){
                          System.out.println("Server offline. Apasa orice tasta pentru reconectare");
                        }
                 }
                }
            };
            inp.start();
         // ------------- TRIMITERE MESAJ CATRE SERVER ------------------
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            do{
                 mesaj = sc.nextLine();
                 out.writeUTF(mesaj);        
            }
            while(!mesaj.equals("/exit"));
            client.close();
            System.exit(0);
            } catch (IOException e) {
                System.out.println("Server offline. Reincercare in 2 secunde...");
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }
}


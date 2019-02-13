package Server;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Server extends Thread {
   public static ServerSocket serverSocket;
   public static Socket usr[] = new Socket[100];
   public static int on, next, mx,k, codMsg = -1; // -------------- cod msg: -1 = msg normal, -2 = msg all, [0-n] = pv msg
   public static String mesaj = "";
   
   public static void afisare(){
       System.out.println("-------------------- USERS ---------------------");
       for(int i = 0 ;i<=mx;i++)
          if(usr[i] != null && usr[i].getPort() != 0) 
              System.out.println("ID: "+i+". "+usr[i].getRemoteSocketAddress());
       System.out.println("-------------------------------------------------");
   }

   @Override
    public void run() {          // ------------------------ TRIMITERE MESAJ CATRE CLIENT ------------------------------
        int flag = 0,cod = codMsg;
        String ms = mesaj;
        Scanner sc = new Scanner(System.in); 
        DataOutputStream out;  
        
        if(cod >= 0)      // ------------------ PM --------------
            try {  
              flag = -1;   
              out = new DataOutputStream(usr[cod].getOutputStream());
              out.writeUTF(ms);
        } catch (IOException | NullPointerException ex) {System.out.println("PM n-a fost trimis"); }
        if(ms.startsWith("/")){          // ------------------------- COMENZI --------------------
           if(ms.equals("/users")){afisare(); flag = -1;}
           else if(ms.equals("/stop")){ }
           else {System.out.println("Comanda nu exista.");flag = -1;}
        }
        while(flag != -1){      // ---------- flag : -1 = end , 0 = server -> all clients
            if(flag == 0 && cod == -1)
                ms = sc.nextLine();
          if(ms.startsWith("/")){          // ------------------------- COMENZI --------------------
             if(ms.equals("/users")){afisare(); flag = -1;}
             else if(ms.equals("/stop")){ }
             else {System.out.println("Comanda nu exista.");flag = -1;}
          }           
            if(flag >= 0) 
                for(int i = flag;i<=mx;i++)     
                   try {
                      out = new DataOutputStream(usr[i].getOutputStream());
                      out.writeUTF(ms);
                   } catch (IOException | NullPointerException ex) { }
            flag = 0;
            if(cod == -2){cod = -1;flag = -1;} // ----- DESTROY THREAD MSG ALL -------    
        }   
    }
   public static void main(String [] args) throws NullPointerException{
      int port = 8080;
      try {
        serverSocket = new ServerSocket(port);
        System.out.println("Server deschis... Port:" + serverSocket.getLocalPort());
        Thread ct;
        Thread a = new Server();
        a.start();
        
        while(true){ // ---------------- CONECTARE CLIENT ---------------------
            usr[99] = serverSocket.accept(); usr[next] = usr[99];
            on++; 
            if(mx < on) mx = on;
            System.out.println("Tocmai s-a conectat:  " + usr[next].getRemoteSocketAddress()+ " pozitie "+next);
            System.out.println("Useri online: "+on);
            k = next;            
            for(int i = 0;i<=mx;i++) 
                try{
                    if( usr[i] == null || !usr[i].isConnected()) {
                        next = i;
                        break;
                    }
                }catch(NullPointerException abe){ next = i; break;}
             
            
            // ----------------------------------- CITIRE MESAJ DE LA CLIENT ----------------------------------
      ct = new Thread(){
        @Override
        public void run(){
            int id = k;
            String msg;
              try{
               DataInputStream in = new DataInputStream(usr[id].getInputStream());
               while(true){
                  msg = in.readUTF();
                  System.out.println("CLIENT("+usr[id].getRemoteSocketAddress()+"): "+msg);
                     if(msg.startsWith("/")){
                         if(msg.startsWith("/all")){  // -------------------- CLIENT VREA MSG PT TOTI -------------
                            mesaj = msg.substring(5, msg.length());
                            mesaj = "~GLOBAL MSG FROM USER("+id+"): "+ mesaj;
                            codMsg = -2;
                         }
                         else if(msg.startsWith("/pm")){    // ----------- PM INTRE 2 CLIENTI --------------
                           String m2[] = new String[3]; m2 = msg.split(" ",3);
                           mesaj = "~PM FROM USER("+id+"): "+m2[2];
                           codMsg = Integer.parseInt(m2[1]); // ---- id 
                        }
                         Thread specMsg = new Server();
                         specMsg.start();
                     }
                }
             } catch (IOException e) { // ------------------- USER DECONECTAT --------------------
                 System.out.println("Utilizator deconectat.."+usr[id].getRemoteSocketAddress() +" pozitie "+ id);
                 on--;
                 next = id;
                 usr[next] = new Socket();
                 System.out.println("Useri online: "+on);
              }
           } 
       };
       ct.start();
      }
   } catch (IOException e){ System.out.println("Server deja online");} 
  }
}
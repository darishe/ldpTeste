
package teste;


import java.io.*;
import java.net.*;
import java.util.Scanner;



public class Player {
  public String nome;
  public int playerID;
  public int totalPlayers;
  
    Scanner scan = new Scanner(System.in); 
    
    //Server setups
    final static int ServerPort = 1234;
    InetAddress ip = InetAddress.getByName("localhost");
    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;
    
  
    
    
public Player() throws IOException{
    
      // MENU PRINCIPAL - escolhe nome ou nao, e entra no servidor ...
      System.out.println(" escreve o teu nome para entrar  ou simples escreve [entrar] para começar com,o anónimo !" );
      String resposta = scan.next();
      
            if(resposta.equals("entrar")){
              nome = "Utilizador#"+(int)(Math.random() * 100 + 1);
            }else{
              nome = resposta;
            }
     
     //Connexão ao servidor automatica
     socket = new Socket(ip, ServerPort);
     dis = new DataInputStream(socket.getInputStream());
     dos = new DataOutputStream(socket.getOutputStream());
     
      
     dos.writeUTF(nome);
     playerID = dis.readInt();
     System.out.println(nome + " O meu ID é "+  playerID);
     
     
     chat();
     

      
      
      
  }



public void chat(){
    
         
      Thread sendMessage = new Thread(new Runnable() {
        @Override
        public void run() {
             Scanner scan = new Scanner(System.in);

            while(true) {
                 String msg = scan.next();
                try {
                    dos.writeUTF(msg);
                } 
                catch (IOException e) {
                }
            }
        }
 });
      
      Thread readMessage = new Thread(new Runnable(){
        @Override
        public void run() {
           while(true) {
                try {
                   String msg = dis.readUTF();
                   System.out.println(msg);
                   
                   
                   
                   if(msg.equals("startGame")){
                       System.out.println("Chegou aqqui");
                        totalPlayers = dis.readInt();
                        
                    }
               
                
                
                } 
               catch (IOException e) {
               }
           }
        }
 }); 
    
    
    
 sendMessage.start();
 readMessage.start();

    
}
    
    
    
    
}

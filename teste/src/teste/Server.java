
package teste;


import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static ArrayList<PlayerHandler> ar = new ArrayList<>();
    public static int numPlayers = 0;
    public static int startsVotacao = 0;
    public static Game jogo ;
    
    
     public static void main(String[] args) throws IOException {
         
         
                System.out.println("-- SERVER ON --");
                ServerSocket ss = new ServerSocket(1234);
                
                
       
                
                
                while(true){
                    
                    
                    // Pode ser temporario, basicamente, um stop á procura de clientes.
                    if(numPlayers == 6 || startGame()){ break; }
                    
                    
                   Socket s = ss.accept(); // espera por clientes 
        
        
                   // System.out.println("Novo Player recebido : " + s);

                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        
        
                        PlayerHandler mtch = new PlayerHandler(s, numPlayers, dis, dos);
                        
                        Thread t = new Thread(mtch);

                        System.out.println("Adiciona Jogador "+ numPlayers + " ao server.");
                        ar.add(mtch);
                        t.start();

                    numPlayers++;
                    
                    
                  
                }
                
                System.out.println("saiu do while de receber clientes");
                

               
                
 
     
     }
     
     
     //Votação para começar o  - Tem de ser mais de um jogador a votar, e tem de ser mais de metade dos jogadores a dizer ready 
      public static boolean startGame(){
         if(startsVotacao > 1 && startsVotacao > (numPlayers / 2) )
            return true;
                  
        return false;
      
      }
    

    
}

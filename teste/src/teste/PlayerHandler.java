
package teste;


import java.io.*;
import java.net.*;


public class PlayerHandler  implements Runnable {
    
        final DataInputStream dis;
        final DataOutputStream dos;
        Socket socket;
        
        int playerID;
        String nome;
        
           public PlayerHandler(Socket s, int id, DataInputStream dis, DataOutputStream dos) {
           
            this.socket = s;
            this.dis = dis;
            this.dos = dos;
            this.playerID = id;
            
           } 
           
           
           
        @Override
        public void run() {
            String recebido; 
           
             try {
                    nome = dis.readUTF();                     
                    
                    dos.writeInt(playerID);

                } 
                catch (IOException e) {System.out.println("Setup PlayerHandler erro");}
             
             
             

        while(true){
        
            try {
                recebido = dis.readUTF();
                System.out.println(nome + " - "+recebido);
          
                    if(recebido.endsWith("logout")){
                        this.socket.close();
                        break; // while
                    }
                    if(recebido.endsWith("ready")){
                        Server.startsVotacao++; 
                        if(Server.startGame()){
                            startGame();
                        }
                        
                    }
                    
            
          //  if(recebido.startsWith("chat")){
                    //Manda mensagem para todos. 
                    for(PlayerHandler mc: Server.ar){
                        mc.dos.writeUTF(nome + " : " + recebido);
                    }
           // }
            

            } catch (IOException e) {
                System.out.println("Recebido no while... ");
             }
        }

 }
        
        // Um player individual faz "ready", e se de alguma maneira este consegui true, manda true para todos através deste método    
        public void startGame() throws IOException{
             for(PlayerHandler mc: Server.ar){
                       System.out.println(Server.numPlayers + " estes é o numero que tento enviar.");
                        mc.dos.writeUTF("startGame");
                         mc.dos.writeInt(Server.numPlayers);
                         
                         //mc.dos.writeObject();

                         
                         

             }
            
        }
    
}

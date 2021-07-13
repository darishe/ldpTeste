
package teste;

import java.util.ArrayList;
import java.util.Collections;


public class Baralho {
    
        public ArrayList<Carta> cartas;
    
    public Baralho(){
        cartas = new ArrayList<Carta>();
      
        
        for(int n=0; n<=3; n++){
           for(int r=0; r<=12; r++){
               cartas.add(new Carta(n,r));
           } 
        }

        baralharCartas();
   }
    
     public void baralharCartas(){
        Collections.shuffle(cartas);
        
    
    }
     
     public void removerCarta(Carta carta){
         cartas.remove(carta);
     }
    
}

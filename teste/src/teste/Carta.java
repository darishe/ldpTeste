
package teste;


public class Carta {
    private int rank, naipe;
    
    private static String[] naipes = {"Copas","Ouros","Espadas","Paus"};
    private static String[] ranks = {"Ás","2","3","4","5","6","7","8","9","10","Valete","Rainha","Rei"};
    
    public Carta(int naipe, int rank){
        this.rank = rank;
        this.naipe = naipe;
    }
    
    public String toString(){
        return ranks[rank] + " de " + naipes[naipe];
    }
    
       public static String rankAsString(int rank) {
        return ranks[rank];
    }

    public int getRank(){
        return rank;
    }
    public int getNaipe(){
        return naipe;
    }
    
    
}

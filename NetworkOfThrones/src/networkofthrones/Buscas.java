/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkofthrones;
import networkofthrones.Grafo;
import networkofthrones.Pessoas;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Neori
 */
public class Buscas {
    
    public void inicializa(Grafo G, Pessoas s){
        for(Pessoas u : G.getVertices()){
            u.setCorBranco();
            u.setDistancia(0);
            u.setPredecessor(null);
            for(Pessoas x: u.getPessoas()){
                x.setCorBranco();
                x.setDistancia(0);
                x.setPredecessor(null);
            }
        }
        s.setCorCinza();
        s.setDistancia(0);
        s.setPredecessor(null);
    }
    
    public void buscaLargura(Grafo G, Pessoas s){
        Pessoas u = null;
        Queue<Pessoas> Q = new LinkedList();
        
        inicializa(G, s);
        Q.add(s);
        while(Q.size() != 0){
            u = Q.remove();
            for (Pessoas v : u.getPessoas()){
                if (v.getCor().equals("branco")){
                    v.setCorCinza();
                    v.setDistancia(u.getDistancia()+1);
                    v.setPredecessor(u);
                    Q.add(v);
                }
            }
            u.setCorPreto();
        }
    }
    
    public void printaDistancia(){
        
    }
}

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
    private Auxiliares[] dicionario;
    
    public Buscas(){
    }
    
    public void inicializa(Grafo G, Pessoas s){
        for(Pessoas u : G.getVertices()){
            dicionario[u.getIndex()] = new Auxiliares(u.getIndex());//adiciona um objeto auxiliar para cada 
        }
        
        dicionario[s.getIndex()].setCorCinza();//faz o dicionario na posicao s mudar a cor
        dicionario[s.getIndex()].setDistancia(0);//distancia
        dicionario[s.getIndex()].setPredecessor(-1);//predecessor
    }
    
    public void buscaLargura(Grafo G, Pessoas s){
        Auxiliares aux = null;
        Pessoas u = null;
        Queue<Auxiliares> Q = new LinkedList();
        
        inicializa(G, s);
        Q.add(dicionario[s.getIndex()]); //adiciona o auxiliar na posicao do indice de s
        while(Q.size() != 0){
            aux = Q.remove();
            u = achaPessoa(aux.getIndex(), G);
            for (Pessoas v : u.getPessoas()){
                if (dicionario[v.getIndex()].getCor().equals("branco")){
                    dicionario[v.getIndex()].setCorCinza();
                    dicionario[v.getIndex()].setDistancia(dicionario[u.getIndex()].getDistancia()+1);
                    //vai no dicionario na posicao onde v esta, e muda o predecessor, para o index
                    //do dicionario na posicao de u
                    dicionario[v.getIndex()].setPredecessor(u.getIndex());
                    Q.add(dicionario[v.getIndex()]);
                }
            }
            dicionario[u.getIndex()].setCorPreto();
        }
    }
    
    public void printaDistancia(){
        
    }
    
    private Pessoas achaPessoa(int index, Grafo not){//acha os indices pelo nome
        Pessoas aux = null;
        
        for (Pessoas x : not.getVertices()){
            if(x.getIndex() == index){
                aux = x;
            }
        }
        
        return aux;
    }
}

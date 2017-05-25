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
    private List<Auxiliares> dicionario;
    
    public Buscas(){
        dicionario = new ArrayList<>();
    }
    
    public void inicializa(Grafo G, Pessoas s){
        for(Pessoas u : G.getVertices()){
            dicionario.add(new Auxiliares(u.getIndex()));//adiciona um objeto auxiliar para cada 
        }
        
        dicionario.get(s.getIndex()).setCorCinza();//faz o dicionario na posicao s mudar a cor
        dicionario.get(s.getIndex()).setDistancia(0);//distancia
        dicionario.get(s.getIndex()).setPredecessor(-1);//predecessor
    }
    
    public void buscaLargura(Grafo G, Pessoas s){
        Auxiliares aux = null;
        Pessoas u = null;
        Queue<Auxiliares> Q = new LinkedList();
        
        inicializa(G, s);
        Q.add(dicionario.get(s.getIndex())); //adiciona o auxiliar na posicao do indice de s
        while(Q.size() != 0){
            aux = Q.remove();
            u = achaPessoa(aux.getIndex(), G);
            for (Pessoas v : u.getPessoas()){
                if (dicionario.get(v.getIndex()).getCor().equals("branco")){
                    dicionario.get(v.getIndex()).setCorCinza();
                    dicionario.get(v.getIndex()).setDistancia(dicionario.get(u.getIndex()).getDistancia()+1);
                    //vai no dicionario na posicao onde v esta, e muda o predecessor, para o index
                    //do dicionario na posicao de u
                    dicionario.get(v.getIndex()).setPredecessor(dicionario.get(u.getIndex()).getIndex());
                    Q.add(dicionario.get(v.getIndex()));
                }
            }
            dicionario.get(u.getIndex()).setCorPreto();
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

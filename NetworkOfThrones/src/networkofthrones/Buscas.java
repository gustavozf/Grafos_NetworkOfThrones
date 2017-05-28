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
import java.util.Scanner;

/**
 *
 * @author Neori
 */
public class Buscas {
    private Auxiliares[] dicionario;
    
    public Buscas(Grafo not){
        dicionario = new Auxiliares[not.getVertices().size()];
        for(Pessoas u : not.getVertices()){
            dicionario[u.getIndex()] = new Auxiliares(u.getIndex());//adiciona um objeto auxiliar para cada 
        }
    }
    
    private void inicializa(Grafo G, Pessoas s){
        for(Pessoas u : G.getVertices()){
            dicionario[u.getIndex()].setCorBranco();
            dicionario[u.getIndex()].setDistancia(-1);
            dicionario[u.getIndex()].setPredecessor(-1);
        }
        
        dicionario[s.getIndex()].setCorCinza();//faz o dicionario na posicao s mudar a cor
        dicionario[s.getIndex()].setDistancia(0);//distancia
        dicionario[s.getIndex()].setPredecessor(-1);//predecessor
    }
    
    private void buscaLargura(Grafo G, Pessoas s){
        Auxiliares aux = null;
        Pessoas u = null;
        Queue<Auxiliares> Q = new LinkedList();
        int i =0;
        int j = 0;
        
        inicializa(G, s);
        Q.add(dicionario[s.getIndex()]); //adiciona o auxiliar na posicao do indice de s
        while(Q.size() != 0){
            aux = Q.remove();
            u = achaPessoaIndex(aux.getIndex(), G);
            i = u.getIndex();
            for (Pessoas v : u.getPessoas()){
                j = v.getIndex();
                if (dicionario[j].getCor().equals("branco")){
                    dicionario[j].setCorCinza();
                    dicionario[j].setDistancia(dicionario[i].getDistancia()+1);
                    //vai no dicionario na posicao onde v esta, e muda o predecessor, para o index
                    //do dicionario na posicao de u
                    dicionario[j].setPredecessor(i);
                    Q.add(dicionario[v.getIndex()]);
                }
            }
            dicionario[u.getIndex()].setCorPreto();
        }
    }
    
    private void printaDistancia(Pessoas y, Pessoas s){
        System.out.println("A distancia entre: " + s.getNome() +" e " + y.getNome()+ " eh "+ dicionario[y.getIndex()].getDistancia());
    }
    
    public void consultaDistancia(Grafo not){
        String nomes, nomey;
        Scanner scan = new Scanner(System.in);
       
        try{
            System.out.printf("Digite o nome da primeira pessoa: ");   
            nomes = scan.nextLine();
            System.out.println();
            System.out.printf("Digite o nome da segunda pessoa: ");
            nomey = scan.nextLine();
            buscaLargura(not, achaPessoaNome(not, nomes));
            printaDistancia(achaPessoaNome(not, nomey), achaPessoaNome(not, nomes));
        } catch (java.lang.NullPointerException e){
            System.out.println("Erro: Nome incorreto!");
        }
    }
    
    public void encontraPontes(Grafo not){
        Tempo tempo;
        
        for(Pessoas u: not.getVertices()){
            dicionario[u.getIndex()].setCorBranco();
            dicionario[u.getIndex()].setDistancia(-1);
        }
        tempo = new Tempo();
        for(Pessoas u: not.getVertices()){
            if (dicionario[u.getIndex()].getCor().equals("branco")){
                briges(tempo, u);
            }
        }
        
    }
    
    private void briges(Tempo tempo, Pessoas u){
        tempo.incrementa();
        dicionario[u.getIndex()].setCorCinza();
        dicionario[u.getIndex()].setLow(tempo.getTempo());
        dicionario[u.getIndex()].setDescoberta(tempo.getTempo());
        for(Pessoas v : u.getPessoas()){
            dicionario[v.getIndex()].setPredecessor(u.getIndex());
            briges(tempo, v);
            dicionario[u.getIndex()].setLow(Math.min(dicionario[u.getIndex()].getLow(), dicionario[v.getIndex()].getLow()));
            if((dicionario[v.getIndex()].getLow()) > (dicionario[u.getIndex()].getDescoberta())){
                System.out.println(u.getNome() + " / " + v.getNome() + " eh ponte! (Indices #" + u.getIndex() + " e #" + v.getIndex() + ")");
            } else {
                if ((v.getIndex() != dicionario[u.getIndex()].getPredecessor()) && 
                        (dicionario[v.getIndex()].getDescoberta() < dicionario[u.getIndex()].getDescoberta())){
                    dicionario[u.getIndex()].setLow(Math.min(dicionario[u.getIndex()].getLow(), dicionario[v.getIndex()].getDescoberta()));
                }
            }
        }
        dicionario[u.getIndex()].setCorPreto();
        tempo.incrementa();
        dicionario[u.getIndex()].setTermino(tempo.getTempo());
    }
    
    private Pessoas achaPessoaIndex(int index, Grafo not){//acha o objeto pessoa pelo indice 
        Pessoas aux = null;
        
        for (Pessoas x : not.getVertices()){
            if(x.getIndex() == index){
                aux = x;
            }
        }
        return aux;
    }
    
    private Pessoas achaPessoaNome (Grafo not, String nome){
        Pessoas aux = null;
        
        for (Pessoas x : not.getVertices()){
            if(x.getNome().equals(nome)){
                aux = x;
            }
        }
        
        return aux;
    }
}

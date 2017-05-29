/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkofthrones;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Neori
 */
public class Buscas {
    private Auxiliares[] dicionario;
    private Tempo tempo;
    
    public Buscas(Grafo not){
        tempo = new Tempo();
        dicionario = new Auxiliares[not.getVertices().size()];
        for(Pessoas u : not.getVertices()){//not = Network of Thrones
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
    
    public void buscaProfundidade(Grafo not){
        for(Pessoas u: not.getVertices()){
            dicionario[u.getIndex()].setCorBranco();
            dicionario[u.getIndex()].setPredecessor(-1);//NULL
        }
        this.tempo.zeraTempo();//zera o tempo
        for(Pessoas u: not.getVertices()){
            if (dicionario[u.getIndex()].getCor().equals("branco")){
                DFS(u, not);
            }
        }
        printaAuxiliares(not);
    }
    
    public void DFS(Pessoas u, Grafo not){
        Auxiliares u1, v1;
        this.tempo.incrementa();
        u1 = dicionario[u.getIndex()];
        u1.setCorCinza();
        u1.setDescoberta(this.tempo.getTempo());
        for(Pessoas v : u.getPessoas()){
            v1 = dicionario[v.getIndex()];
            if (v1.getCor().equals("branco")){
                v1.setPredecessor(u.getIndex());
                DFS(not.getVertices().get(v1.getIndex()), not);
            }
        }
        u1.setCorPreto();
        this.tempo.incrementa();
        u1.setTermino(this.tempo.getTempo());
    }
    
    public void encontraPontos(Grafo not){
        Pessoas pesAux = new Pessoas("Aux", -1);//utilizar como vetor auxiliar para adicionar
        //os pontos de ariticulacao (ja que este mesmo insere somente se nao estiver ja adicionado
        
        for(Pessoas u: not.getVertices()){
            dicionario[u.getIndex()].setCorBranco();
            dicionario[u.getIndex()].setPredecessor(-1);//NULL
        }
        this.tempo.zeraTempo();//zera o tempo
        for(Pessoas u: not.getVertices()){
            if (dicionario[u.getIndex()].getCor().equals("branco")){
                pontoArticulacao(u, not, pesAux);
            }
        }
        //printaAuxiliares(not);
        for(Pessoas x : pesAux.getPessoas()){
            System.out.println(x.getNome() + " eh ponto de articulacao! (Indice #" + x.getIndex() + ")");
        }
    }
    
    private void pontoArticulacao(Pessoas u, Grafo not, Pessoas pesAux){
       Auxiliares u1, v1;
       u1 = dicionario[u.getIndex()];
       
       this.tempo.incrementa();
       u1.setCorCinza();
       u1.setLow(this.tempo.getTempo());
       u1.setDescoberta(this.tempo.getTempo());
       for (Pessoas v : u.getPessoas()){
           v1 = dicionario[v.getIndex()];
           if(v1.getCor().equals("branco")){
               v1.setPredecessor(u.getIndex());
               pontoArticulacao(not.getVertices().get(v1.getIndex()), not, pesAux);
               if(u1.getPredecessor() == -1){
                   if(u.getPessoas().size() > 1){
                       //System.out.println(u.getNome() + " eh ponto de articulacao! (Indice #" + u.getIndex() + ")");
                       pesAux.addPessoa(u);
                   }
                } else {
                   u1.setLow(min(u1.getLow(), v1.getLow()));
                   if(v1.getLow() >= u1.getDescoberta()){
                       pesAux.addPessoa(u);
                       //System.out.println(u.getNome() + " eh ponto de articulacao! (Indice #" + u.getIndex() + ")");
                   }
               }
           } else {
               if((v.getIndex() != u1.getPredecessor()) && (v1.getDescoberta() < u1.getDescoberta())){
                   u1.setLow(min(u1.getLow(), v1.getDescoberta()));
               }
           }
       }
       u1.setCorPreto();
       this.tempo.incrementa();
       u1.setTermino(this.tempo.getTempo());
    }
    
    public void encontraPontes(Grafo not){
        for(Pessoas u: not.getVertices()){
            dicionario[u.getIndex()].setCorBranco();
            dicionario[u.getIndex()].setPredecessor(-1);//NULL
        }
        this.tempo.zeraTempo();//zera o tempo
        for(Pessoas u: not.getVertices()){
            if (dicionario[u.getIndex()].getCor().equals("branco")){
                bridges(u, not);
            }
        }
        
        //printaAuxiliares(not);
    }
    
    private void bridges(Pessoas u, Grafo not){
        this.tempo.incrementa();
        Auxiliares u1, v1;
        u1 = dicionario[u.getIndex()];
        u1.setCorCinza();
        u1.setLow(this.tempo.getTempo());
        u1.setDescoberta(this.tempo.getTempo());
        for(Pessoas v : u.getPessoas()){
            v1 = dicionario[v.getIndex()];
            if (v1.getCor().equals("branco")){
                v1.setPredecessor(u.getIndex());
                bridges(not.getVertices().get(v1.getIndex()), not);
                u1.setLow(min(u1.getLow(), v1.getLow()));
                if((v1.getLow()) > (u1.getDescoberta())){
                    System.out.println(u.getNome() + " / " + v.getNome() + " eh ponte! (Indices #" + u.getIndex() + " e #" + v.getIndex() + ")");
                } 
            } else {
                if ((v.getIndex() != u1.getPredecessor()) && (v1.getDescoberta() < u1.getDescoberta())){
                        u1.setLow(min(u1.getLow(), v1.getDescoberta()));
                }
            }
        }
        u1.setCorPreto();
        this.tempo.incrementa();
        u1.setTermino(this.tempo.getTempo());
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
    
    private Pessoas achaPessoaNome (Grafo not, String nome){//acha o objeto pelo nome
        Pessoas aux = null;
        
        for (Pessoas x : not.getVertices()){
            if(x.getNome().equals(nome)){
                aux = x;
            }
        }
        
        return aux;
    }
    
    private void printaAuxiliares(Grafo not){
        Auxiliares u1;
        for(Pessoas x : not.getVertices()){
            u1 = dicionario[x.getIndex()];
            System.out.println("#" + u1.getIndex() + " " + x.getNome() 
                    + " u.d=" + u1.getDescoberta()+ " u.f=" + u1.getTermino());
        }
    }
    
    private int min(int x, int y){
        if (x < y){
            return x;
        } else {
            return y;
        }
    }
}

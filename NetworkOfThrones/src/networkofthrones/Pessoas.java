/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkofthrones;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gustavozf
 */
public class Pessoas {//Vertices
    private final String name;
    private  String cor;
    private int low;
    private int distancia;
    private Pessoas predecessor;
    private  List<Pessoas> ligacoes;//Lista de adjacencia
    
    public Pessoas(String nome){
        this.name = nome;
        this.cor = "branco";
        this.predecessor = null;
        this.distancia = -1;
        this.low = 0;
        this.ligacoes = new ArrayList<>();
    }
    
    public void addPessoa(Pessoas pessoay){
        if(!(this.ligacoes.contains(pessoay))){//caso a pessoa nao esteja ja inserida
            this.ligacoes.add(pessoay);//adiciona a pessoa a lista
        }
    }
    
    public void setCorCinza(){
        this.cor = "cinza";
    }
    
    public void setCorPreto(){
        this.cor = "preto";
    }
    
    public void setCorBranco(){
        this.cor = "branco";
    }
    
    public void setLow(int low){
        this.low = low;
    }
    
    public void setDistancia(int dist){
        this.distancia = dist;
    }
    
    public void setPredecessor(Pessoas pessoax){
        this.predecessor = pessoax;
    }
    
    public String getCor(){
        return this.cor;
    }
    
    public String getNome(){
        return this.name;
    }
    
    public int getLow(){
        return this.low;
    }
    
    public int getDistancia(){
        return this.distancia;
    }
    
    public Pessoas getPredecessor(){
        return this.predecessor;
    }
    
    public List<Pessoas> getPessoas(){
        return this.ligacoes;
    }
}
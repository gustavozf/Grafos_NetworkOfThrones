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
    private final int index;
    private  List<Pessoas> ligacoes;//Lista de adjacencia
    
    public Pessoas(String nome, int index){
        this.name = nome;
        this.index = index;
        this.ligacoes = new ArrayList<>();
    }
    
    public void addPessoa(Pessoas pessoay){
        if(!Inserido(pessoay.getNome())){//caso a pessoa nao esteja ja inserida
            this.ligacoes.add(pessoay);//adiciona a pessoa a lista
            //System.out.println("Entrou!");
        }
    }
    
    
    public String getNome(){
        return this.name;
    }
    
    public List<Pessoas> getPessoas(){
        return this.ligacoes;
    }
    
    public int getIndex(){
        return this.index;
    }
    
    public boolean Inserido(String nome){
        boolean bool = false;
        
        for(Pessoas x : this.ligacoes){
            if(x.getNome().equals(nome)){
                bool = true;
            }
        }
        
        return bool;
    }
}
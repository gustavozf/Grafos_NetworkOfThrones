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
    private  List<Pessoas> ligacoes;//Lista de adjacencia
    
    public Pessoas(String nome){
        this.name = nome;
        this.cor = "branco";
        this.ligacoes = new ArrayList<>();
    }
    
    public void addPessoa(Pessoas siMesma, Pessoas pessoay){
        if(!(this.ligacoes.contains(pessoay))){//caso a pessoa nao esteja ja inserida
            this.ligacoes.add(pessoay);//adiciona a pessoa a lista
            pessoay.addPessoa(pessoay, siMesma);//a pessoa adiciona a adicionada a sua lista
            //(nao orientado), logo ambas devem estar nas listas de adjacencia uma da outra
        } else {
            System.out.println("Aresta " + siMesma.getNome() +
                            "<->"+pessoay.getNome()+" já existente!" );
        }
    }
    
    public void setCor(String cor){
        this.cor = cor;
    }
    
    public String getCor(){
        return this.cor;
    }
    
    public String getNome(){
        return this.name;
    }
    
    
}
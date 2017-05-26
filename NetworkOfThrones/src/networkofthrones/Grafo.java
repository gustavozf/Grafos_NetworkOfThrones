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
public class Grafo {
    private List<Pessoas> listaPessoas;
    
    public Grafo(){
        this.listaPessoas = new ArrayList<>();
    }
    
    //public void addVertices(List<Pessoas> pessoax){//adiciona um vertice ao grafo
        //this.listaPessoas = pessoax;
    
    public void addVertices(Pessoas pessoax){
            this.listaPessoas.add(pessoax);
    }
    
    public void addAresta(Pessoas pessoax, Pessoas pessoay){
        for(Pessoas aresta : this.listaPessoas){
            if(aresta.getNome().equals(pessoax.getNome())){
                aresta.addPessoa(pessoay);//Já que o grafo é nao orientado, uma pessoa
                                          // deve estar adicionada na lista da outra
            }
            if(aresta.getNome().equals(pessoay.getNome())){
                aresta.addPessoa(pessoax);
            }
        }
    }
    
    public List<Pessoas> getVertices(){
        return this.listaPessoas;
    }
    
    public boolean Inserido(String nome){//Verifica se um nome ja se encontra no grafo
        boolean bool = false;
        
        for(Pessoas x : this.listaPessoas){
            if(x.getNome().equals(nome)){
                bool = true;
            }
        }
        
        return bool;
    }
    
    public void printaGrafo(){
        for(Pessoas x:this.listaPessoas){
            System.out.print(x.getNome() + " #" + x.getIndex() + " lista = ");
            for(Pessoas y : x.getPessoas()){
                System.out.printf(" %s ", y.getNome());
            }
            System.out.println();
        }
    }
}

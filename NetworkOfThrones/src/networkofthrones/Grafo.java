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
    
    public void addVertice(Pessoas pessoax){//adiciona um vertice ao grafo
        if(!(this.listaPessoas.contains(pessoax))){
            this.listaPessoas.add(pessoax);
        }
    }
    
    public void addAresta(Pessoas pessoax, Pessoas pessoay){
        for(Pessoas aresta : this.listaPessoas){
            if(aresta.getNome().equals(pessoax.getNome())){
                pessoax.addPessoa(pessoax, pessoay);
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkofthrones;

/**
 *
 * @author gustavozf
 */
public class Auxiliares {
    private  String cor;
    private int low;
    private int distancia;
    private int predecessor;
    private int index;
    private int descoberta;
    private int termino;
    
    public Auxiliares(int index){
        this.cor = "branco";
        this.predecessor = -1;//null
        this.distancia = -1;//null
        this.low = 0;
        this.index = index;
        this.descoberta = 0;
        this.termino = 0;
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
    
    public void setPredecessor(int pessoaIndex){
        this.predecessor = pessoaIndex;
    }
    
    public void setDescoberta(int desc){
        this.descoberta = desc;
    }
    
    public void setTermino(int term){
        this.termino = term;
    }
    
    public String getCor(){
        return this.cor;
    }
    
    public int getLow(){
        return this.low;
    }
    
    public int getDistancia(){
        return this.distancia;
    }
    
    public int getPredecessor(){
        return this.predecessor;
    }
    
    public int getIndex(){
        return this.index;
    }
    
    public int getDescoberta(){
        return this.descoberta;
    }
    
    public int getTermino(){
        return this.termino;
    }
}

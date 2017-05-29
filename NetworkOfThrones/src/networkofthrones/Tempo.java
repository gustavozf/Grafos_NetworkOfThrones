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
public class Tempo {
    private int tempo;
    
    public Tempo(){
        this.tempo = 0;
    }
    
    public void incrementa(){
        this.tempo++;
    }
    
    public int getTempo(){
        return this.tempo;
    }
    
    public void zeraTempo(){
        this.tempo = 0;
    }
}

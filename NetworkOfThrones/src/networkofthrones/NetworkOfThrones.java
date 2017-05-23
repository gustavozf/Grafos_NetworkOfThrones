/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkofthrones;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import networkofthrones.Grafo;
import networkofthrones.Pessoas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gustavozf
 */
public class NetworkOfThrones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grafo not = new Grafo();
        
        inicializacao(not);
        //not.getPessoas();
        
    }
    
    public static void inicializacao(Grafo not){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String fileArestas = "/home/gustavozf/NetBeansProjects/NetworkOfThrones/src/CSVs/stormofswordsARESTAS.csv";
        String fileVertices = "/home/gustavozf/NetBeansProjects/NetworkOfThrones/src/CSVs/stormofswordsVERTICES.csv";
        
        try {//inserir vertices

            br = new BufferedReader(new FileReader(fileVertices));
            while ((line = br.readLine()) != null) {
                not.addVertices(new Pessoas(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        line = "";
        br = null;
        
        try {//inserir arestas

            br = new BufferedReader(new FileReader(fileArestas));
            while ((line = br.readLine()) != null) {

                // virgula eh o "separador"
                String[] arestas = line.split(cvsSplitBy);
                not.addAresta(new Pessoas(arestas[0]), new Pessoas(arestas[1]));
                

            }

        } catch(IOException e) { //caso de errado
            e.printStackTrace();
        }  
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkofthrones;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    private static final String fileArestas = "stormofswordsARESTAS.csv";
    private static final String fileVertices = "stormofswordsVERTICES.csv";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grafo not = new Grafo();
        
        inicializacao(not);
        
    }
    
    public static void inicializacao(Grafo not){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        //List<Pessoas> Aux = new ArrayList(71);
        //int i = 0;
        
        try {//inserir vertices

            br = new BufferedReader(new FileReader(fileVertices));
            while ((line = br.readLine()) != null) {
                not.addVertices(new Pessoas(line));
            }

        } catch(FileNotFoundException e) { //caso de errado
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

        } catch(FileNotFoundException e) { //caso de errado
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}

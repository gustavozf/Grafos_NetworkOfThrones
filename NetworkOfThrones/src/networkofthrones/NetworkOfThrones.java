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
        not.printaGrafo();
        
    }
    
    @SuppressWarnings({"CallToPrintStackTrace", "UnusedAssignment"})
    public static void inicializacao(Grafo not){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String file = "/home/gustavozf/NetBeansProjects/NetworkOfThrones/src/CSVs/teste.csv";
        int i = 0;
        int aux1 = 0, aux2 = 0;
        
        try {//inserir vertices

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) { //le linha por linha do arquivo
                String[] arestas = line.split(cvsSplitBy);
                if(!not.Inserido(arestas[0])){//se ja nao estiver inserido
                    not.addVertices(new Pessoas(arestas[0], i)); //adiciona a pessoa com o indice "i"
                    i++;
                }
                if(!not.Inserido(arestas[1])){
                    not.addVertices(new Pessoas(arestas[1], i)); //adiciona a pessoa com o indice "i"
                    i++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        line = "";
        br = null;
        
        try {//inserir arestas

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {//le linha por linha do arquivo

                // virgula eh o "separador"
                String[] arestas = line.split(cvsSplitBy);
                aux1 = achaIndex(arestas[0], not); //salva o index do nome encontrado
                aux2 = achaIndex(arestas[1], not);//    ''                        ''
                not.addAresta(new Pessoas(arestas[0], aux1), new Pessoas(arestas[1], aux2));
                

            }

        } catch(IOException e) { //caso de errado
            e.printStackTrace();
        }  
    }
    
    private static int achaIndex(String nomex, Grafo not){//acha os indices pelo nome
        int aux = 0;
        
        for (Pessoas x : not.getVertices()){
            if(x.getNome().equals(nomex)){
                aux = x.getIndex();
            }
        }
        
        return aux;
    }
}

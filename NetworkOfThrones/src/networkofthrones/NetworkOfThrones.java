/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkofthrones;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
        Buscas buscas;
        Scanner scan = new Scanner(System.in);
        int escolha = 1;
        
        inicializacao(not);
        buscas = new Buscas(not);
        
        System.out.println("Bem vindo ao grafo, onde 2/3 dos personagens provavelmente"
                + " ja estao mortos!\n");
        while(escolha != 0){
            System.out.println("1 - Printar o Grafo\n2 - Consultar Distancia\n3 - Consultar Pontes\n"
                    + "4 - Consultar Pontos de Articulacao\n5 - Busca em Profundidade\n0 - Sair");
            System.out.print(">");
            escolha = scan.nextInt();
            System.out.println();
            switch(escolha){
                case 1: 
                        not.printaGrafo();
                        System.out.println();
                        break;
                
                case 2: 
                        buscas.consultaDistancia(not);
                        System.out.println();
                        break;
                
                case 3: 
                        buscas.encontraPontes(not);
                        System.out.println();
                        break;
                  
                case 4: 
                        buscas.encontraPontos(not);
                        System.out.println();
                        break;
                        
                case 5:
                        buscas.buscaProfundidade(not);
                        System.out.println();
                        break;
                default:
                        System.out.println("THE WHITE WALKERS ARE COMING");
                        break;
            }
            
        }
    }
    
    @SuppressWarnings({"CallToPrintStackTrace", "UnusedAssignment"})
    public static void inicializacao(Grafo not){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String file = "/home/gustavozf/NetBeansProjects/NetworkOfThrones/src/CSVs/teste1.csv";
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

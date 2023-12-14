package demo;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.google.common.graph.ElementOrder;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The {DataGraph} class represents a graph data structure that stores relationships between individuals.
 * It provides methods for reading data from a file, performing breadth-first search (BFS), and extracting information about individuals taking a specific class.
 */
public class DataGraph{

   /** The graph representing relationships between individuals and associated class information. */
   private MutableValueGraph<String, Integer> graph;
    

    /**
    * Reads data from a file and populates the graph with relationships and class information.
    *
    * @param fileName The name of the file containing relationship and class information.
    */
    public void fileReader(String fileName){

        graph = ValueGraphBuilder.undirected().incidentEdgeOrder(ElementOrder.stable()).build();
    
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

           
            while( scanner.hasNextLine()){
                String line = scanner.nextLine();

                String[] values = line.trim().split("\\s*,\\s*");
                
                if(!graph.nodes().contains(values[0])){
                    graph.addNode(values[0]);
                }
                if(!graph.nodes().contains(values[1])){
                    graph.addNode(values[1]);
                }
                
                 if(graph.edgeValueOrDefault(values[0], values[1], 0) != 0){
                    // System.out.println("Edge already exists");
                    int oldEdge = graph.edgeValueOrDefault(values[0], values[1], 0);
                    int newEdge = oldEdge * 1000 + Integer.parseInt(values[2]);
                    graph.putEdgeValue(values[0], values[1], newEdge);
                } else {
                   graph.putEdgeValue(values[0], values[1], Integer.parseInt(values[2]));
                }
            
                new GraphDisplay(graph);
                for (int i =0; i<values.length; i++){
               // System.out.println("Values :" + values[i]);
            }
            } 
            scanner.close();

            //System.out.println(graph.nodes());

        } catch (FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        }
    }


    /**
    * Performs breadth-first search (BFS) starting from a specified node in the graph.
    *
    * @param startNode The starting node for BFS.
    * @param graph The graph on which BFS is performed.
    */
    public void bfs(String startNode, MutableValueGraph<String, Integer> graph){
        ArrayDeque<String> visited = new ArrayDeque<>();
        HashSet<String> seen = new HashSet<>();
        seen.add(startNode); 
        visited.add(startNode);

        while(!visited.isEmpty()){
            String currentNode = visited.remove();
            System.out.println("current node: " + currentNode);

            for(String neighbor : graph.successors(currentNode)){
             //  System.out.println("neighbor: " + neighbor);
                if(!seen.contains(neighbor)){
                    seen.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    /**
    * Retrieves a list of individuals taking a specific class with the given student.
    *
    * @param startNode The student's name.
    * @param classNum The class number.
    * @return A list of individuals taking the specified class with the student.
    */
    public ArrayList<String> bfsClass(String startNode, int classNum){
        ArrayList<String> list = new ArrayList<>();
        
        ArrayDeque<String> visited = new ArrayDeque<>();
        HashSet<String> seen = new HashSet<>();
        seen.add(startNode); 
        visited.add(startNode);

        while(!visited.isEmpty()){

            String currentNode = visited.remove();
            System.out.println("current node: " + currentNode);
            System.out.println("neighbor: " + graph.successors(currentNode));

            for(String neighbor : graph.successors(currentNode)){
                //System.out.println("seen is" + seen);

                if(!seen.contains(neighbor)){

                    int encodedEdgeValue = graph.edgeValueOrDefault(currentNode, neighbor, 0);
                    String value = Integer.toString(encodedEdgeValue);
                    String[] results = value.split("(?<=\\G.{" + 3 + "})");
                    for( int i=0; i<results.length; i++){
                        if(classNum == Integer.parseInt(results[i])){
                            list.add(neighbor);
                        }
                    }
                }
            }
        }
        System.out.println("These are the people your student is taking that class with " + list);
        System.out.println(startNode + " is taking " + classNum + " with " + list);
        return list;
    }

    /**
    * The main method for testing the functionalities of the {@code DataGraph} class.
    *
    * @param args The command-line arguments (not used in this context).
    */
    public static void main(String[] args) {
       DataGraph myGraph = new DataGraph(); 
       myGraph.fileReader("Data/myDataSet.csv");
    
        //myGraph.bfs("Quinn", myGraph.graph);

    
        Scanner userInput = null;
        try {
            System.out.println("file found");
            userInput = new Scanner(new File("Data/input.txt"));

            
        } catch (FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        }
        userInput.nextLine();
        String name = userInput.nextLine();
        userInput.nextLine();
        String classnumber = userInput.nextLine();
        System.out.println("name: " + name + " and " + "class: " + classnumber);
        userInput.close();

        myGraph.bfsClass(name, Integer.valueOf(classnumber));
        
    }
}
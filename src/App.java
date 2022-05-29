import java.io.*;
import java.util.*;

import graph.AllCyclesInDirectedGraphJohnson;
import graph.Graph;
import graph.NonTouchingLoops;
import graph.Vertex;

public class App {
  public static void main(String[] args) throws Exception {
    if (args.length == 0) {
      System.err.println("No file path given");
      System.exit(1);
    } else {
      try {
        File inputFile = new File(args[0]);
        FileReader inputReader = new FileReader(inputFile);
        try (Scanner input = new Scanner(inputReader)) {
          int numberOfNodes = Integer.parseInt(input.nextLine().trim());
          String[] sourceAndDestination = input.nextLine().trim().split("\\s+");
          int sourceNode = Integer.parseInt(sourceAndDestination[0]);
          int destinationNode = Integer.parseInt(sourceAndDestination[1]);
          int numberOfEdges = Integer.parseInt(input.nextLine().trim());
          int i = 0;
          int src, dest;
          String weight;
          Graph<Integer> graph = new Graph<>(true);
          while (i < numberOfEdges) {
            String[] sourceDestWeightString = input.nextLine().trim().split("\\s+");
            src = Integer.parseInt(sourceDestWeightString[0]);
            dest = Integer.parseInt(sourceDestWeightString[1]);
            weight = sourceDestWeightString[2];
            graph.addEdge(src, dest, weight);
            i++;
          }
          AllCyclesInDirectedGraphJohnson johnson = new AllCyclesInDirectedGraphJohnson();
          List<List<Vertex<Integer>>> forwardPaths = graph.getAllForwardPaths(sourceNode, destinationNode);
          List<List<Vertex<Integer>>> allCycles = johnson.simpleCyles(graph);
          NonTouchingLoops loops = new NonTouchingLoops(forwardPaths, allCycles);
          loops.computeNonTouchingLoops();
          List<List<List<List<Vertex<Integer>>>>> nonTouchingLoops = loops.nonTouchingLoops;
          System.out.println("Number of Nodes = " + numberOfNodes);
          System.out.println("Source Node: " + sourceNode + ", Destination Node: " + destinationNode);
          System.out.println("Number of Edges: " + numberOfEdges);
          System.out.println("Graph: ");
          System.out.println(graph);
          System.out.println("Forward paths: ");
          System.out.println(forwardPaths);
          System.out.println("Loops: ");
          System.out.println(allCycles);
          System.out.println("Non Touching Loops: ");
          i = 2;
          for (List<List<List<Vertex<Integer>>>> aList : nonTouchingLoops) {
            if (aList.size() == 0)
              break;
            System.out.println("Set of " + i++ + " Non touching loops");
            System.out.println(aList);
          }
        }
      } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}

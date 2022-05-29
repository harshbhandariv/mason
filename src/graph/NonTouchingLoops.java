package graph;

import java.util.*;

public class NonTouchingLoops {
  public List<List<List<List<Vertex<Integer>>>>> nonTouchingLoops;
  public List<List<Vertex<Integer>>> forwardPaths;
  List<List<Vertex<Integer>>> allCycles;
  int noOfLoops;
  int noOfForwardPaths;

  public NonTouchingLoops(List<List<Vertex<Integer>>> forwardPaths, List<List<Vertex<Integer>>> allCycles) {
    this.allCycles = new ArrayList<>(allCycles);
    this.noOfLoops = this.allCycles.size();
    this.nonTouchingLoops = new ArrayList<>();
    for (int i = 0; i < noOfLoops - 1; i++) {
      this.nonTouchingLoops.add(new ArrayList<>());
    }
    this.forwardPaths = forwardPaths;
    this.noOfForwardPaths = forwardPaths.size();
  }

  private void helper(List<int[]> combinations, int data[], int start, int end, int index) {
    if (index == data.length) {
      int[] combination = data.clone();
      combinations.add(combination);
    } else if (start <= end) {
      data[index] = start;
      helper(combinations, data, start + 1, end, index + 1);
      helper(combinations, data, start + 1, end, index);
    }
  }

  public List<int[]> generate(int n, int r) {
    List<int[]> combinations = new ArrayList<>();
    helper(combinations, new int[r], 0, n - 1, 0);
    return combinations;
  }

  private void checkNonTouching(int[] combination, int i) {
    Set<Long> set = new HashSet<>();
    for (int loopIndex : combination) {
      for (Vertex<Integer> x : allCycles.get(loopIndex)) {
        if (set.contains(x.getId())) {
          return;
        } else {
          set.add(x.getId());
        }
      }
    }
    List<List<Vertex<Integer>>> loopPairs = new ArrayList<>();
    for (int loopIndex : combination) {
      loopPairs.add(new ArrayList<>(allCycles.get(loopIndex)));
    }
    nonTouchingLoops.get(i - 2).add(loopPairs);
  }

  public void computeNonTouchingLoops() {
    for (int i = 2; i <= noOfLoops; i++) {
      List<int[]> combinations = generate(noOfLoops, i);
      for (int[] combination : combinations) {
        checkNonTouching(combination, i);
      }
    }
  }
}

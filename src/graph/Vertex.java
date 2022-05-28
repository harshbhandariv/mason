package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
  long id;
  private T data;
  private List<Edge<T>> edges = new ArrayList<>();
  private List<Vertex<T>> adjacentVertex = new ArrayList<>();

  Vertex(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setData(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public void addAdjacentVertex(Edge<T> e, Vertex<T> v) {
    edges.add(e);
    adjacentVertex.add(v);
  }

  public String toString() {
    return String.valueOf(id);
  }

  public List<Vertex<T>> getAdjacentVertexes() {
    return adjacentVertex;
  }

  public List<Edge<T>> getEdges() {
    return edges;
  }

  public int getDegree() {
    return edges.size();
  }
}
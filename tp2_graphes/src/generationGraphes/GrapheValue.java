package generationGraphes;

import java.util.LinkedList;
import java.util.List;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class GrapheValue implements Graph {

	LinkedList<Vertex> Vertex;
	LinkedList<Edge> Edges;

	public GrapheValue() {
		this.Vertex = new LinkedList<Vertex>();
		this.Edges = new LinkedList<Edge>();
	}

	public LinkedList<Vertex> getVertex() {
		return this.Vertex;
	}

	public LinkedList<Edge> getEdges() {
		return this.Edges;
	}

	@Override
	public void addVertex() {
		Vertex newVertex = new Vertex(this.Vertex.size()+1);
		this.Vertex.add(newVertex);
	}

	public void removeVertex(Vertex vertex) {
		this.Vertex.remove(vertex.getNumber());
	}

	public void addVertexNumber(int i) throws VertexAlreadyExistException {
		Vertex newVertex = new Vertex(i);
		this.Vertex.add(newVertex);
	}

	public void removeVertexNumber(int i) {
		this.Vertex.remove(i);
	}

	public void addEdge(Vertex v1, Vertex v2) throws VertexNotFoundException {
		Edge edge = new Edge(v1, v2);
		this.Edges.add(edge);
	}

	public void addValue(Edge edge, int value) {
		edge.setValue(value);
	}

	public void addEdge(Vertex v1, Vertex v2, int value) {
		Edge edge = new Edge(v1, v2);
		edge.setValue(value);

	}

	public void addEdge(int i, int j) throws VertexNotFoundException {
		Edge edge = new Edge(this.Vertex.get(i), this.Vertex.get(j));
		this.Edges.add(edge);
	}

	public Vertex getVertex(int i) {
		return this.Vertex.get(i);
	}

	public Edge getEdge(int i, int j) {
		for (Edge e : Edges) {
			if (e.getStart().getNumber() == i && e.getEnd().getNumber() == j
					|| e.getEnd().getNumber() == i
					&& e.getStart().getNumber() == j)
				return e;
		}
		return null;
	}
	
	public Edge getEdge(Vertex i, Vertex j) {
		for (Edge e : Edges) {
			if ((e.getStart().equals(i) && e.getEnd().equals(j)) || (e.getEnd().equals(i) && e.getStart().equals(j)))
				return e;
		}
		return null;
	}

	/**
	 * Calculate the list of all Vertex's neighbours
	 * 
	 * @param v
	 *            : Vertex's number to look for neighbours
	 * @return l : list of all of v's neighbours
	 */
	public List<Vertex> getVertexNeighbours(Vertex v) {
		List<Vertex> l = new LinkedList<Vertex>();
		for (Edge e : this.Edges) {
			if (e.getStart().equals(v))
				l.add(e.getEnd());
		}
		return l;
	}

}

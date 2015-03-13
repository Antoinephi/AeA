package algorithmes;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;

import generationGraphes.*;

public class Kruskal {
	private GrapheValue graph;

	public Kruskal(GrapheValue graph) {
		this.graph = graph;
	}

	public GrapheValue algo() throws VertexAlreadyExistException {
		GrapheValue mst = new GrapheValue();

		for (int i = 0; i < this.graph.getVertex().size(); i++) {
			mst.addVertexNumber(this.graph.getVertex().get(i).getNumber());
		}

		LinkedList<Edge> F = new LinkedList<Edge>();
		Edge[] e = new Edge[this.graph.getEdges().size()];
		for (int i = 0; i < e.length; i++) {
			e[i] = this.graph.getEdges().get(i);
		}
		e = sortEdge(e);
		for (int j = 0; j < e.length; j++) {
			if (!isCyclic(e[j], F)) {
				F.add(e[j]);
			}
		}

		return mst;
	}

	public Edge[] sortEdge(Edge[] e) {
		return e;
	}

	public boolean isCyclic(Edge e, LinkedList<Edge> F) {
		for(int i=0;i<F.size();i++){
			
		}
		return true;
	}

}

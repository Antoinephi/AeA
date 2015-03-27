package graphe;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class GraphImpl implements GraphItf {

	LinkedList<Vertex> Vertex;
	LinkedList<Edge> Edges;

	public GraphImpl() {
		this.Vertex = new LinkedList<Vertex>();
		this.Edges = new LinkedList<Edge>();
	}

	public LinkedList<Vertex> getVertex() {
		return this.Vertex;
	}

	public LinkedList<Edge> getEdges() {
		return this.Edges;
	}

	public void addNVertex(int n) {
		for (int i = 0; i < n; i++) {
			this.addVertex();
		}
	}

	public void addVertexWithStart(int n, int start)
			throws VertexAlreadyExistException {
		for (int i = 0; i < n; i++) {
			this.addVertexNumber(n + i);
		}
	}

	public void addVertex(int i){
		Vertex v = new Vertex(i);
		this.Vertex.add(v);
	}
	
	@Override
	public void addVertex() {
		Vertex newVertex = new Vertex(this.Vertex.size() - 1);
		this.Vertex.add(newVertex);
	}

	public void removeVertex(Vertex vertex) {
		this.Vertex.remove(vertex.getNumber());
	}

	@Override
	public void addVertexNumber(int i) throws VertexAlreadyExistException {
		for (int j = 0; j < this.Vertex.size(); j++) {
			if (this.Vertex.get(j).getNumber() == i)
				throw new VertexAlreadyExistException();
		}
		Vertex newVertex = new Vertex(i);
		this.Vertex.add(newVertex);
	}

	public void removeVertexNumber(int i) {
		this.Vertex.remove(i);
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) throws VertexNotFoundException {
		if (!this.Vertex.contains(v1) || !this.Vertex.contains(v2))
			throw new VertexNotFoundException();
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

	@Override
	public void addEdge(int i, int j) throws VertexNotFoundException {
		Edge edge = new Edge(getVertexFromNumber(i), getVertexFromNumber(j));
		this.Edges.add(edge);
	}

	public void addEdgeValue(int i, int j, int value)
			throws VertexNotFoundException {
		Edge edge = new Edge(getVertexFromNumber(i), getVertexFromNumber(j));
		edge.setValue(value);
		this.Edges.add(edge);
	}

	@Override
	public Vertex getVertex(int i) {
		return this.Vertex.get(i);
	}

	public Vertex getVertexFromNumber(int i) throws VertexNotFoundException {
		for (Vertex vertex : this.Vertex) {
			if (vertex.getNumber() == i)
				return vertex;
		}
		throw new VertexNotFoundException();
	}

	public void affiche() {
		System.out.println(this.Edges.size());

		for (int i = 0; i < this.Edges.size(); i++) {
			System.out.println(this.Edges.get(i).getStart().getNumber()
					+ "--->" + this.Edges.get(i).getEnd().getNumber()
					+ " Value : " + this.Edges.get(i).getValue());
		}
	}
	
	public LinkedList<Vertex> getVertexNeighboors(Vertex v){
		LinkedList<Vertex> vertexNeighboors = new LinkedList<Vertex>();
		LinkedList<Edge> edgesStart = getStartEdgesFromVertex(v);
		LinkedList<Edge> edgesEnd = getEndEdgesFromVertex(v);
		for (Edge edge : edgesStart) {
			if(!vertexNeighboors.contains(edge.getStart()))
				vertexNeighboors.add(edge.getStart());
		}
		for (Edge edge : edgesEnd) {
			if(!vertexNeighboors.contains(edge.getEnd()))
				vertexNeighboors.add(edge.getEnd());
		}
		return vertexNeighboors;
	}

	public LinkedList<Edge> getEndEdgesFromVertex(Vertex v) {
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for (Edge edge : this.Edges) {
			if (edge.getStart() == v
					&& edge.getEnd().getNumber() > v.getNumber()) {
				edges.add(edge);
			}

		}
		return edges;
	}

	public LinkedList<Edge> getStartEdgesFromVertex(Vertex v) {
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for (Edge edge : this.Edges) {
			if (edge.getEnd() == v
					&& edge.getStart().getNumber() > v.getNumber()) {
				edges.add(edge);
			}

		}
		return edges;
	}

	public String graphToTxt() {
		String formatTxt = "";
		for (int i = 0; i < this.Vertex.size(); i++) {
			Vertex currentVertex = this.Vertex.get(i);
			LinkedList<Edge> startEdges = getStartEdgesFromVertex(currentVertex);
			LinkedList<Edge> endEdges = getEndEdgesFromVertex(currentVertex);
			formatTxt += this.Vertex.get(i).getNumber() + " ";

			if (!startEdges.isEmpty() || !endEdges.isEmpty()) {
				for (Edge edge : startEdges) {
					formatTxt += edge.getStart().getNumber() + " "
							+ edge.getValue() + " ";
				}
				for (Edge edge : endEdges) {
					formatTxt += edge.getEnd().getNumber() + " "
							+ edge.getValue() + " ";
				}
			}
			formatTxt += "\n";
		}

		return formatTxt;
	}

	public void txtToGraph(String txt) throws VertexNotFoundException {
		String[] txtSplitLine = txt.split("\n");
		for (int i = 0; i < txtSplitLine.length; i++) {
			String[] txtSplitWord = txtSplitLine[i].split(" ");
			this.addVertex(Integer.parseInt(txtSplitWord[0]));
		}
		for (int i = 0; i < txtSplitLine.length; i++) {
			String[] txtSplitWord = txtSplitLine[i].split(" ");
			int start = Integer.parseInt(txtSplitWord[0]);
			for (int j = 1; j < txtSplitWord.length - 1; j+=2) {
				int end = Integer.parseInt(txtSplitWord[j]);
				int value = Integer.parseInt(txtSplitWord[j + 1]);
				this.addEdgeValue(start, end, value);
			}

		}
		/*
		 * this.addVertex(Integer.parseInt(txtSplit[0])); for(int
		 * i=0;i<txtSplit.length;i++){ if(txtSplit[i] == "\n" &&
		 * i<txtSplit.length-1) this.addVertex(Integer.parseInt(txtSplit[i+1]));
		 * System.out.println("here : "+Integer.parseInt(txtSplit[i+1])); }
		 */
	}

}

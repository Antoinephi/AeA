package generationGraphes;

import java.util.LinkedList;
import java.util.List;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class GraphImpl implements GraphItf {

	List<Vertex> Vertex;
	List<Edge> Edges;

	public GraphImpl() {
		this.Vertex = new LinkedList<Vertex>();
		this.Edges = new LinkedList<Edge>();
	}

	public List<Vertex> getVertex() {
		return this.Vertex;
	}

	public List<Edge> getEdges() {
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
	
	public void addVertex(Vertex v){
		this.Vertex.add(v);
	}
	

	public void addVertex() {
		Vertex newVertex = new Vertex(this.Vertex.size());
		this.Vertex.add(newVertex);
	}

	public void removeVertex(Vertex vertex) {
		this.Vertex.remove(vertex.getNumber());
	}

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

	public void addEdge(int i, int j) throws VertexNotFoundException {
		if (i > this.Vertex.size() || j > this.Vertex.size())
			throw new VertexNotFoundException();
		Edge edge = new Edge(this.Vertex.get(i), this.Vertex.get(j));

	}
	public void addEdgeValue(int i, int j, int value)
			throws VertexNotFoundException {
		Edge edge = new Edge(getVertexFromNumber(i), getVertexFromNumber(j));
		edge.setValue(value);
		this.Edges.add(edge);
	}
	
	public void addEdge(int i, int j, int value) throws VertexNotFoundException {
		if (i > this.Vertex.size() || j > this.Vertex.size())
			throw new VertexNotFoundException();
		Edge edge = new Edge(this.Vertex.get(i), this.Vertex.get(j));
		edge.setValue(value);
		this.Edges.add(edge);
	}
	
	public void addEdge(Edge e){
		this.Edges.add(e);
	}

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
			if ((e.getStart().equals(i) && e.getEnd().equals(j))
					|| (e.getEnd().equals(i) && e.getStart().equals(j)))
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
			if (e.getStart().equals(v) && !l.contains(e.getEnd()))
				l.add(e.getEnd());
			if(e.getEnd().equals(v) && !l.contains(e.getStart()))
				l.add(e.getStart());
		}
		return l;
	}

	public void affiche() {
		System.out.println(this.Edges.size());

		for (int i = 0; i < this.Edges.size(); i++) {
			System.out.println(this.Edges.get(i).getStart().getNumber()
					+ "--->" + this.Edges.get(i).getEnd().getNumber()
					+ " Value : " + this.Edges.get(i).getValue());
		}
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

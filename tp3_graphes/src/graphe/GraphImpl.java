package graphe;

import java.util.LinkedList;

import VertexExceptions.EdgeNotFoundException;
import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class GraphImpl implements GraphItf {

	LinkedList<Vertex> Vertex;
	LinkedList<Edge> Edges;

	public GraphImpl() {
		this.Vertex = new LinkedList<Vertex>();
		this.Edges = new LinkedList<Edge>();
	}

	/**
	 * Retourne les sommets du graphe
	 * 
	 * @return la liste des sommets du graphe
	 */
	public LinkedList<Vertex> getVertex() {
		return this.Vertex;
	}

	/**
	 * Retourne les arètes du graphe
	 * 
	 * @return la liste des arètes du graphe
	 */
	public LinkedList<Edge> getEdges() {
		return this.Edges;
	}

	/**
	 * Permet d'ajouter n sommets au graphe
	 * 
	 * @param n
	 *            le nombre de sommets à ajouter
	 */
	public void addNVertex(int n) {
		for (int i = 0; i < n; i++) {
			this.addVertex();
		}
	}

	/**
	 * Permet d'ajouter n sommets dont le numéro du premier est fixé
	 * 
	 * @param n
	 *            le nombre de sommets à ajouter
	 * @param start
	 *            le numéro du premier sommet
	 * @throws VertexAlreadyExistException
	 */
	public void addVertexWithStart(int n, int start)
			throws VertexAlreadyExistException {
		for (int i = 0; i < n; i++) {
			this.addVertex(n + i);
		}
	}

	/**
	 * Ajoute le sommet de numéro i
	 * 
	 * @param i
	 *            le numéro du sommet
	 * @throws VertexAlreadyExistException
	 */
	public void addVertex(int i) throws VertexAlreadyExistException {
		boolean isPresent = false;
		for (Vertex vertex : Vertex) {
			if (vertex.getNumber() == i) {
				isPresent = true;
				break;
			}
		}
		if (isPresent)
			throw new VertexAlreadyExistException();
		Vertex v = new Vertex(i);
		this.Vertex.add(v);
	}

	/**
	 * Ajoute un sommet, celui-ci aura le numéro suivant
	 */
	public void addVertex() {
		Vertex newVertex = new Vertex(this.Vertex.size() - 1);
		this.Vertex.add(newVertex);
	}

	/**
	 * Supprime un sommet
	 * 
	 * @param vertex
	 *            le sommet à supprimer du graphe
	 * @throws VertexNotFoundException
	 *             si le sommet n'existe pas
	 */
	public void removeVertex(Vertex vertex) throws VertexNotFoundException {
		if (!Vertex.contains(vertex))
			throw new VertexNotFoundException();
		this.Vertex.remove(vertex.getNumber());
	}

	/**
	 * Supprime le sommet de numéro i
	 * 
	 * @param i
	 *            le numéro du sommet à supprimer
	 * @throws VertexNotFoundException
	 *             si le sommet n'existe pas
	 */
	public void removeVertexNumber(int i) throws VertexNotFoundException {
		boolean isPresent = false;
		for (Vertex v : Vertex) {
			if (v.getNumber() == i) {
				isPresent = true;
				break;
			}
		}
		if (!isPresent)
			throw new VertexNotFoundException();
		this.Vertex.remove(i);
	}

	/**
	 * Ajoute une arète entre les sommets v1 et v2
	 * 
	 * @param v1
	 *            le sommet de début de l'arète
	 * @param v2
	 *            le sommet de fin de l'arète
	 * @throws VertexNotFoundException
	 *             si l'un des deux sommets n'existe pas
	 */
	public void addEdge(Vertex v1, Vertex v2) throws VertexNotFoundException {
		if (!this.Vertex.contains(v1) || !this.Vertex.contains(v2))
			throw new VertexNotFoundException();
		Edge edge = new Edge(v1, v2);
		this.Edges.add(edge);
	}

	/**
	 * Ajoute une pondération à l'arète
	 * 
	 * @param edge
	 *            l'arète à valuer
	 * @param value
	 *            la valeur donnée
	 * @throws EdgeNotFoundException
	 */
	public void addValue(Edge edge, int value) throws EdgeNotFoundException {
		if (!Edges.contains(edge))
			throw new EdgeNotFoundException();
		edge.setValue(value);
	}

	/**
	 * Ajoute l'arète de début v1 et de fin v2
	 * 
	 * @param v1
	 *            le sommet de départ
	 * @param v2
	 *            le somemt d'arrivée
	 * @param value
	 *            la valeur a donner à l'arète
	 * @throws VertexNotFoundException
	 *             si l'un de deux sommets n'existe pas
	 */
	public void addEdge(Vertex v1, Vertex v2, int value)
			throws VertexNotFoundException {
		if (!Vertex.contains(v1) || !Vertex.contains(v2))
			throw new VertexNotFoundException();
		Edge edge = new Edge(v1, v2);
		edge.setValue(value);

	}

	/**
	 * Ajoute l'arète dont le numéro de somme de départ est i et le numéro de
	 * sommet d'arrivée est j
	 * 
	 * @param i
	 *            le numéro du sommet de départ
	 * @param j
	 *            le numéro du sommet d'arrivée
	 * @throws VertexNotFoundException
	 *             si l'un des deux sommets n'existe pas
	 */
	public void addEdge(int i, int j) throws VertexNotFoundException {
		boolean isPresenti = false;
		boolean isPresentj = false;
		for (Vertex v : Vertex) {
			if (v.getNumber() == i)
				isPresenti = true;
			if (v.getNumber() == j)
				isPresentj = true;
		}
		if (!isPresenti || !isPresentj)
			throw new VertexNotFoundException();

		Edge edge = new Edge(getVertexFromNumber(i), getVertexFromNumber(j));
		this.Edges.add(edge);
	}

	/**
	 * Ajoute l'arète valuée dont le numéro du sommet de départ est i, le numéro du sommet d'arrivée j et la valeur value
	 * @param i le numéro du sommet de départ
	 * @param j le numéro du sommet d'arrivée
	 * @param value la pondération du graphe
	 * @throws VertexNotFoundException
	 */
	public void addEdgeValue(int i, int j, int value)
			throws VertexNotFoundException {
		boolean isPresenti = false;
		boolean isPresentj = false;
		for (Vertex v : Vertex) {
			if (v.getNumber() == i)
				isPresenti = true;
			if (v.getNumber() == j)
				isPresentj = true;
		}
		if (!isPresenti || !isPresentj)
			throw new VertexNotFoundException();
		Edge edge = new Edge(getVertexFromNumber(i), getVertexFromNumber(j));
		edge.setValue(value);
		this.Edges.add(edge);
	}

	/**
	 * Retourne le ième sommet de la liste des sommets du graphe
	 * 
	 * @param l
	 *            'indice du sommet à retourner
	 * @return le ième sommet
	 * @throws VertexNotFoundException
	 *             si le nombre de sommet est inférieur à i
	 */
	public Vertex getVertex(int i) throws VertexNotFoundException {
		if (this.Vertex.size() < i)
			throw new VertexNotFoundException();
		return this.Vertex.get(i);
	}

	/**
	 * Retourne le sommet numéro i
	 * 
	 * @param i
	 *            le numéro du sommet à retourner
	 * @return le sommet de numéro i
	 * @throws VertexNotFoundException
	 *             si ce sommet n'existe pas
	 */
	public Vertex getVertexFromNumber(int i) throws VertexNotFoundException {
		for (Vertex vertex : this.Vertex) {
			if (vertex.getNumber() == i)
				return vertex;
		}
		throw new VertexNotFoundException();
	}

	/**
	 * Permet l'affichage du graphe
	 */
	public void affiche() {
		for (int i = 0; i < this.Edges.size(); i++) {
			System.out.println(this.Edges.get(i).getStart().getNumber()
					+ "--->" + this.Edges.get(i).getEnd().getNumber()
					+ " Value : " + this.Edges.get(i).getValue()
					+ " Color start : "
					+ this.Edges.get(i).getStart().getColor() + " Color end : "
					+ this.Edges.get(i).getEnd().getColor());
		}
	}

	/**
	 * Retourne le degré du sommet v
	 * 
	 * @param v
	 *            le sommet dont on veut connaitre le degré
	 * @return le degré de ce sommet
	 * @throws VertexNotFoundException
	 *             si le sommet v n'existe pas
	 */
	public int getVertexDegree(Vertex v) throws VertexNotFoundException {
		if (!Vertex.contains(v))
			throw new VertexNotFoundException();
		int degree = 0;
		for (Edge edge : Edges) {
			if (edge.getStart() == v || edge.getEnd() == v)
				degree++;
		}
		return degree;
	}

	/**
	 * Retourne les sommets voisins du sommet v
	 * 
	 * @param v
	 *            le sommet dont on souhaite connaitre les voisins
	 * @return la liste des voisins de v
	 * @throws VertexNotFoundException
	 *             si ce sommet n'existe pas
	 */
	public LinkedList<Vertex> getVertexNeighboors(Vertex v)
			throws VertexNotFoundException {
		if (!Vertex.contains(v))
			throw new VertexNotFoundException();
		LinkedList<Vertex> vertexNeighboors = new LinkedList<Vertex>();
		LinkedList<Edge> edgesStart = getStartEdgesFromVertex(v);
		LinkedList<Edge> edgesEnd = getEndEdgesFromVertex(v);
		for (Edge edge : edgesStart) {
			if (!vertexNeighboors.contains(edge.getStart()))
				vertexNeighboors.add(edge.getStart());
		}
		for (Edge edge : edgesEnd) {
			if (!vertexNeighboors.contains(edge.getEnd()))
				vertexNeighboors.add(edge.getEnd());
		}
		return vertexNeighboors;
	}

	/**
	 * Retourne la liste des sommets vEnd tels qu'il existe une arète pour
	 * laquelle v est un départ et vEnd une arrivée
	 * 
	 * @param v
	 *            le sommet de départ
	 * @return la liste des sommets
	 * @throws VertexNotFoundException
	 *             si le sommet de départ n'existe pas
	 */
	public LinkedList<Edge> getEndEdgesFromVertex(Vertex v)
			throws VertexNotFoundException {
		if (!Vertex.contains(v))
			throw new VertexNotFoundException();
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for (Edge edge : this.Edges) {
			if (edge.getStart() == v
					&& edge.getEnd().getNumber() != v.getNumber()) {
				edges.add(edge);
			}

		}
		return edges;
	}

	/**
	 * Retourne la liste des sommets vStart tels qu'il existe une arète pour
	 * laquelle v est une arrivée et vStart un départ
	 * 
	 * @param v
	 *            le sommet d'arrivée
	 * @return la liste des sommets
	 * @throws VertexNotFoundException
	 *             si le sommet d'arrivée n'existe pas
	 */
	public LinkedList<Edge> getStartEdgesFromVertex(Vertex v)
			throws VertexNotFoundException {
		if (!Vertex.contains(v))
			throw new VertexNotFoundException();
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for (Edge edge : this.Edges) {
			if (edge.getEnd() == v
					&& edge.getStart().getNumber() != v.getNumber()) {
				edges.add(edge);
			}

		}
		return edges;
	}

	/**
	 * Retourne le texte correspondant au graphe
	 * 
	 * @return un texte représentant le graphe
	 * @throws VertexNotFoundException
	 */
	public String graphToTxt() throws VertexNotFoundException {
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

	/**
	 * Crée un graphe à partir d'un format texte représentant ce graphe
	 * 
	 * @param txt
	 *            le texte représentant un graphe
	 * @throws VertexNotFoundException
	 * @throws NumberFormatException
	 * @throws VertexAlreadyExistException
	 */
	public void txtToGraph(String txt) throws VertexNotFoundException,
			NumberFormatException, VertexAlreadyExistException {
		String[] txtSplitLine = txt.split("\n");
		for (int i = 0; i < txtSplitLine.length; i++) {
			String[] txtSplitWord = txtSplitLine[i].split(" ");
			this.addVertex(Integer.parseInt(txtSplitWord[0]));
		}
		for (int i = 0; i < txtSplitLine.length; i++) {
			String[] txtSplitWord = txtSplitLine[i].split(" ");
			int start = Integer.parseInt(txtSplitWord[0]);
			for (int j = 1; j < txtSplitWord.length - 1; j += 2) {
				int end = Integer.parseInt(txtSplitWord[j]);
				int value = Integer.parseInt(txtSplitWord[j + 1]);
				this.addEdgeValue(start, end, value);
			}

		}
	}

}

package generationGraphes;

import java.util.LinkedList;
import java.util.List;

import VertexExceptions.EdgeNotFoundException;
import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class GraphImpl implements GraphItf {

	List<Vertex> Vertex;
	List<Edge> Edges;

	public GraphImpl() {
		this.Vertex = new LinkedList<Vertex>();
		this.Edges = new LinkedList<Edge>();
	}

	/**
	 * Retourne la liste des sommets
	 * 
	 * @return la liste des sommets du graphe
	 */
	public List<Vertex> getVertex() {
		return this.Vertex;
	}

	/**
	 * Retourne la liste des ar�tes du graphe
	 * 
	 * @return la liste des ar�tes du graphe
	 */
	public List<Edge> getEdges() {
		return this.Edges;
	}

	/**
	 * Ajoute n sommets au graphe
	 * 
	 * @param n
	 *            le nombre de sommets � ajouter
	 */
	public void addNVertex(int n) {
		for (int i = 0; i < n; i++) {
			this.addVertex();
		}
	}

	/**
	 * Ajoute n sommet dont le premier a le num�ro start
	 * 
	 * @param n
	 *            le nombre de sommet � ajouter
	 * @param start
	 *            le num�ro du premier sommet
	 * @throws VertexAlreadyExistException
	 */
	public void addVertexWithStart(int n, int start)
			throws VertexAlreadyExistException {
		for (int i = 0; i < n; i++) {
			for (Vertex v : Vertex) {
				if (v.getNumber() == n + i)
					throw new VertexAlreadyExistException();
			}
			this.addVertex(n + i);
		}
	}

	/**
	 * Ajoute le sommet de num�ro i
	 * 
	 * @param i
	 *            le num�ro du sommet � ajouter
	 * @throws VertexAlreadyExistException
	 */
	public void addVertex(int i) throws VertexAlreadyExistException {
		for (Vertex v : Vertex) {
			if (v.getNumber() == i)
				throw new VertexAlreadyExistException();
		}
		Vertex v = new Vertex(i);
		this.Vertex.add(v);
	}

	/**
	 * Ajoute le sommet v au graphe
	 * 
	 * @param v
	 *            le sommet � ajouter
	 * @throws VertexAlreadyExistException
	 */
	public void addVertex(Vertex v) throws VertexAlreadyExistException {
		if (Vertex.contains(v))
			throw new VertexAlreadyExistException();
		this.Vertex.add(v);
	}

	/**
	 * Ajoute un sommet au graphe
	 */
	public void addVertex() {
		Vertex newVertex = new Vertex(this.Vertex.size());
		this.Vertex.add(newVertex);
	}

	/**
	 * Supprime un sommet du graphe
	 * 
	 * @param vertex
	 *            le sommet � supprimer
	 * @throws VertexNotFoundException
	 */
	public void removeVertex(Vertex vertex) throws VertexNotFoundException {
		if (!Vertex.contains(vertex))
			throw new VertexNotFoundException();
		this.Vertex.remove(vertex.getNumber());
	}

	/**
	 * Supprime le sommet i
	 * 
	 * @param i
	 *            le num�ro du sommet � retirer
	 * @throws VertexNotFoundException
	 */
	public void removeVertexNumber(int i) throws VertexNotFoundException {
		if (Vertex.size() < i)
			throw new VertexNotFoundException();
		this.Vertex.remove(i);
	}

	/**
	 * Ajoute l'ar�te dont le d�part est v1 et l'arriv�e v2
	 * 
	 * @param v1
	 *            le sommet de d�part
	 * @param v2
	 *            le sommet d'arriv�e
	 */
	public void addEdge(Vertex v1, Vertex v2) {
		if (!this.Vertex.contains(v1))
			this.Vertex.add(v1);
		if (!this.Vertex.contains(v2))
			this.Vertex.add(v2);
		Edge edge = new Edge(v1, v2);
		if (!this.Edges.contains(edge) && !v1.equals(v2))
			this.Edges.add(edge);
	}

	/**
	 * Ajoute une valeur � l'ar�te
	 * 
	 * @param edge
	 *            l'ar�te que l'on veut valuer
	 * @param value
	 *            la valeur donn�e � l'ar�te
	 */
	public void addValue(Edge edge, int value) {
		edge.setValue(value);
	}

	/**
	 * Ajoute l'ar�te de d�part v1, d'arriv�e v2 et de valeur value
	 * 
	 * @param v1
	 *            le sommet de d�part
	 * @param v2
	 *            le sommet d'arriv�e
	 * @param value
	 *            la valeur de l'ar�te
	 * @throws VertexNotFoundException
	 *             si l'une des deux ar�tes n'existe pas
	 */
	public void addEdge(Vertex v1, Vertex v2, int value)
			throws VertexNotFoundException {
		if (!Vertex.contains(v1) || !Vertex.contains(v2))
			throw new VertexNotFoundException();
		Edge edge = new Edge(v1, v2);
		edge.setValue(value);
		this.Edges.add(edge);
	}

	/**
	 * Ajoute l'ar�te de d�part le i�me sommet et d'arriv�e le j�me sommet
	 * 
	 * @param i
	 *            l'indice du sommet de d�part
	 * @param j
	 *            l'indice du sommet d'arriv�e
	 */
	public void addEdge(int i, int j) throws VertexNotFoundException {
		if (i > this.Vertex.size() || j > this.Vertex.size())
			throw new VertexNotFoundException();
		Edge edge = new Edge(this.Vertex.get(i - 1), this.Vertex.get(j - 1));
		this.Edges.add(edge);
	}

	/**
	 * Ajoute une ar�te valu�e entre le i�me sommet et le j�me sommet
	 * 
	 * @param i
	 *            l'indice du sommet de d�part
	 * @param j
	 *            l'indice du sommet d'arriv�e
	 * @param value
	 *            la valeur de l'ar�te
	 * @throws VertexNotFoundException
	 *             si l'un des deux sommets n'existe pas
	 */
	public void addEdge(int i, int j, int value) throws VertexNotFoundException {
		if (i > this.Vertex.size() || j > this.Vertex.size())
			throw new VertexNotFoundException();
		Edge edge = new Edge(this.Vertex.get(i), this.Vertex.get(j));
		edge.setValue(value);
		this.Edges.add(edge);
	}

	/**
	 * Ajoute au graphe l'ar�te e
	 * 
	 * @param e
	 *            une ar�te
	 */
	public void addEdge(Edge e) {
		this.Edges.add(e);
	}

	/**
	 * Retourne le i�me sommet
	 * 
	 * @throws VertexNotFoundException
	 */
	public Vertex getVertex(int i) throws VertexNotFoundException {
		if (Vertex.size() < i)
			throw new VertexNotFoundException();
		return this.Vertex.get(i);
	}

	/**
	 * Retourne le sommet dont le num�ro est i
	 * 
	 * @param i
	 *            le num�ro du sommet � retourner
	 * @return le sommet de num�ro i
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
	 * Retourne l'ar�te dont le num�ro de d�part est i et le num�ro d'arriv�e j
	 * 
	 * @param i
	 *            le num�ro du sommet de d�part
	 * @param j
	 *            le num�ro du sommet d'arriv�e
	 * @return l'ar�te correspondante
	 * @throws EdgeNotFoundException
	 */
	public Edge getEdge(int i, int j) throws EdgeNotFoundException {
		for (Edge e : Edges) {
			if (e.getStart().getNumber() == i && e.getEnd().getNumber() == j
					|| e.getEnd().getNumber() == i
					&& e.getStart().getNumber() == j)
				return e;
		}
		throw new EdgeNotFoundException();
	}

	/**
	 * Retourne l'ar�te de d�part le sommet i et d'arriv�e le sommet j
	 * 
	 * @param i
	 *            le sommet de d�part
	 * @param j
	 *            le sommet d'arriv�e
	 * @return l'ar�te correspondante
	 * @throws EdgeNotFoundException
	 */
	public Edge getEdge(Vertex v1, Vertex v2) throws EdgeNotFoundException {
		for (Edge e : Edges) {
			if ((e.getStart().equals(v1) && e.getEnd().equals(v2))
					|| (e.getEnd().equals(v1) && e.getStart().equals(v2)))
				return e;
		}
		throw new EdgeNotFoundException();
	}

	/**
	 * Set la liste des ar�tes � edges
	 * 
	 * @param Edges
	 *            la liste des ar�tes
	 */
	public void setEdges(List<Edge> Edges) {
		this.Edges = Edges;
	}

	/**
	 * Calculate the list of all Vertex's neighbours Compléxité pire des cas :
	 * O(n)
	 * 
	 * @param v
	 *            : Vertex's number to look for neighbours
	 * @return l : list of all of v's neighbours
	 * @throws VertexNotFoundException
	 */
	public List<Vertex> getVertexNeighbours(Vertex v)
			throws VertexNotFoundException {
		if (!Vertex.contains(v))
			throw new VertexNotFoundException();
		List<Vertex> l = new LinkedList<Vertex>();
		for (Edge e : this.Edges) {
			if (e.getStart().equals(v))
				l.add(e.getEnd());
			else if (e.getEnd().equals(v))
				l.add(e.getStart());
		}
		return l;
	}

	/**
	 * Affiche le graphe
	 */
	public void affiche() {
		System.out.println(this.Edges.size());

		for (int i = 0; i < this.Edges.size(); i++) {
			System.out.println(this.Edges.get(i).getStart().getNumber()
					+ "--->" + this.Edges.get(i).getEnd().getNumber()
					+ " Value : " + this.Edges.get(i).getValue());
		}
	}

	/**
	 * Retourne la liste des sommets vEnd tels qu'il existe une ar�te pour
	 * laquelle v est un d�part et vEnd une arriv�e
	 * 
	 * @param v
	 *            le sommet de d�part
	 * @return la liste des sommets
	 * @throws VertexNotFoundException
	 *             si le sommet de d�part n'existe pas
	 */
	public LinkedList<Edge> getEndEdgesFromVertex(Vertex v)
			throws VertexNotFoundException {
		if (!Vertex.contains(v))
			throw new VertexNotFoundException();
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for (Edge edge : this.Edges) {
			if (edge.getStart() == v
					&& edge.getEnd().getNumber() > v.getNumber()) {
				edges.add(edge);
			}

		}
		return edges;
	}

	/**
	 * Retourne la liste des sommets vStart tels qu'il existe une ar�te pour
	 * laquelle v est une arriv�e et vStart un d�part
	 * 
	 * @param v
	 *            le sommet d'arriv�e
	 * @return la liste des sommets
	 * @throws VertexNotFoundException
	 *             si le sommet d'arriv�e n'existe pas
	 */
	public LinkedList<Edge> getStartEdgesFromVertex(Vertex v)
			throws VertexNotFoundException {
		if (!Vertex.contains(v))
			throw new VertexNotFoundException();
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for (Edge edge : this.Edges) {
			if (edge.getEnd() == v
					&& edge.getStart().getNumber() > v.getNumber()) {
				edges.add(edge);
			}

		}
		return edges;
	}
	
	
	/**
	 * Retourne le texte correspondant au graphe
	 * 
	 * @return un texte repr�sentant le graphe
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
	 * Cr�e un graphe � partir d'un format texte repr�sentant ce graphe
	 * 
	 * @param txt
	 *            le texte repr�sentant un graphe
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
				this.addEdge(start, end, value);
			}

		}
	}

}

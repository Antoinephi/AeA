package graphe;

public class Edge {
	private Vertex start;
	private Vertex end;
	private int value;

	/**
	 * Constructeur d'une arète
	 * 
	 * @param start
	 *            le numéro de sommet du début de l'arète
	 * @param end
	 *            le numéro de sommet de fin de l'arète
	 */
	public Edge(Vertex start, Vertex end) {
		this.start = start;
		this.end = end;
		this.value = 0;
	}

	/**
	 * Retourne le numéro de sommet du début de l'arète
	 * 
	 * @return le numéro de sommet du début de l'arète
	 */
	public Vertex getStart() {
		return this.start;
	}

	/**
	 * Retourne le numéro de sommet de la fin de l'arète
	 * 
	 * @return le numéro de sommet de la fin de l'arète
	 */
	public Vertex getEnd() {
		return this.end;
	}

	/**
	 * Permet de fixer une pondération à l'arète
	 * 
	 * @param value
	 *            un entier représentant la pondération de l'arète
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Retourne la pondération de l'arète
	 * 
	 * @return la valeur de pondération de l'arète
	 */
	public int getValue() {
		return this.value;
	}

}

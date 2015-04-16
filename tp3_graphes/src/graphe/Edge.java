package graphe;

public class Edge {
	private Vertex start;
	private Vertex end;
	private int value;

	/**
	 * Constructeur d'une ar�te
	 * 
	 * @param start
	 *            le num�ro de sommet du d�but de l'ar�te
	 * @param end
	 *            le num�ro de sommet de fin de l'ar�te
	 */
	public Edge(Vertex start, Vertex end) {
		this.start = start;
		this.end = end;
		this.value = 0;
	}

	/**
	 * Retourne le num�ro de sommet du d�but de l'ar�te
	 * 
	 * @return le num�ro de sommet du d�but de l'ar�te
	 */
	public Vertex getStart() {
		return this.start;
	}

	/**
	 * Retourne le num�ro de sommet de la fin de l'ar�te
	 * 
	 * @return le num�ro de sommet de la fin de l'ar�te
	 */
	public Vertex getEnd() {
		return this.end;
	}

	/**
	 * Permet de fixer une pond�ration � l'ar�te
	 * 
	 * @param value
	 *            un entier repr�sentant la pond�ration de l'ar�te
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Retourne la pond�ration de l'ar�te
	 * 
	 * @return la valeur de pond�ration de l'ar�te
	 */
	public int getValue() {
		return this.value;
	}

}

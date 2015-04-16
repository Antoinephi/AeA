package graphe;

public class Vertex {
	private int number;
	private int color;

	/**
	 * Constructeur de sommet
	 * 
	 * @param number
	 *            le numéro du sommet
	 */
	public Vertex(int number) {
		this.number = number;
		this.color = -1;
	}

	/**
	 * Retourne le numéro du sommet
	 * 
	 * @return le numéro du sommet
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Permet de vérifier que l'objet est égal au sommet
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		return this.getNumber() == ((Vertex) o).getNumber();
	}

	/**
	 * Retourne la couleur attribuée au sommet
	 * 
	 * @return la couleur attribuée au sommet
	 */
	public int getColor() {
		return this.color;
	}

	/**
	 * Attribue une couleur au sommet
	 * 
	 * @param color
	 *            la couleur à attribuer au sommet
	 */
	public void setColor(int color) {
		this.color = color;
	}
}

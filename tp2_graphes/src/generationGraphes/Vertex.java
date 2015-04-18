package generationGraphes;

public class Vertex {
	private int number;
	private int part;

	public Vertex(int number) {
		this.number = number;
		this.part = -1;
	}

	/**
	 * Set la part pour Kruskal (iscyclic)
	 * 
	 * @param part
	 *            la partie � laquelle fait partie le sommet
	 */
	public void setPart(int part) {
		this.part = part;
	}

	/**
	 * Retourne la partie � laquelle fait partie le sommet
	 * 
	 * @return part la partie � laquelle fait partie le sommet
	 */
	public int getPart() {
		return this.part;
	}

	/**
	 * Retourne le num�ro du sommet
	 * 
	 * @return le num�ro du sommet
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * V�rifie que l'objet o est bien le sommet boolean
	 */
	public boolean equals(Object o) {
		return this.getNumber() == ((Vertex) o).getNumber();
	}
	
	public String toString(){
		return this.number + "";
	}

}

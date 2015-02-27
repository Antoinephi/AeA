
public class Graphe {
	private String[] lesMots;
	private Liste[] listeSucc;
	private int nb;
	
	public Graphe(String[] lesMots){
		this.lesMots = lesMots;
		this.nb = lesMots.length;
		this.listeSucc = new Liste[this.nb];
	}
	
	/**
	 * Ajoute une arete entre S et D
	 * @param s l'indice du mot S dans lesMots
	 * @param d l'indice du mot D dans lesMots
	 */
	public void ajouterArete(int s, int d){
		// Création des aretes
		Liste nextS = new Liste(d,null);
		Liste nextD = new Liste(s,null);
		
		// Recherche du dernier élément de la liste
		Liste lastS = this.lastSucc(s);
		Liste lastD = this.lastSucc(d);
		
		// Ajout de l'arete entre S et D
		lastS.setNextElement(nextS);
		lastD.setNextElement(nextD);
	}
	
	/**
	 * Permet de chercher le dernier successeur d'un mot 
	 * @param s
	 * @return
	 */
	private Liste lastSucc(int s){
		Liste current = this.listeSucc[s];
		while(current.getNextElement() != null){
			current = current.getNextElement();
		}
		return current;
	}
	
	public void lettreQuiSaute(){
		
	}
	
}

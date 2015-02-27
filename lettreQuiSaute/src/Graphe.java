public class Graphe {
	private String[] lesMots;
	private Liste[] listeSucc;
	private int nb;

	public Graphe(String[] lesMots) {
		this.lesMots = lesMots;
		this.nb = lesMots.length;
		this.listeSucc = new Liste[this.nb];
		this.initList();
	}
	
	private void initList(){
		for(int i=0;i<this.lesMots.length;i++){
			this.listeSucc[i] = new Liste(i,null);
		}
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
	@SuppressWarnings("finally")
	private Liste lastSucc(int s){
		Liste current = this.listeSucc[s];
			try {
				while(current.getNextElement() != null){
					current = current.getNextElement();
				}
			} finally {
				return current;
			}
	}

	public void lettreQuiSaute() {
		for (int i = 0; i < this.nb; i++) {
			for (int j = 0; j < this.nb; j++) {
				if (diffUneLettre(this.lesMots[i], this.lesMots[j]))
					this.ajouterArete(i,j);
			}
		}

	}

	private boolean diffUneLettre(String a, String b) {
		// a et b supposees de meme longueur
		int i = 0;
		while (i < a.length() && a.charAt(i) == b.charAt(i))
			++i;
		if (i == a.length())
			return false;
		++i;
		while (i < a.length() && a.charAt(i) == b.charAt(i))
			++i;
		if (i == a.length())
			return true;
		return false;
	}


public static void main (String[] args) {
    String[] dico3court = {
       "gag", "gai", "gaz", "gel", "gks", "gin", 
       "gnu", "glu", "gui", "guy", "gre", "gue", 
       "ace", "acm", "agi", "ait", "aie", "ail", 
       "air", "and", "alu", "ami", "arc", "are", 
       "art", "apr", "avr", "sur", "mat", "mur" } ;
    Graphe g = new Graphe (dico3court) ;
    g.lettreQuiSaute() ;
    //afficher (g) ;
  }
}

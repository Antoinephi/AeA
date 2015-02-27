
public class Graphe {
	private String[] lesMots;
	private Liste listeSucc;
	private int nb;
	
	public Graphe(String[] lesMots){
		this.lesMots = lesMots;
		this.nb = lesMots.length;
		this.listeSucc = new Liste(this.nb);
	}
	
	static void ajouterArete(Graphe g, int s, int d){
		
	}
	
}

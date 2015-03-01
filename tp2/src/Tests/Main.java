package Tests;

public class Main {
	
	public static void main(String[] args) {
		Simulateur sim = new Simulateur();
		char[] preMiARN = sim.genPreMiARNBoucles(true);
		CheckSimulateur check = new CheckSimulateur(preMiARN);
		System.out.println("Taille : "+check.checkSize());
		System.out.println("Nombre d'appariements correct : "+check.enoughMatching());
		System.out.println("Ordre des appariements correct : "+ check.goodMatching());
		System.out.println("Au moins trois appariements : "+check.atLeastThreeMatching());
		System.out.println("Nombre de nucléotides dans les boucles correct : "+ check.nbInLoop());
	}
	
}

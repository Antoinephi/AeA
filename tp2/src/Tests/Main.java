package Tests;

import RechercheMotif.RecherchePreMiARN;

public class Main {
	
	public static void main(String[] args) {
		Simulateur sim = new Simulateur();
		char[] preMiARN = sim.genPreMiARNBoucles(false);
		//CheckSimulateur check = new CheckSimulateur(preMiARN);
	/*	System.out.println("Taille : "+check.checkSize());
		System.out.println("Nombre d'appariements correct : "+check.enoughMatching());
		System.out.println("Ordre des appariements correct : "+ check.goodMatching());
		System.out.println("Au moins trois appariements : "+check.atLeastThreeMatching());
		System.out.println("Nombre de nucléotides dans les boucles correct : "+ check.nbInLoop());
	*/ 
		char[] sequence = {'G','C','C','U','A','G','C','G'};
		char[] sequence1 = {'U','C','U','A','C','A','G','U','A','C','G','G','C','U','A','U'};
		RecherchePreMiARN algo = new RecherchePreMiARN(sequence);
		char[] result = algo.algoNeedlemanAndWunsch();
	}
	
}

import java.util.Arrays;

import Parser.FastaParser;
import RechercheMotif.AlgoRecherche;
import RechercheMotif.KMP;

public class Main {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		int longMotif = 5;
		FastaParser f = new FastaParser(
				"resources/chromosome13_NT_009952.14.fasta");
		/*FastaParser f = new FastaParser(
				"resources/test.fa");*/
		char[] sequence = f.readFile();
		char[] motif = Arrays.copyOfRange(sequence, 0, longMotif);
		AlgoRecherche kmp = 
				new KMP(sequence,motif);
		kmp.setMotif(motif);
		kmp.calculTous(5);				
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime/1000 + "s");

	}

}

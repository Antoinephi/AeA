import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import Conversion.ConvertSequence;
import Parser.FastaParser;
import RechercheMotif.KMP;

public class Main {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		int longMotif = 5;
		FastaParser f = new FastaParser(
				"resources/chromosome13_NT_009952.14.fasta");
		char[] sequence = f.readFile();
		char[] motif = Arrays.copyOfRange(sequence, 0, longMotif);
		KMP kmp = new KMP(sequence, motif);
		List<Integer> listeOccurences = null;
		PrintWriter p;
		try {
			p = new PrintWriter("file.txt", "UTF-8");
		
			
			for (int i = 0; i < sequence.length - longMotif; i++) {	
				motif = Arrays.copyOfRange(sequence, i, i + longMotif);
				ConvertSequence c = new ConvertSequence(motif);
				char[] motifReverse = c.getReverse();
				char[] motifComp = c.getComp();
				char[] motifReverseComp = c.getReverseComp();
				kmp.setMotif(motif);
				kmp.preCalcul();
				kmp.calcul();
				kmp.setMotif(motifReverse);
				kmp.preCalcul();
				kmp.calcul();
				kmp.setMotif(motifComp);
				kmp.preCalcul();
				kmp.calcul();
				kmp.setMotif(motifReverseComp);
				kmp.preCalcul();
				kmp.calcul();
				listeOccurences = kmp.getListOccurences();
				kmp.resetListOccurences();
				for(int j=0;j<listeOccurences.size();j+=10){
					int occurence = listeOccurences.get(j);
					p.println(i + " " + occurence);			
				}
				System.out
						.println(((float) i / (sequence.length - longMotif)) * 100);
			}
			p.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime/3600);

	}

}

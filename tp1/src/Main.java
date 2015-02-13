import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Conversion.ConvertSequence;
import Parser.FastaParser;
import RechercheMotif.KMP;

public class Main {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		int longMotif = 5;
		/*FastaParser f = new FastaParser(
				"resources/chromosome13_NT_009952.14.fasta");*/
		FastaParser f = new FastaParser(
				"resources/test.fa");
		char[] sequence = f.readFile();
		char[] motif = Arrays.copyOfRange(sequence, 0, longMotif);
		KMP kmp = new KMP(sequence, motif);
		List<Integer> listeOccurences = null;
		PrintWriter p;
		Map<Integer, Integer> toutesOccurences = new LinkedHashMap<Integer, Integer>();
		try {
			p = new PrintWriter("file.txt", "UTF-8");
		
			
			for (int i = 0; i < sequence.length - longMotif; i++) {	
				motif = Arrays.copyOfRange(sequence, i, i + longMotif);
				ConvertSequence c = new ConvertSequence(motif);
				char[] motifReverse = c.getReverse();
				char[] motifComp = c.getComp();
				char[] motifReverseComp = c.getReverseComp();
				kmp.setMotif(motif);
				kmp.calcul();
				kmp.setMotif(motifReverse);
				kmp.calcul();
				kmp.setMotif(motifComp);
				kmp.calcul();
				kmp.setMotif(motifReverseComp);
				kmp.calcul();
				listeOccurences = kmp.getListOccurences();
				for(int j=0;j<listeOccurences.size();j++){
					int occurence = listeOccurences.get(j);
					if(toutesOccurences.containsKey(occurence))
						toutesOccurences.put(occurence, toutesOccurences.get(occurence)+1);
					else
						toutesOccurences.put(occurence, 1);
					//p.println(i + " " + occurence);			
				}
				kmp.resetListOccurences();

				System.out
						.println(((float) i / (sequence.length - longMotif)) * 100);
			}
			Set<Integer> keys = toutesOccurences.keySet();
			Iterator<Integer> it = keys.iterator();
			for(int i = 0; i<toutesOccurences.size(); i++){
				int indice = it.next();
				p.println(indice + " " + toutesOccurences.get(indice));
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
		System.out.println(elapsedTime/60 + "s");

	}

}

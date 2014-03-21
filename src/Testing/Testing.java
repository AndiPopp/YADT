package Testing;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import eu.sffi.dsa4.SpielgruppenKonfiguration;
import eu.sffi.dsa4.alchemie.AlchemieKonfiguration;
import eu.sffi.dsa4.alchemie.Elixier;
import eu.sffi.dsa4.alchemie.ElixierArt;
import eu.sffi.dsa4.util.SimplePersistentNamedCollection;




public class Testing {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		

//		AventurischesDatum DD = new AventurischesDatum(-25, 1, 1, 17);
//		System.out.println(DD.getNumerischenMonat());
		
//		Held Alrik = new Held("Alrik", 12, 14, 12, 11, 12, 11, 12, 11);
//		try {
//			Alrik.addTalentWert(Talent.KOCHEN, 5);
//			Alrik.addTalentWert(Talent.ALCHEMIE, 12);
//			
//		} catch (TalentException e) {
//			e.printStackTrace();
//		}
		
		
//		SpielgruppenKonfiguration spielgruppenKonfiguration = new SpielgruppenKonfiguration(AlchemieKonfiguration.STANDARDKONFIGURATION);
//		spielgruppenKonfiguration.save("/home/andi/spielgruppe.dat");
		
		SpielgruppenKonfiguration spielgruppenKonfiguration = SpielgruppenKonfiguration.load("/home/andi/spielgruppe.dat");
		System.out.println(spielgruppenKonfiguration.alchemieKonfiguration.elixierArten);
	}

}

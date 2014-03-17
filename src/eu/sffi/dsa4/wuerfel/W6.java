/**
 * 
 */
package eu.sffi.dsa4.wuerfel;

import java.util.Random;

/**
 * @author Andi Popp
 *
 */
public class W6 extends Wuerfel{

	/**
	 * Konstruiert einen neunen W6
	 */
	public W6(){
		this.zufallsgenerator = new Random();
	}
	
	/**
	 * Konstruiert einen neuen W6 und seeded den 
	 * Zufallsgenerator mit dem angegebenen Seed
	 * @param seed
	 */
	public W6(long seed){
		this.zufallsgenerator = new Random(seed);
	}
	
	@Override
	public byte werfen() {
		return (byte) Math.ceil(this.zufallsgenerator.nextDouble()*6);
	}

}

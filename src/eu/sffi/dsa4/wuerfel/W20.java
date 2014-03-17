/**
 * 
 */
package eu.sffi.dsa4.wuerfel;

import java.util.Random;

/**
 * @author Andi Popp
 *
 */
public class W20 extends Wuerfel{

	/**
	 * Konstruiert einen neunen W20
	 */
	public W20(){
		this.zufallsgenerator = new Random();
	}
	
	/**
	 * Konstruiert einen neuen W20 und seeded den 
	 * Zufallsgenerator mit dem angegebenen Seed
	 * @param seed
	 */
	public W20(long seed){
		this.zufallsgenerator = new Random(seed);
	}
	
	@Override
	public byte werfen() {
		return (byte) Math.ceil(this.zufallsgenerator.nextDouble()*20);
	}

}

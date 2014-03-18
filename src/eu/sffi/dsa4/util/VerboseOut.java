package eu.sffi.dsa4.util;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Wrapper for a print stream that can be activated or deactivated and has an optional timestamp
 * @author Andi Popp
 *
 */
public class VerboseOut extends PrintStream {

	public static VerboseOut CONSOLE = new VerboseOut(System.out);
	
	private final PrintStream wrappedPrintStream;
	
	private boolean mute;
	
	private boolean timestamp;
	
	/**
	 * Create an unmuted VerboseOut with activated timestamps
	 * @param out
	 * @param wrappedPrintStream
	 */
	public VerboseOut(PrintStream wrappedPrintStream) {
		super(wrappedPrintStream);
		this.wrappedPrintStream = wrappedPrintStream;
		this.mute = false;
		this.timestamp = true;
	}	
	
	public void mute(){
		this.mute = true;
	}
	
	public void unmute(){
		this.mute = false;
	}
	
	public void activateTimeStamp(){
		this.timestamp = true;
	}
	
	public void deactivateTimeStamp(){
		this.timestamp = false;
	}
	
	@Override
	public void println(){
		if (!this.mute){
			wrappedPrintStream.println();
		}
	}
	@Override
	public void println(String s){
		if (!this.mute){
			if (this.timestamp) {
				SimpleDateFormat simpleDate = new SimpleDateFormat("[yyyy-MM-dd,HH:mm:ss] ");
				wrappedPrintStream.print(simpleDate.format(new Date()));
			}
			wrappedPrintStream.println(s);
		}
	}
	
	@Override
	public void print(String s){
		if (!this.mute){
			if (this.timestamp) {
				SimpleDateFormat simpleDate = new SimpleDateFormat("[yyyy-MM-dd,HH:mm:ss] ");
				wrappedPrintStream.print(simpleDate.format(new Date()));
			}
			wrappedPrintStream.print(s);
		}
	}
	
	public void ammend(String s){
		if (!this.mute){
			wrappedPrintStream.print(s);
		}
	}
	
	public void ammendln(String s){
		if (!this.mute){
			wrappedPrintStream.println(s);
		}
	}
	
}

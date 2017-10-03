import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class QuotaSpy {
	
	public static void main(String[] args) {
		//long startTime = System.nanoTime();

		//************** This is my code "Spy and Notify" works also as DoS *****************
		while(true){
			DataSearcher x = new DataSearcher("acc",178,1);
			ArrayList<InfoHolder> myStuff = x.findAll();
			//myStuff.addAll(y.findAll());
			//myStuff.addAll(z.findAll());
			Filter myFilter = new Filter(myStuff);
			myStuff = myFilter.getArray();
			if(myStuff.get(0).getQuota()>0 ){//|| myStuff.get(1).getQuota()>0|| myStuff.get(2).getQuota()>0){
				System.out.println("FREE!!!!!");
				try {
					alert();
				} catch (LineUnavailableException e) {
					System.out.println("Error: "+e);
				}
			}
			else{
				System.out.println("full");
				myStuff = null;
				myFilter = null;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		//long endTime = System.nanoTime();
		//System.out.println("Took "+(endTime - startTime)/1000000000 + " second(s)");
		
	}
	public static void alert() throws LineUnavailableException{
		byte[] buf = new byte[1];
		AudioFormat af = new AudioFormat( (float )44100, 8, 1, true, false );
		SourceDataLine sdl = AudioSystem.getSourceDataLine( af );
		sdl.open();
		sdl.start();
		for( int i = 0; i < 1000 * (float )44100 / 1000; i++ ) {
			double angle = i / ( (float )44100 / 440 ) * 2.0 * Math.PI;
			buf[ 0 ] = (byte )( Math.sin( angle ) * 100 );
			sdl.write( buf, 0, 1 );
		}
		sdl.drain();
		sdl.stop();
	}
	
}
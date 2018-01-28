import java.util.concurrent.TimeUnit;

import akka.imgutil.hac.imgutil.FileReSize;

public class ResizeDirContents {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String srcdir = "/Users/mohamedameen/amroot/work";
		String destdir = "/Users/mohamedameen/amroot/workout";
		long startTime = System.nanoTime();	
		FileReSize fs = new FileReSize(srcdir,destdir);
		long endTime = System.nanoTime();
		long totTime = endTime - startTime;
		System.out.println("Time to resize "+TimeUnit.NANOSECONDS.toSeconds(totTime) +" seconds" );

	}

}

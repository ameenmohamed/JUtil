import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.thebuzzmedia.imgscalr.Scalr;


public class BatchResize {

	public static void main(String[] args) throws IOException {
		
		String srcdir = "C:\\Amin\\am\\Images";
		String destdir = "C:\\Amin\\am\\Images\\tt";
		File src = new File(srcdir);
		
		if(src.isDirectory()){
			String[] filelist = src.list();
			File flt = new File(destdir);
			if(!flt.exists()){
				flt.mkdir();
			}
		long startTime = System.nanoTime();	
			for (int i = 0; i < filelist.length; i++) {
				String _currfl =srcdir+"/"+ filelist[i];
				File workFl = new File(_currfl);
				if(workFl.isFile()){
					
				String _destFile = destdir + "/" + filelist[i];
				System.out.print("resizing :"+_currfl);
				ImageResizer.createResizedCopy(_currfl, 1550, _destFile);
				System.out.println(".... Done");
				}
				else{
					System.out.println(_currfl +" is Dir");
				}
			}
			
			long endTime = System.nanoTime();
			long totTime = endTime - startTime;
			System.out.println("Time to resize "+TimeUnit.NANOSECONDS.toSeconds(totTime) +" seconds" );
		}
		
	}
	
	
	
	
}

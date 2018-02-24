package akka.imgutil.hac.imgutil;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;




public class ImageResizer {
	
	static ArrayList imageExtentsions = new ArrayList();
	
	static{
		imageExtentsions.add("jpg");
		imageExtentsions.add("jpeg");
		imageExtentsions.add("png");
		imageExtentsions.add("bmp");
		imageExtentsions.add("gif");
		imageExtentsions.add("JPG");
		imageExtentsions.add("JPEG");
	}
	
		public static void createResizedCopy(String insrcFile,int pixel,String _outFile) throws IOException
		{
			long startTime = System.nanoTime();
			File srcFl = new File(insrcFile);
			if(isImage(srcFl)){
			BufferedImage srcImage = ImageIO.read(srcFl);
			// Scale the image using the imgscalr library
			BufferedImage scaledImage = Scalr.resize(srcImage, pixel);
			
			
			
			File outFile = new File(_outFile);
			outFile.getParentFile().mkdir();
			
		//	ImageIO.write(RenderedImage im,String formatName, File output);
			ImageIO.write(scaledImage,"jpg", outFile);
			long endTime = System.nanoTime();
			long diff = endTime - startTime;
			System.out.println("Resize of"+ srcFl.getName()+" completed in : "+TimeUnit.NANOSECONDS.toSeconds(diff) +"  Secs" );
			}
			else {
				System.out.print("Not an Image ignoring....");
			}
		}

		static boolean isImage(File srcFl){
			String ext = FilenameUtils.getExtension(srcFl.getName());
			if(imageExtentsions.contains(ext)){
				return true;
			}
			else
				return false;
			
		}
	
	

}

package akka.imgutil.hac.imgutil;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import akka.imgutil.hac.vo.FileSet;




public class FileReSize extends BaseRecursion {
	
	

	public FileReSize(String loc) {
		super(loc);
		
	}
	public FileReSize(String loc,String destLoc) {
		super(loc,destLoc);
		
	}
	
	
	

	public FileSet getFset() {
		return fset;
	}
	@Override
	public void executeDirTask(File fl) {
		String flName = destdir+File.separator+fl.getName();
		if(!new File(flName).exists())	{
			new File(flName).mkdirs();
		}

		

	}

	@Override
	public void executeFileTask(File fl) {
		if(ImageResizer.isImage(fl)) {
		String parent = fl.getParent();
		String dirName = parent.substring(parent.lastIndexOf("\\")+1,parent.length());
		String _destDir = destdir+File.separator+dirName;
		if(!new File(_destDir).exists()){
			new File(_destDir).mkdirs();
		}
		
		String _destFile = _destDir+File.separator+fl.getName();
		String _currfl = fl.getAbsolutePath();
			fset.addFilePath(_currfl);
			System.out.println("FileName :"+_currfl);
		
		}else {
			System.out.println("not an image b:"+fl.getName());
		}

	}
	
	

}

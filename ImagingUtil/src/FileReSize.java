import java.io.File;
import java.io.IOException;


public class FileReSize extends BaseRecursion {

	public FileReSize(String loc) {
		super(loc);
	}
	public FileReSize(String loc,String destLoc) {
		super(loc,destLoc);
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
		String parent = fl.getParent();
		String dirName = parent.substring(parent.lastIndexOf("\\")+1,parent.length());
		String _destDir = destdir+File.separator+dirName;
		if(!new File(_destDir).exists()){
			new File(_destDir).mkdirs();
		}
		
		String _destFile = _destDir+File.separator+fl.getName();
		String _currfl = fl.getAbsolutePath();
	
		//System.out.print("resizing :"+_currfl);
		try {
			ImageResizer.createResizedCopy(_currfl, 1550, _destFile);
			System.out.println("FileName :"+_currfl);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		System.out.println(".... Done");

	}

}

package akka.imgutil.hac.vo;

import java.util.ArrayList;
import java.util.List;

public class FileManifest {
	
	private String filePath;
	private int  fileOGSizse;
	private int  fileReducedSize;
	private String destPath;
	private long resizeTimeInMills;
	
	public List<FileSet> fileMF = new ArrayList<FileSet>();
	
	
	public void addToFileSet(List<FileSet> _fileMF) {		
		fileMF.addAll(_fileMF);		
	}
	
	public void setFileManifest(FileSet fm) {
		fileMF.add(fm);
	}

	public List<FileSet> getFileMF() {
		return fileMF;
	}

	
	
}

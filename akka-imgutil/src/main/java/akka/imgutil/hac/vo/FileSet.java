package akka.imgutil.hac.vo;

import java.util.ArrayList;
import java.util.List;

public class FileSet {
	
	
	public List<String> filePaths = new ArrayList<String>();
	
	
	public FileSet(List<String> _filePaths) {
		filePaths = _filePaths;
	}

	public FileSet() {
		
	}
	

	public List<String> getFilePaths() {
		return filePaths;
	}

	public void addFilePath(String _currfl) {
		filePaths.add(_currfl);
		
	}
	
	
	

}

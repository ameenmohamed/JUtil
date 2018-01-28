package akka.imgutil.hac.actors;

import java.util.ArrayList;
import java.util.List;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.imgutil.hac.App;
import akka.imgutil.hac.imgutil.ImageResizer;
import akka.imgutil.hac.vo.FileSet;



public class FileResizeActor extends AbstractActor {
	  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	  public static Props props() {
		    return Props.create(FileResizeActor.class);
		  }
	  
	@Override
	public void onReceive(Object message) throws Throwable {
		if(message instanceof FileWorkSet) {
			FileWorkSet work = (FileWorkSet) message;
			List<String> flworkPaths = work.getFlSet().getFilePaths();
			FileSet fSet = new FileSet();
			for(String eachPath : flworkPaths) {
				
				String destFile = eachPath.replace(App.srcdir,App.destdir);
				
				ImageResizer.createResizedCopy(eachPath, 1550, destFile);
				
			}
		
		}
		
		
	}
	
	
	@Override
	public Receive createReceive() {
		  .match(FileWorkSet.class, r -> {
              getSender().tell(new FileSet(r.requestId, lastTemperatureReading), getSelf());
            })
            .build();
	}

	//--------------------------------------------------------
	
	public static final class FileWorkSet {
		FileSet flSet ;
		int startRange;
		int endRange ;


		public FileWorkSet(FileSet _flSet,int start,int end) {
			flSet = _flSet;
			startRange = start;
			endRange = end;

		}

		public FileSet getFlSet() {
			return flSet;
		}

		public int getStartRange() {
			return startRange;
		}

		public int getEndRange() {
			return endRange;
		}


	}
//-----------------------------------
	
	public static final class FileSet {
		public List<String> filePaths = new ArrayList<String>();
			
		public FileSet(List<String> _filePaths) {
			filePaths = _filePaths;
		}
	}
	
	

}

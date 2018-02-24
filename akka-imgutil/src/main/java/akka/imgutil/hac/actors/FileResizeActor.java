package akka.imgutil.hac.actors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.concurrent.TimeUnit;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import akka.imgutil.hac.imgutil.ImageResizer;




public class FileResizeActor extends AbstractActor {
	  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
//	  private final FileWorkSet workset ;
	  
	  private String actorName ;
	  
	  
	  
	  
	  public static Props props(String _actorName) {
		    return Props.create(FileResizeActor.class, () -> new FileResizeActor(_actorName));
		  }
	  
	  
	  
	 public FileResizeActor(String _actorName ) {
		 actorName= _actorName;
	}
	  

	
	private WorkSetResponse getToWork(FileWorkSet fileWorkSet) {
		long startTime = System.currentTimeMillis();
		List<String> al = new ArrayList<String>();
		al.addAll(fileWorkSet.workMap.keySet());
		List<String> keys = al.subList(fileWorkSet.startRange, fileWorkSet.endRange);
		int flCount = 0;
		for (String eachpath : keys) {
			try {
				ImageResizer.createResizedCopy(eachpath, 1550, fileWorkSet.workMap.get(eachpath));
				flCount++;
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		long timetaken = TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - startTime);
		WorkSetResponse resp = new WorkSetResponse(timetaken, flCount);
		
		return resp;
	}
	
	
	


	//--------------------------------------------------------
	
	public static final class FileWorkSet {
		Map<String,String> workMap ;
		int startRange;
		int endRange ;
		

		public FileWorkSet(Map<String,String> _workMap,int start,int end) {
			workMap = _workMap;
			startRange = start;
			endRange = end;

		}


		public int getStartRange() {
			return startRange;
		}

		public int getEndRange() {
			return endRange;
		}


	}
//-----------------------------------
	
	public static final class WorkSetResponse {
		long timeTaken;
		int fileCount;
			
		public WorkSetResponse(long _timeTaken,int _fileCount) {
			timeTaken = _timeTaken;
			fileCount= _fileCount;
		}
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
			.match(FileWorkSet.class, _workset -> {
					getToWork(_workset);
				}).build();
		
		
	}
	
	

}

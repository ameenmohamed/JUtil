package akka.imgutil.hac;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.imgutil.hac.actors.FileResizeActor;
import akka.imgutil.hac.actors.MasterActor;
import akka.imgutil.hac.imgutil.FileReSize;
import akka.imgutil.hac.vo.FileSet;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final String srcdir = "/Users/mohamedameen/amroot/work";
	public static final String destdir = "/Users/mohamedameen/amroot/workout";
	
    public static void main( String[] args )
    {
    	long startTime = System.nanoTime();
    	final  ActorSystem system = ActorSystem.create("ImageCompression");
    
    
    	 
    	 // create the result listener, which will print the result and shutdown the system
  //  	    final ActorRef listener = system.actorOf(new Props(null, Listner.class, null), "listener");
    	    
    	    FileReSize fs = new FileReSize(srcdir,destdir);
    	    FileSet fset = fs.getFset();
    	    int picSize = fset.getFilePaths().size();
    	    
    	    
    	   
 //   	    ActorRef supervisor = system.actorOf(Master.props(), "app-supervisor");
    	    HashMap<String,String> workMap = new HashMap<String,String>();
    	    for(String eachPath: fset.getFilePaths()) {
    	    	 String destFile = eachPath.replace(srcdir, destdir );
    	    		System.out.println(eachPath + "  "+ destFile);
    	    		workMap.put(eachPath, destFile);
    	    }
    	    
    	    
    		
    	    int eachVal = picSize/4;
    	    
    	    
    	    final ActorRef masterActor = system.actorOf(MasterActor.props("MasterActor"),"mainActor");
    	    masterActor.tell(new MasterActor.WorkMapVO(workMap), masterActor);
    	    
    	    
    	    /*final ActorRef actorOne = system.actorOf(FileResizeActor.props("T1"),"thread1");
    	    final ActorRef actorTwo = system.actorOf(FileResizeActor.props("T2"),"thread2");
    	    final ActorRef actorThree = system.actorOf(FileResizeActor.props("T3"),"thread3");
    	    final ActorRef actorFour = system.actorOf(FileResizeActor.props("T4"),"thread4");
    	    
    	    
    	    actorOne.tell(new FileResizeActor.FileWorkSet(workMap,0,54), ActorRef.noSender());
    	    actorTwo.tell(new FileResizeActor.FileWorkSet(workMap,55,108), ActorRef.noSender());
    	    actorThree.tell(new FileResizeActor.FileWorkSet(workMap,109,162), ActorRef.noSender());
    	    
    	    actorFour.tell(new FileResizeActor.FileWorkSet(workMap,163,216), ActorRef.noSender());*/
   		/*int start = 0;
    		int end = eachVal;
    			
    		for (int i = 0; i < 4; i++) {
    			if(i>0) {
    				start =  end+1;
    				end += eachVal;
    			}*/
    			
    	    long endTime = System.nanoTime();
			long diff = endTime - startTime;
	//		System.out.println("******************Resize completed in : "+TimeUnit.NANOSECONDS.toSeconds(diff) +"  Secs" );	
    	    system.terminate();
    	    System.out.println("Terminating system ......");
    		}
    	    
    	    
    	    
    	    
    
}

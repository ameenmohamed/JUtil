package akka.imgutil.hac;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.imgutil.hac.actors.Listner;
import akka.imgutil.hac.actors.Master;
import akka.imgutil.hac.imgutil.FileReSize;
import akka.imgutil.hac.vo.FileSet;
import akka.imgutil.hac.vo.FileWorkSet;
import akka.testkit.TestActors.EchoActor;

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
    	 ActorSystem system = ActorSystem.create("ImageCompression");
    	
    
    	 
    	 // create the result listener, which will print the result and shutdown the system
  //  	    final ActorRef listener = system.actorOf(new Props(null, Listner.class, null), "listener");
    	    
    	    FileReSize fs = new FileReSize(srcdir,destdir);
    	    FileSet fset = fs.getFset();
    	    System.out.println(fset.getFilePaths().size());
    	 
    	   
    	    ActorRef supervisor = system.actorOf(Master.props(), "app-supervisor");
    	    
    	    for(String eachPath: fset.getFilePaths()) {
    	    	 String destFile = eachPath.replace(srcdir, destdir );
    	    		System.out.println(eachPath + "  "+ destFile);
    	    }
    	    
    	    
    	    system.terminate();
    }
}

package akka.imgutil.hac.actors;


import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.imgutil.hac.vo.FileManifest;
import akka.imgutil.hac.vo.FileSet;

public  class Listner extends AbstractActor{
	static public Props props(FileSet fileset, ActorRef printerActor) {
	    return Props.create(Listner.class, () -> new Listner(fileset, printerActor));
	  }
	
	 private final ActorRef printerActor;
	  private FileSet fileset;

	  public Listner(FileSet _fileset, ActorRef _printerActor) {
		  fileset = _fileset;
	    printerActor = _printerActor;
	  }


	@Override
	public Receive createReceive() {
		return receiveBuilder()
		        .match(Greet.class, x -> {
		          printerActor.tell(new Greeting(greeting), getSelf());
		        })
		        .build();
		
	}
}
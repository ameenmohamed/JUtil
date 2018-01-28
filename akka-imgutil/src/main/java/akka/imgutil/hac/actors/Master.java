package akka.imgutil.hac.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import akka.event.Logging;
import akka.event.LoggingAdapter;



public class Master extends AbstractActor  {
	private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
	private final ActorRef listener;

 
	public static Props props() {
	    return Props.create(Master.class);
	  }

	  public void preStart() {
	    log.info("Img Resizze Application started");
	  }

	  @Override
	  public void postStop() {
	    log.info("Img Resizze Application stopped");
	  }

	
    
	public Master(FileWorkSet _fwset, ActorRef listener) {
		fwset = _fwset;
        this.listener = listener;
   
       
      }

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				 .match(FileWorkSet.class, r -> {
		              getSender().tell(new RespondTemperature(r.requestId, lastTemperatureReading), getSelf());
		            })
	            .build();
	}
    //------------------------------------------------------
	
	
	
	

}

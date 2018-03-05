package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.FromConfig;
import com.google.inject.Inject;
import play.libs.akka.InjectedActorSupport;


public class CentralMasterActor extends AbstractActor implements InjectedActorSupport {
    private final ActorRef engineNode;
    private final LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    @Inject
    public CentralMasterActor(ActorSystem system) {
        engineNode = system.actorOf(FromConfig.getInstance().props(), "EngineNode");
    }

    // subscribe to cluster changes
    @Override
    public void preStart() {
        logger.info("CentralMasterImpl started");
    }

    // re-subscribe when restart
    @Override
    public void postStop() {
        logger.info("CentralMasterImpl stopped");
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .matchEquals("Hello", s -> {
                    logger.info("Message: " + s);
                    engineNode.tell(s, getSelf());
                })
                .matchEquals("World", s -> {
                    logger.info("Message: " + s);
                })
                .build();
    }

}

package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import static akka.cluster.ClusterEvent.initialStateAsEvents;

public class CentralMasterActor extends AbstractActor {
    public static final String ACTOR_NAME = "CentralMasterActor";

    public static Props getProps() {
        return Props.create(CentralMasterActor.class);
    }

    // private final Cluster cluster = Cluster.get(getContext().getSystem());

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

//    // subscribe to cluster changes
//    @Override
//    public void preStart() {
//        log.info("CentralMasterImpl started");
//        cluster.subscribe(getSelf(), initialStateAsEvents(), ClusterEvent.MemberEvent.class, ClusterEvent.UnreachableMember.class);
//    }
//
//    // re-subscribe when restart
//    @Override
//    public void postStop() {
//        log.info("CentralMasterImpl stopped");
//        cluster.unsubscribe(getSelf());
//    }

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .matchEquals("Hello", s -> log.info("Catch any event: " + s))
                .build();
    }

}

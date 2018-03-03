package actors;

import akka.actor.*;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import static akka.cluster.ClusterEvent.initialStateAsEvents;

public class CentralMaster extends AbstractActor {
    public static final String ACTOR_NAME = "CentralMaster";

    public static Props getProps() {
        return Props.create(CentralMaster.class);
    }

    private final Cluster cluster = Cluster.get(getContext().getSystem());

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    // subscribe to cluster changes
    @Override
    public void preStart() {
        log.info("CentralMaster started");
        cluster.subscribe(getSelf(), initialStateAsEvents(), MemberEvent.class, UnreachableMember.class);
    }

    // re-subscribe when restart
    @Override
    public void postStop() {
        log.info("CentralMaster stopped");
        cluster.unsubscribe(getSelf());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(o -> log.info("Catch any event: " + o))
                .build();
    }
}

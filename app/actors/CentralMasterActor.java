package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorSystem;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent.MemberUp;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.google.inject.Inject;
import play.libs.akka.InjectedActorSupport;
import protocols.MetricProtocol;

public class CentralMasterActor extends AbstractActor implements InjectedActorSupport {
    private final Cluster cluster;
    private final LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    @Inject
    public CentralMasterActor(ActorSystem system) {
        this.cluster = Cluster.get(system);
    }

    @Override
    public void preStart() {
        logger.info("Central started");
        cluster.subscribe(getSelf(), MemberUp.class);
    }

    @Override
    public void postStop() {
        logger.info("Central stopped");
        cluster.unsubscribe(getSelf());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(MemberUp.class, mUp -> logger.info("MemberUp: " + mUp.member()))
                .match(MetricProtocol.class, metricProtocol -> logger.info(metricProtocol.toString()))
                .matchEquals("GetStatisticService", s -> logger.info("GetStatisticService"))
                .build();
    }
}

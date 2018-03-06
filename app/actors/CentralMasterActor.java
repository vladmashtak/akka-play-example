package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent.MemberUp;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.FromConfig;
import akka.util.Timeout;
import com.google.inject.Inject;
import play.libs.akka.InjectedActorSupport;
import scala.concurrent.duration.Duration;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static akka.pattern.PatternsCS.ask;
import static akka.pattern.PatternsCS.pipe;

public class CentralMasterActor extends AbstractActor implements InjectedActorSupport {
    private final ActorSystem system;
    private final ActorRef engineNode;
    private final Cluster cluster;
    private final LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);
    private final Timeout timeout = new Timeout(Duration.create(15, TimeUnit.SECONDS));

    @Inject
    public CentralMasterActor(ActorSystem s) {
        system = s;
        cluster = Cluster.get(system);
        engineNode = system.actorOf(FromConfig.getInstance().props(), "EngineNode");
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
                .match(MemberUp.class, mUp -> {
                    logger.info("MemberUp: " + mUp.member().toString());
                })
                .matchEquals("GetStatisticService", s -> {
                    CompletionStage<Object> result = ask(engineNode, s, timeout);

                    pipe(result, system.dispatcher()).to(getSender());
                })
                .build();
    }
}

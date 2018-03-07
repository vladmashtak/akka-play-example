package services;

import akka.actor.ActorRef;
import akka.util.Timeout;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import scala.concurrent.duration.Duration;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static akka.pattern.PatternsCS.ask;

@Singleton
public class CentralMasterImpl implements CentralMaster {
    private final ActorRef centralActor;
    private final ActorRef engineActor;
    private final Timeout timeout = new Timeout(Duration.create(30, TimeUnit.SECONDS));

    @Inject
    public CentralMasterImpl(@Named("CentralMasterActor") ActorRef centralActor,
                             @Named("EngineNodeActor") ActorRef engineActor) {
        this.centralActor = centralActor;
        this.engineActor = engineActor;
    }

    @Override
    public CompletionStage<Object> getSessionTraffic() {
        return ask(engineActor, "GetStatisticService", timeout);
    }
}

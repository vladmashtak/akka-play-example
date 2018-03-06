package services;

import akka.actor.ActorRef;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.util.concurrent.CompletionStage;

import static akka.pattern.PatternsCS.ask;

@Singleton
public class CentralMasterImpl implements CentralMaster {
    private ActorRef centralActor;

    @Inject
    public CentralMasterImpl(@Named("CentralMasterActor") ActorRef centralActor) {
        this.centralActor = centralActor;
    }

    @Override
    public CompletionStage<Object> getSessionTraffic() {
        return ask(centralActor, "GetStatisticService", 15000);
    }
}

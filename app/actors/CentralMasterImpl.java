package actors;

import akka.actor.ActorRef;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class CentralMasterImpl implements CentralMaster {
    private ActorRef centralMaster;

    @Inject
    public CentralMasterImpl(@Named("CentralMasterActor") ActorRef centralMaster) {
        this.centralMaster = centralMaster;
    }

    @Override
    public void getData() {
        centralMaster.tell("Hello", ActorRef.noSender());
    }
}

package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CentralMasterImpl implements CentralMaster {
    private ActorRef centralMaster;

    public CentralMasterImpl() {
        ActorSystem system = ActorSystem.apply("application");
        // centralMaster.tell(new GetData(), ActorRef.noSender());
        centralMaster = system.actorOf(CentralMasterActor.getProps(), CentralMasterActor.ACTOR_NAME);
        System.out.println(centralMaster);
        centralMaster.tell("Hello", ActorRef.noSender());
    }

    @Override
    public ActorRef getCentralMater() {
        return centralMaster;
    }
}

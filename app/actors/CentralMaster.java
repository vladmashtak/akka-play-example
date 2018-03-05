package actors;

import akka.actor.ActorRef;
import com.google.inject.ImplementedBy;

@ImplementedBy(CentralMasterImpl.class)
public interface CentralMaster {

    ActorRef getCentralMater();
}

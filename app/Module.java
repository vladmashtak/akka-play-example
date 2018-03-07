import actors.CentralMasterActor;
import akka.actor.Actor;
import akka.routing.FromConfig;
import com.google.inject.AbstractModule;
import play.libs.akka.AkkaGuiceSupport;

public class Module extends AbstractModule implements AkkaGuiceSupport {
    @Override
    protected void configure() {
        bindActor(CentralMasterActor.class, "CentralMasterActor");
        bindActor(Actor.class, "EngineNodeActor", FromConfig.getInstance()::props);
    }
}

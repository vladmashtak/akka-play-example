import actors.CentralMasterActor;
import com.google.inject.AbstractModule;
import play.libs.akka.AkkaGuiceSupport;

public class Module extends AbstractModule implements AkkaGuiceSupport {
    @Override
    protected void configure() {
        bindActor(CentralMasterActor.class, "CentralMasterActor");
    }
}

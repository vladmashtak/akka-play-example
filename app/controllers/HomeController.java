package controllers;

import actors.CentralMaster;
import actors.CentralMasterActor;
import actors.CentralMasterImpl;
import akka.actor.*;
import akka.routing.FromConfig;
import akka.util.Timeout;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.mvc.*;
import scala.concurrent.duration.Duration;


import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

@Singleton
public class HomeController extends Controller {
    // private ActorRef engineNode;
    // private final Timeout timeout = new Timeout(Duration.create(1, TimeUnit.SECONDS));
    private CentralMaster central;

    @Inject
    public HomeController(CentralMaster central) {
        this.central = central;

        // engineNode = system.actorOf(FromConfig.getInstance().props(), "engineNode");
    }

    public Result getData() {
        // engineNode.tell("test", ActorRef.noSender());

        return ok(views.html.index.render());
    }

    public Result index() {
        return ok(views.html.index.render());
    }

}

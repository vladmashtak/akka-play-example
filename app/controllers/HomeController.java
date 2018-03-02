package controllers;

import actors.CentralMaster;
import actors.CentralMasterProtocol.*;
import akka.actor.*;
import akka.util.Timeout;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import play.mvc.*;
import scala.compat.java8.FutureConverters;
import scala.concurrent.duration.Duration;


import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

@Singleton
public class HomeController extends Controller {

    private ActorRef centralMaster;
    private final Timeout timeout = new Timeout(Duration.create(1, TimeUnit.SECONDS));

    @Inject
    public HomeController() {
        final Config config = ConfigFactory.load("application.conf");

        ActorSystem system = ActorSystem.create("application", config);

        centralMaster = system.actorOf(CentralMaster.getProps(), CentralMaster.ACTOR_NAME);
    }

    public Result getData() {
/*        return FutureConverters.toJava(ask(centralMaster, new GetData(), timeout))
                .thenApply(response -> ok((String) response));*/

        centralMaster.tell(new GetData(), ActorRef.noSender());

        return ok(views.html.index.render());
    }

    public Result index() {
        return ok(views.html.index.render());
    }

}

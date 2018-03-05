package controllers;

import actors.CentralMaster;
import akka.actor.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import play.mvc.*;

@Singleton
public class HomeController extends Controller {
    private final CentralMaster central;

/*    @Inject
    public HomeController(@Named("CentralMasterActor") ActorRef centralMaster) {
        this.сentralMaster = centralMaster;
    }*/

    @Inject
    public HomeController(CentralMaster central) {
        this.central = central;
    }

    public Result getData() {
        central.getData();
        return ok(views.html.index.render());
    }

    public Result index() {
        return ok(views.html.index.render());
    }

}

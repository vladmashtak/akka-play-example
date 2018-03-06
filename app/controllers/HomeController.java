package controllers;

import play.libs.Json;
import services.CentralMaster;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.mvc.*;

import java.util.ArrayList;
import java.util.concurrent.CompletionStage;

@Singleton
public class HomeController extends Controller {
    private final CentralMaster central;

    @Inject
    public HomeController(CentralMaster central) {
        this.central = central;
    }

    public CompletionStage<Result> getData() {
        return central.getSessionTraffic()
                .thenApply(response -> ok(Json.toJson((ArrayList<String>)response)));
    }

    public Result index() {
        return ok(views.html.index.render());
    }

}

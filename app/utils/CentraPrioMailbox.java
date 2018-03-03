package utils;

// import akka.actor.PoisonPill;
import akka.actor.ActorSystem.*;
import akka.dispatch.PriorityGenerator;
import akka.dispatch.UnboundedStablePriorityMailbox;
import com.typesafe.config.Config;

public class CentraPrioMailbox extends UnboundedStablePriorityMailbox {
    // needed for reflective instantiation
    public CentraPrioMailbox(Settings settings, Config config) {
        // Create a new PriorityGenerator, lower prio means more important
        super(new PriorityGenerator() {
            @Override
            public int gen(Object message) {
                if (message.equals("highpriority"))
                    return 0; // 'highpriority messages should be treated first if possible
                else if (message.equals("lowpriority"))
                    return 2; // 'lowpriority messages should be treated last if possible
                else
                    return 1; // By default they go between high and low priority
            }
        });
    }
}

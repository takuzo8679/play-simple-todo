package model;

import org.apache.pekko.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class DbExecutionContext extends CustomExecutionContext {

    @Inject
    public DbExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "database.dispatcher");
    }
}

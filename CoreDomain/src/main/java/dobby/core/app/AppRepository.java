package dobby.core.app;

import dobby.core.Repository;
import dobby.core.communication.Router;
import dobby.core.stakeholder.App;
import dobby.core.user.UserRepository;

import java.util.*;

/**
 * Created by gautierc on 10/12/15.
 */

public class AppRepository implements Repository<UUID, App> {

    private UserRepository userRepo;
    private Router router;
    private List<App> apps;

    public AppRepository(Router router) {
        this.router = router;
        apps = Collections.synchronizedList(new ArrayList<App>());
    }

    public AppRepository(List<App> availableApps, Router router) {
        this(router);
        for (App app : availableApps)
            accept(app);
    }

    public void setUserRepository(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserRepository getUserRepository() {
        return this.userRepo;
    }

    @Override
    public Optional<App> getItemByKey(UUID key) {
        return apps.stream().filter(app -> app.getUUID() == key).findFirst();

    }

    @Override
    public Optional<App> getItemByName(String name) {
        return apps.stream().filter(app -> app.getName() == name).findFirst();
    }

    @Override
    public void accept(App app) {
        if (!exists(app))
            apps.add(app);
    }

    @Override
    public void close(App app) {
        router.appHasLeft(app);
        if (exists(app))
            apps.remove(app);
    }

    private boolean exists(App app) {
        return apps.stream()
                .filter(g -> app.equals(g))
                .findFirst()
                .isPresent();
    }
}
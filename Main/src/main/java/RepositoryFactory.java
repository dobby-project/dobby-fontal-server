import dobby.core.Repository;
import dobby.core.app.AppRepository;
import dobby.core.communication.Router;
import dobby.core.user.UserRepository;

/**
 * Created by gautierc on 20/01/16.
 */
public class RepositoryFactory {

    private static UserRepository userRepo;
    private static AppRepository appRepo;

    public RepositoryFactory(Router router) {
        userRepo = new UserRepository(router);
        appRepo = new AppRepository(router);
        userRepo.setAppRepository(appRepo);
        appRepo.setUserRepository(userRepo);
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }

    public AppRepository getAppRepo() {
        return appRepo;
    }
}

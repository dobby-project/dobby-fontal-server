import dobby.core.Repository;
import dobby.core.app.AppRepository;
import dobby.core.user.UserRepository;

/**
 * Created by gautierc on 20/01/16.
 */
public class RepositoryFactory {

    private static UserRepository userRepo;
    private static AppRepository appRepo;

    public RepositoryFactory() {
        userRepo = new UserRepository();
        appRepo = new AppRepository();
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

package local;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {

    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.load("application.conf");
    }

    String STANDARD_USER_LOGIN = readConfig().getString("users.standard_user.login");
    String STANDARD_USER_PASSWORD = readConfig().getString("users.standard_user.password");
    String BLOCKED_USER_LOGIN = readConfig().getString("users.blocked_user.login");
    String BLOCKED_USER_PASSWORD = readConfig().getString("users.blocked_user.password");

}

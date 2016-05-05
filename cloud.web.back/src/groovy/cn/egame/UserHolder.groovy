package cn.egame

import cn.egame.user.User
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.context.ApplicationContext

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-8
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
class UserHolder {
    private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();
    private static ThreadLocal<GrailsApplication> grailsApplicationThreadLocal = new ThreadLocal<GrailsApplication>();
    private static UserHolder userHolder = new UserHolder();

    private UserHolder() {

    }

    public static UserHolder getInstance() {
        return userHolder;
    }

    public void setApplicationContext(GrailsApplication application) {
        grailsApplicationThreadLocal.set(application);
    }

    public ApplicationContext getMainContext() {
        return grailsApplicationThreadLocal.get().getMainContext();
    }

    public void removeApplicationContext() {
        grailsApplicationThreadLocal.remove();
    }

    public static void setCurrentUser(User user) {
        threadLocal.set(user);
    }

    public static User getCurrentUser() {
        return threadLocal.get();
    }

    public static void removeCurrentUser() {
        threadLocal.remove();
    }
}

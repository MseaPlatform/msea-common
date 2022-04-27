package io.msea.starter.current.user;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class CurrentUserHelper {

    private static InheritableThreadLocal<CurrentUser> CURRENT_USER = new InheritableThreadLocal<>();
    private static InheritableThreadLocal<String> LOCAL_AUTHORIZATION = new InheritableThreadLocal<>();

    public void setCurrentUser(CurrentUser currentUserInfo) {
        CURRENT_USER.set(currentUserInfo);
    }

    public CurrentUser getCurrentUser() {
        return CURRENT_USER.get();
    }

    public void setAuthorization(String authorization){
        LOCAL_AUTHORIZATION.set(authorization);
    }

    public String getAuthorization(){
        return LOCAL_AUTHORIZATION.get();
    }
}

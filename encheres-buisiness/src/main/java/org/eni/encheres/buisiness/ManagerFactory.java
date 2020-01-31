package org.eni.encheres.buisiness;

import org.eni.encheres.buisiness.user.UserManager;

public class ManagerFactory {

    public UserManager getUserManger(){
        return new UserManager();
    }
}

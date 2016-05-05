package cn.egame.filter

import cn.egame.UserHolder
import cn.egame.user.User

import javax.servlet.http.HttpSession

class AuthFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
//                if (params.controller == 'user') return true;
//                HttpSession session = request.getSession(false);
//                if (session == null) {
//                    redirect(controller: 'user', action: 'index');
//                    return false;
//                };
//                return true;
                if (params.controller == 'user') return true;
                HttpSession session = request.getSession(false);
                if (session == null) {
                    UserHolder.removeCurrentUser();
                    redirect(controller: 'user', action: 'index');
                    return false;
                };
                return true;
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}

package cn.egame.user

import cn.egame.UserHolder
import grails.converters.JSON

import javax.servlet.http.HttpSession

/**
 * 用户登录控制类
 * @Copyright play.cn
 *
 * @Project push-sdk-back
 *
 * @Author zx
 *
 * @timer 15-10-28 15:30
 *
 * @Version 3.0
 *
 * @JDK version used 6.0
 *
 */

class UserController {

    /**
     * 用户登录跳转
     * @return
     */
    def index() {
        def model = [
        ];
        render(view: '/user/login', model: model);
    }

    /**
     * 用户登录
     * @return
     */
    def login() {
        def resultMap = [:];
        String username = params.get("username") as String;
        String password = params.get("password") as String;
        if(!username || !password) {
            log.error("参数错误");
            return ;
        }
        try {
            User user = User.findByUsername(username);
            if(user != null && user.password.equals(password)) {
//                session.setAttribute("userName", user);
//                session.setAttribute("userId", user.getId());
                session.setAttribute("USER_IN_SESSION", user);
//                User user = session.getAttribute("USER_IN_SESSION");
                UserHolder.setCurrentUser(user);
                resultMap << [result: true];
                resultMap << [msg: "登录成功！"];
            } else{
                resultMap << [result: false];
                resultMap << [msg: "登录失败！"];
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            resultMap << [result: false];
            resultMap << [msg: "登录失败！"];
        }
        render(resultMap as JSON);
    }

    /**
     * 用户退出
     * @return
     */
    def logout() {
        def resultMap = [:];
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
                resultMap << [result: true];
                resultMap << [msg: "登出成功！"];
            } else{
                resultMap << [result: false];
                resultMap << [msg: "登出失败！"];
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            resultMap << [result: false];
            resultMap << [msg: "登出失败！"];
        }
        render(resultMap as JSON);
    }
}

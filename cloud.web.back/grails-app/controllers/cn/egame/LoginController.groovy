package cn.egame

import cn.egame.user.AclInfo
import cn.egame.user.ResInfo
import cn.egame.user.User
import cn.egame.user.UserRole
import grails.converters.JSON
import org.apache.commons.collections.MultiMap
import org.apache.commons.collections.map.MultiValueMap

import javax.servlet.http.HttpSession

class LoginController {

    /**
     * 用户登录跳转
     * @return
     */
    def index() {
        def model = [
        ];
        render(view: '/user/login', model: model);
    }

    def login() {
//        HttpSession session = request.getSession(false);
        User user = null;
        def resultMap = [:];

        String username = params.get("username") as String;
        String password = params.get("password") as String;
//        if (!username || !password) {
//            log.error("参数错误");
//            return;
//        }
        try {
            user = User.findByUsername(username);
            if (user != null && user.password.equals(password)) {
                session.setAttribute("userName", user);
                resultMap << [result: true];
                resultMap << [msg: "登录成功！"];
            } else {
                resultMap << [result: false];
                resultMap << [msg: "登录失败！"];
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            resultMap << [result: false];
            resultMap << [msg: "登录失败！"];
            render(resultMap as JSON);
        }
        if (user == null) {
            render(resultMap as JSON);
        }
        log.info("用户登录成功-->用户ID：" + user.id + ",姓名：" + user.username);
        MultiMap menuMap = new MultiValueMap();

        //如果有权限，查询用户角色关联的菜单
        List<Long> roleIds = UserRole.executeQuery("SELECT roleId FROM UserRole WHERE id=?", [user.id]);
        List<AclInfo> misAclList = new ArrayList<AclInfo>();
        roleIds.each {
            List<AclInfo> misAcls = AclInfo.findAllByRoleId(it.toString());
            misAclList.addAll(misAcls);
        }
        //不同角色可能会有相同的菜单，一个用户同时有两个角色的时候，去除重复的菜单
        List misMenuList = new ArrayList();
        for (AclInfo misAcl : misAclList) {
            ResInfo misMenu = ResInfo.findById(Long.valueOf(misAcl.resId));
            if (!misMenuList.contains(misMenu)) {
                misMenuList.add(misMenu);
            }
        }
        //根据菜单顺序排序
        OrderFlagCompare orderFlagCompare = new OrderFlagCompare();
        Collections.sort(misMenuList, orderFlagCompare);
        for (ResInfo misMenu : misMenuList) {
            if (misMenu) {
                menuMap.put(misMenu.parentId.toString(), misMenu);
            }
        }

//        render(view: "index", model: ["menuMap": menuMap]);
        render(view: '/index', model: ["menuMap": menuMap]);
    }
}

package cn.egame.common

import cn.egame.OrderFlagCompare
import cn.egame.user.AclInfo
import cn.egame.user.ResInfo
import cn.egame.user.User
import cn.egame.user.UserRole
import org.apache.commons.collections.MultiMap
import org.apache.commons.collections.map.MultiValueMap

/**
 * SDK管理后台界面跳转
 * @Copyright play.cn
 *
 * @Project push-sdk-back
 *
 * @Author zx
 *
 * @timer 15-10-19 15:30
 *
 * @Version 3.0
 *
 * @JDK version used 6.0
 *
 */
class ChargeIndexController {

    def index() {
//        def model = [
//                former: params.get("former")
//        ]
//        render(view: "/basic/index", model: model);

        User user = session.getAttribute("USER_IN_SESSION");
//        User user = UserHolder.getCurrentUser();
        if(user==null){
            redirect(controller: 'user', action: 'index');
        }
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

    def indexSdk() {
        def model = [
        ]
        render(view: "/charge/list_charge", model: model);
    }

    def indexMemory() {
        def model = [
        ]
        render(view: "/memory/list_memory", model: model);
    }

    def indexApp() {
        def model = [
        ]
        render(view: "/memory/list_app", model: model);
    }

    def indexAdvert() {
        def model = [
        ]
        render(view: "/memory/list_advert", model: model);
    }

    def indexPkg() {
        def model = [
        ]
        render(view: "/pkg/list_pkg", model: model);
    }

    def indexPreGame() {
        def model = [
        ]
        render(view: "/preMemory/list_game", model: model);
    }

    def indexPreApp() {
        def model = [
        ]
        render(view: "/preMemory/list_app", model: model);
    }

    def indexPreAdvert() {
        def model = [
        ]
        render(view: "/preMemory/list_advert", model: model);
    }
}

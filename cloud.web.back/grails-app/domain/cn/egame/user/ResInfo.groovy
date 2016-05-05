package cn.egame.user

class ResInfo implements Serializable {

    static mapping = {
        table 't_res_info'
        id column: 'res_id'
        menuName column: 'menu_name'
        menuUrl column: 'menu_url'
        param column: 'param'
        parentId column: 'parent_id'
        treeLevel column: 'tree_level'
        isBranch column: 'is_branch'
        createUser column: 'create_user'
        createTime column: 'create_time'
        updateUser column: 'update_user'
        updateTime column: 'update_time'
        recordStatus column: 'record_status'
        orderFlag column: 'order_flag'
        menuType column: 'menu_type'
        version false
    }

    static constraints = {
        menuName nullable: true
        menuUrl nullable: true
        param nullable: true
        parentId nullable: true
        treeLevel nullable: true
        isBranch nullable: true
        createUser nullable: true
        createTime nullable: true
        updateUser nullable: true
        updateTime nullable: true
        recordStatus nullable: true
        orderFlag nullable: true
        menuType nullable: true
    }

    Long id;
    String menuName;
    String menuUrl;
    String param;
    Long parentId;
    Long treeLevel;
    Long isBranch;
    Long createUser;
    Long createTime;
    Long updateUser;
    Long updateTime;
    Long recordStatus;
    Integer orderFlag;
    Integer menuType;

}

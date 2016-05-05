package cn.egame.user

class AclInfo implements Serializable {

    static mapping = {
        table 't_acl_info'
        id column: 'id'
        roleId column: 'role_id'
        resId column: 'res_id'
        createUser column: 'create_user'
        createTime column: 'create_time'
        updateUser column: 'update_user'
        updateTime column: 'update_time'
        version false
    }

    static constraints = {
        roleId nullable: true
        resId nullable: true
        createUser nullable: true
        createTime nullable: true
        updateUser nullable: true
        updateTime nullable: true
    }

    Long id;
    Long roleId;
    Long resId;
    Long createUser;
    Long createTime;
    Long updateUser;
    Long updateTime;
}

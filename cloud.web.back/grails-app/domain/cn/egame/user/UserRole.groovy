package cn.egame.user

class UserRole implements Serializable {

    static mapping = {
        table 't_user_role'
        id column: 'user_id'
        roleId column: 'role_id'
        version false
    }

    static constraints = {
        id nullable: true
        roleId nullable: true
    }

    Long id;
    Long roleId;
}

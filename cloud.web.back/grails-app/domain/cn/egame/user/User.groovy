package cn.egame.user

/**
 * 用户类
 * @Copyright play.cn
 *
 * @Project push-sdk-back
 *
 * @Author zx
 *
 * @timer 15-10-28 10:52
 *
 * @Version 3.0
 *
 * @JDK version used 6.0
 *
 */

class User implements Serializable {

    static mapping = {
        table 't_sdk_user'
        id column: 'id'
        username column: 'username'
        password column: 'password'
        insertTime column: 'insert_time'
        updateTime column: 'update_time'
        version false
    }

    static constraints = {
        username nullable: true
        password nullable: true
        insertTime nullable: true
        updateTime nullable: true
    }

    Long id;

    String username;

    String password;

    Long insertTime;

    Long updateTime;

}

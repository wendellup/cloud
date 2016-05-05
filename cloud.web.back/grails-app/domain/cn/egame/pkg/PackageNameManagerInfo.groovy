package cn.egame.pkg

class PackageNameManagerInfo {

    static mapping = {
        table 't_package_name_manager'
        id column: 'id'
        packageName column: 'package_name'
        insertTime column: 'insert_time'
        updateTime column: 'update_time'
        version false
    }

    static constraints = {
        insertTime nullable: true
        updateTime nullable: true
    }

    Long id;
    String packageName;
    Long insertTime;
    Long updateTime;
}

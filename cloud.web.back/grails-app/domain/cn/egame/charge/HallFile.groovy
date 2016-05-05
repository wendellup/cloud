package cn.egame.charge

/**
 * SDK升级信息
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

class HallFile {

    static mapping = {
        table 't_hall_filev2'
        id column: 'file_id'
        fileName column: 'file_name'
        fileSize column: 'file_size'
        downloadPath column: 'download_path'
        introduction column: 'introduction'
        isForced column: 'is_forced'
        remark column: 'remark'
        operatorId column: 'operator_id'
        insertTime column: 'insert_time'
        updateTime column: 'update_time'
        versionCode column: 'version'
        clientId column: 'client_id'
        efsId column: 'efs_id'
        md5 column: 'md5'
        version false
    }

    static constraints = {
        introduction nullable: true
        remark nullable: true
        efsId nullable: true
        md5 nullable: true
    }

    Integer id;   //文件编号 唯一标示
    String fileName; //文件名称
    Integer fileSize;  //文件大小
    String downloadPath; //下载路径
    String introduction; //描述
    Integer isForced;  //是否强制升级(手游大厅已废弃)
    String remark;  //备注
    Integer operatorId;  //操作员
    Date insertTime;  //1970-01-01 00:00:00' COMMENT '上传时间'
    Date updateTime;  //1970-01-01 00:00:00' COMMENT '更新时间'
    String versionCode;  //sdk版本号
    Integer clientId;  //默认为0
    Long efsId;
    String md5;
}

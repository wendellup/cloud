package cn.egame.charge

/**
 * Created by zx on 15-10-20.
 */
class HallFileVo {
    Integer id;   //文件编号 唯一标示
    String fileName; //文件名称
    Integer fileSize;  //文件大小
    String downloadPath; //下载路径
    String realDownloadPath;  //下载决定路径
    String introduction; //描述
    Integer isForced;  //是否强制升级(手游大厅已废弃)
    String remark;  //备注
    Integer operatorId;  //操作员
    String insertTime;  //1970-01-01 00:00:00' COMMENT '上传时间'
    String updateTime;  //1970-01-01 00:00:00' COMMENT '更新时间'
    String versionCode;  //sdk版本号
    Integer clientId;  //默认为0
    Long efsId;
    String md5;
}

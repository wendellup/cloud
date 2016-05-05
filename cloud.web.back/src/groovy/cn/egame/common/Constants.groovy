package cn.egame.common

/**
 * Created by zx on 15-10-20.
 */
class Constants {
    static final int PAGE_SIZE = 10;
    static final int PAY_SDK_APP_ID = 8888060  //支付SDK的app id
    static final long LOGIN_USER_ID = 0;       //用户id
    static final String DOWNLOAD_URL = "http://192.168.251.53:8103/f/m/";  //文件系统下载路径
    static final String USER_IN_SESSION = "USER_IN_SESSION";
    def static imageSize = [:];

    static {
        //循环广告位图片规格
//    imageSize << [1: [522, 225, null, null]];
        //推送icon图片规格
        imageSize << [2: [150, 150, null, null]];
    }


}

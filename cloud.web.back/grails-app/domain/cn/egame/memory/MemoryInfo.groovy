package cn.egame.memory

/**
 * 常驻内存配置
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

class MemoryInfo {

    static mapping = {
        table 't_memory'
        id column: 'id'
        switchType column: 'switch_type'
        advertType column: 'advert_type'
        filePath column: 'file_path'
        startTime column: 'start_time'
        endTime column: 'end_time'
        title column: 'title'
        description column: 'description'
        linkUrl column: 'link_url'
        insertTime column: 'insert_time'
        updateTime column: 'update_time'
        operatorId column: 'operator_id'
        status column: 'status'
        layOut column: 'lay_out'
        hasBgcolor column: 'has_bgcolor'
        delStatus column: 'del_status'
        appId column: 'app_id'
        gameName column: 'game_name'
        gameIcon column: 'game_icon'
        gameDownloadUrl column: 'game_download_url'
        gameSize column: 'game_size'
        packageName column: 'package_name'
        imsiNum column: 'imsi_num'
        flag column: 'flag'
        remark column: 'remark'
        silentDownload column: 'silent_download'
        objType column : 'obj_type'
        ownName column: 'own_name'
        pricingWay column: 'pricing_way'
        activationCondition column: 'activation_condition'
        recyclingPrice column: 'recycling_price'
        effectImsiNum column: 'effect_imsi_num'
        effectTime column: 'effect_time'
        effectFlag column: 'effect_flag'
        version false
    }

    static constraints = {
        switchType nullable: true
        advertType nullable: true
        filePath nullable: true
        startTime nullable: true
        endTime nullable: true
        title nullable: true
        description nullable: true
        linkUrl nullable: true
        insertTime nullable: true
        updateTime nullable: true
        operatorId nullable: true
        status nullable: true
        layOut nullable: true
        hasBgcolor nullable: true
        delStatus nullable: true
        appId nullable: true
        gameName nullable: true
        gameIcon nullable: true
        gameDownloadUrl nullable: true
        gameSize nullable: true
        packageName nullable: true
        imsiNum nullable: true
        flag nullable: true
        remark nullable: true
        silentDownload nullable: true
        objType nullable: true
        ownName nullable: true
        pricingWay nullable: true
        activationCondition nullable: true
        recyclingPrice nullable: true
        effectImsiNum nullable: true
        effectTime nullable: true
        effectFlag nullable: true
    }

    Long id;
    Integer switchType;
    Integer advertType;
    String filePath;
    Long startTime;
    Long endTime;
    String title;
    String description;
    String linkUrl;
    Long insertTime;
    Long updateTime;
    Long operatorId;
    Integer status;
    Integer layOut;
    Integer hasBgcolor;
    Integer delStatus;
    Integer appId;
    String gameName;
    String gameIcon;
    String gameDownloadUrl;
    Integer gameSize;
    String packageName;
    Integer imsiNum;
    Integer flag;
    String remark;
    Integer silentDownload;
    Integer objType;
    String ownName;
    String pricingWay;
    String activationCondition;
    Double recyclingPrice;
    Integer effectImsiNum;
    Long effectTime;
    Integer effectFlag;

}

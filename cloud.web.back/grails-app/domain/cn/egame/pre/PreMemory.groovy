package cn.egame.pre

/**
 * 常驻内存预配置
 * @Copyright play.cn
 *
 * @Project push-sdk-back
 *
 * @Author yuchao
 *
 * @timer 16-3-16 16:16
 *
 * @JDK version used 6.0
 *
 */
class PreMemory {

    static mapping = {
        table 't_pre_memory'
        id column: 'id'
        startTime column: 'start_time'
        endTime column: 'end_time'
        title column: 'title'
        description column: 'description'
        linkUrl column: 'link_url'
        insertTime column: 'insert_time'
        updateTime column: 'update_time'
        operatorId column: 'operator_id'
        status column: 'status'
        delStatus column: 'del_status'
        gameName column: 'game_name'
        gameIcon column: 'game_icon'
        gameDownloadUrl column: 'game_download_url'
        gameSize column: 'game_size'
        packageName column: 'package_name'
        imsiNum column: 'imsi_num'
        remark column: 'remark'
        objType column: 'obj_type'
        ownName column: 'own_name'
        pricingWay column: 'pricing_way'
        activationCondition column: 'activation_condition'
        recyclingPrice column: 'recycling_price'
        advertType column: 'advert_type'
        flag column: 'flag'
        silentDownload column: 'silent_download'
        version false
    }

    static constraints = {
        startTime nullable: true
        endTime nullable: true
        title nullable: true
        description nullable: true
        linkUrl nullable: true
        insertTime nullable: true
        updateTime nullable: true
        operatorId nullable: true
        status nullable: true
        delStatus nullable: true
        gameName nullable: true
        gameIcon nullable: true
        gameDownloadUrl nullable: true
        gameSize nullable: true
        packageName nullable: true
        imsiNum nullable: true
        remark nullable: true
        objType nullable: true
        ownName nullable: true
        pricingWay nullable: true
        activationCondition nullable: true
        recyclingPrice nullable: true
        advertType nullable: true
        flag nullable: true
        silentDownload nullable: true
    }

    Long id;
    Long startTime;
    Long endTime;
    String title;
    String description;
    String linkUrl;
    Long insertTime;
    Long updateTime;
    Long operatorId;
    Integer status;
    Integer delStatus;
    String gameName;
    String gameIcon;
    String gameDownloadUrl;
    Integer gameSize;
    String packageName;
    Integer imsiNum;
    String remark;
    Integer objType;
    String ownName;
    String pricingWay;
    String activationCondition;
    Double recyclingPrice;
    Integer advertType;
    Integer flag;
    Integer silentDownload;
}

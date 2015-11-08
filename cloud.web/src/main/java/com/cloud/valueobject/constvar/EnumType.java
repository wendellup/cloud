package com.cloud.valueobject.constvar;

import java.util.TreeMap;

public class EnumType {
	public enum TagType {
		none(0, "默认值"), articleType(1, "文章类型");
        int value = 0;
        String message = "";

        public String getMessage() {
            return this.message;
        }

        private static TreeMap<Integer, TagType> _map;

        static {
            _map = new TreeMap<Integer, TagType>();
            for (TagType num : TagType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static TagType lookup(int value) {
            return _map.get(new Integer(value));
        }

        TagType(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public int value() {
            return this.value;
        }
    }
	
	
    public enum VcType {
        newTopicAndClassification(1, "新专题,分类用自定义分类替换游戏包"), choiceness(2, "7.4.2精选,隐藏"), gameUpdateType(4, "游戏更新兼容");

        int _value = 0;
        String _message = "";

        public String getMessage() {
            return this._message;
        }

        private static TreeMap<Integer, VcType> _map;

        static {
            _map = new TreeMap<Integer, VcType>();
            for (VcType num : VcType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static VcType lookup(int value) {
            return _map.get(new Integer(value));
        }

        VcType(int value, String message) {
            this._value = value;
            this._message = message;
        }

        public int value() {
            return this._value;
        }
    }

    // 短信计费类型
    public enum SMSType {
        free(0), telecom(1), unicom(2), mobile(4), all(7);
        @SuppressWarnings("unused")
        private int value;

        SMSType(int value) {
            this.value = value;
        }
    }

    public enum AdvLayoutType {
        // 1 滚动 2 双屏 3 单一 4 文字类布局广告
        roll(1),
        doub(2),
        single(3),
        word(4);

        @SuppressWarnings("unused")
        private int value;

        AdvLayoutType(int value) {
            this.value = value;
        }

    }

    public enum FilterType {
        black(1), white(2);

        private int value;

        FilterType(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    public enum AppParameterParamType {
        none(""), is_all_list("is_all_list"), is_first_online_time("is_first_online_time"), // 是否是新品标签，新品补全：最新上架时间补全
        is_net_first_online_time("is_net_first_online_time"), // 是否是网游最新标签，网游最新补全：网游最新上架时间补全
        merge_tag_id("merge_tag_id"), // 游戏推荐，补全逻辑：游戏周排行补全
        merge_net_tag_id("merge_net_tag_id"), // 网游最热：补全逻辑：网游游戏周排行补全
        merge_webgame_tag_id("merge_webgame_tag_id"), // 页游补全
        merge_paydownload_tag_id("merge_paydownload_tag_id"), // 付费精品补全

        pay_code("pay_code"),
        network_type("network_type"),
        is_not_adaptation("is_not_adaptation"), // 是否不做机型适配
        is_adaptation("is_adaptation"), // 是否做机型适配
        ref_app_parameter_id("ref_app_parameter_id"),
        menu_page_type("menu_page_type"), // 页面类型，前端使用
        menu_type("menu_type"), // 业务类型，后端使用
        channel_page_type("channel_page_type"),
        channel_type("channel_type"),
        is_need_play_app("is_need_play_app"), // 是否是必玩应用
        is_show_tags("is_show_tags"), // 该频道下是否显示七种标签
        is_show_network_tags("is_show_network_tags"), // 网游频道(最新最热)下是否显示特定标签

        is_rank_day("is_rank_day"), // 日排行
        is_rank_week("is_rank_week"), // 周排行
        is_rank_month("is_rank_month"), // 月排行
        is_rank_total("is_rank_total"), // 总排行
        is_rank_free("is_rank_free"), // 总排行

        new_status_tag("new_status_tag"), // 最新标签(角标)
        hot_status_tag("hot_status_tag"), // 最热标签(角标)
        first_status_tag("first_status_tag"), // 首发标签(角标)
        classic_status_tag("classic_status_tag"), // 经典标签(角标)
        limitfree_status_tag("limitfree_status_tag"), // 限免标签(角标)
        open_status_tag("open_status_tag"), // 开服标签(角标)
        gift_status_tag("gift_status_tag"), // 礼包标签(角标)

        chinese_status_tag("chinese_status_tag"), // 汉化标签(角标)
        network_status_tag("network_status_tag"), // 网盟标签

        game_quality_fine_tag("game_quality_fine_tag"), // 网游品质标签-精(品质标签)
        game_quality_extreme_tag("game_quality_extreme_tag"), // 网游品质标签-极(品质标签)
        game_quality_god_tag("game_quality_god_tag"), // 网游品质标签-神(品质标签)

        active_status_tag("active_status_tag"), // 活动标签(状态标签)
        game_gift_tag("game_gift_tag"), // 网游状态标签-礼包(状态标签)
        game_new_server_tag("game_new_server_tag"), // 网游状态标签-新服(状态标签)
        game_new_version_tag("game_new_version_tag"), // 网游状态标签-新版(状态标签)
        game_raiders_tag("game_raiders_tag"), // 网游状态标签-攻略(状态标签)
        game_awards_tag("game_awards_tag"), // 网游状态标签-大奖(状态标签)
        game_match_tag("game_match_tag"), // 网游状态标签-比赛(状态标签)
        game_test_tag("game_test_tag"), // 网游状态标签-测试(状态标签)

        game_status_tag("game_status_tag"), // 角标分组表示-角标
        game_quality_tag("game_quality_tag"), // 角标分组表示-品质
        game_mode_tag("game_mode_tag"), // 角标分组表示-状态

        ref_sort_type("ref_sort_type"), // 分类对应的排序规则
        is_not_show_no_apps("is_not_show_no_apps"), // 专题和必玩如果没有配应用则不显示
        is_new_topic("is_new_topic"), // 是否是新的专题
        classification_gamepackage("classification_gamepackage"), // 分类下游戏包
        classification_custom("classification_custom"), // 自定义分类
        channel_tag_id("channel_tag_id"),
        channel_code("channel_code"),
        operation_type("operation_type"),

        // 7.4.3排行需求
        is_rank_hot("is_rank_hot"), // 热门榜
        is_rank_new("is_rank_new"), // 新品榜
        is_rank_all("is_rank_all"), // 总排行

        // 7.4.8新排行榜下新增网游榜
        is_rank_onlinegame("is_rank_onlinegame"), // 网游榜

        // 7.5.1新增搜索权重月排行
        is_rank_month_weight("is_rank_month_weight"),

        is_channel_tag("is_channel_tag"), // 分类下挂的tag
        tv_default_channel_type("tv_default_channel_type"), // 来自tv
        classifiction_all_game("classifiction_all_game"),
        is_ref_channel_games("is_ref_channel_games"), // 7.4.9 区分频道和游戏合集
        is_show_first_sort("is_show_first_sort"), // 7.4.9 是否在首页分类显示
        is_important_tag("is_important_tag"), // 7.4.9 标签是否区别显示
        is_new_user("is_new_user"), // 750个性化推荐是否是新用户
        is_one_time_push("is_one_time_push"), // sdk是否是一次性任务
        is_all_games("is_all_games"), // sdk全部还是部分游戏

        feedback_option_position("feedback_option_position"),
        feedback_option_mutex_ids("feedback_option_mutex_ids");
        String value = "";

        AppParameterParamType(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }

        private static TreeMap<String, AppParameterParamType> _map;

        static {
            _map = new TreeMap<String, AppParameterParamType>();
            for (AppParameterParamType num : AppParameterParamType.values()) {
                _map.put(num.value(), num);
            }
        }

        public static AppParameterParamType lookup(String value) {
            AppParameterParamType obj = _map.get(value);
            if (obj == null) {
                return none;
            }
            return obj;
        }

    }

    public enum AppParameterType {
        all(0),

        channel(1), // 频道
        common(2), // 装机必备
        area(3), // 省份
        operationManageChannel(4), // 运营支撑管理系统菜单
        businessManageChannel(5), // 商务合作管理平台菜单
        screenpx(13), // 分辨率
        os(14), // 操作系统
        terminalGroup(23), // 机型组
        operationType(90), // 操作类型

        /** 7版手游使用 begin */
        v7MobileTreeType(7),
        v7MobileSortType(8),
        v7MobileActiveTagType(31),
        v7MobileNewTagType(32),
        v7MobileHotTagType(33),
        v7MobileFirstTagType(34),
        v7MobileClassicTagType(35),
        v7MobileLimitfreeTagType(36),
        v7MobileOpenTagType(37),
        v7MobileGiftTagType(38);
        /** 7版手游使用 end */

        int value = 0;

        private static TreeMap<Integer, AppParameterType> _map;

        static {
            _map = new TreeMap<Integer, AppParameterType>();
            for (AppParameterType num : AppParameterType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static AppParameterType lookup(int value) {
            return _map.get(new Integer(value));
        }

        AppParameterType(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    public enum FileStatus {
        none(0, "待测试"), testPassed(1, "测试通过"), testNotPassed(2, "测试未通过"), adaptationed(3, "已适配");
        int value = 0;
        String message = "";

        public String getMessage() {
            return this.message;
        }

        private static TreeMap<Integer, FileStatus> _map;

        static {
            _map = new TreeMap<Integer, FileStatus>();
            for (FileStatus num : FileStatus.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static FileStatus lookup(int value) {
            return _map.get(new Integer(value));
        }

        FileStatus(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public int value() {
            return this.value;
        }
    }

    public enum GameRankingKeyWordType {
        none(0), day(1), week(2), month(4), total(8);
        int _value = 0;

        private static TreeMap<Integer, GameRankingKeyWordType> _map;

        static {
            _map = new TreeMap<Integer, GameRankingKeyWordType>();
            for (GameRankingKeyWordType num : GameRankingKeyWordType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static GameRankingKeyWordType lookup(int value) {
            return _map.get(new Integer(value));
        }

        GameRankingKeyWordType(int value) {
            this._value = value;
        }

        public int value() {
            return this._value;
        }
    }

    public enum GameSortType {
        none(0), is_mouse(1), is_keyboard(2), is_remote_control(3), is_handle(4), is_sense(5), publish_time(6);

        int value = 0;
        private static TreeMap<Integer, GameSortType> _map;

        static {
            _map = new TreeMap<Integer, GameSortType>();
            for (GameSortType num : GameSortType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static GameSortType lookup(int value) {
            return _map.get(new Integer(value));
        }

        GameSortType(int value) {
            this.value = value;
        }

        public int get(String name) {
            return this.value;
        }

        public int value() {
            return this.value;
        }

    }

    public enum GameStatus {
        none(-1, "无状态"),
        toAudit(0, "待审核"),
        auditNotPassed(1, "审核未通过"),
        deleted(2, "已删除"),
        auditPassed(3, "已审核"),
        testPassed(4, "测试通过"),
        published(5, "已发布"),
        business(6, "商用"),
        offLine(7, "已下线"),
        test63(100, "测试63");

        int _value = 0;
        String _message = "";

        public String getMessage() {
            return this._message;
        }

        private static TreeMap<Integer, GameStatus> _map;

        static {
            _map = new TreeMap<Integer, GameStatus>();
            for (GameStatus num : GameStatus.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static GameStatus lookup(int value) {
            return _map.get(new Integer(value));
        }

        GameStatus(int value, String message) {
            this._value = value;
            this._message = message;
        }

        public int value() {
            return this._value;
        }
    }

    // searchTag(1):(TV and 手游)管理平台录入的搜索关键字
    // gameParameterAppTag(2): 跟菜单表关联
    // in_tag(3): TV用，“hot、new”标签
    // shop_search_tag(4):彩蛋用
    // game_label_tag(5): 现不用
    // systemAutoSearchTag(6): 定时任务计算用户输入的搜索关键词数量的前50个，为此标签类型
    // first_searchTag(7): 首屏标签（换一批关键词功能，不变的标签），对应searchTag
    // first_systemAutoSearchTag(8):首屏标签，对应systemAutoSearchTag
    // channel_tag(16): 精选对应的tag(精选标签)
    // search_input_tag(32): 搜索框对应的tag(搜索默认关键词)
    public enum GameTagType {
        none(0),
        searchTag(1),
        gameParameterAppTag(2),
        in_tag(3),
        shop_search_tag(4),
        game_label_tag(5),
        systemAutoSearchTag(6),
        first_searchTag(7),
        first_systemAutoSearchTag(8),
        channel_tag(16),
        search_input_tag(32),
        tv_searchTag(128);

        int value = 0;

        private static TreeMap<Integer, GameTagType> _map;

        static {
            _map = new TreeMap<Integer, GameTagType>();
            for (GameTagType num : GameTagType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static GameTagType lookup(int value) {
            return _map.get(new Integer(value));
        }

        GameTagType(int value) {
            this.value = value;
        }

        public int get(String name) {
            return this.value;
        }

        public int value() {
            return this.value;
        }
    }

    public enum GameType {
        none(0),
        tv(1),
        mobile(2),
        itv(4),
        web(8),
        wap(16),
        shop(32),
        sns(64),
        shopWallpaper(128),
        app(256),
        distinguish_app(512),
        distinguish_game(1024);
        int _value = 0;

        private static TreeMap<Integer, GameType> _map;

        static {
            _map = new TreeMap<Integer, GameType>();
            for (GameType num : GameType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static GameType lookup(int value) {
            return _map.get(new Integer(value));
        }

        GameType(int value) {
            this._value = value;
        }

        public int value() {
            return this._value;
        }
    }

    public enum NetworkType {
        none(0, "未分类"), offlineGame(11, "单机游戏"), onlineGame(12, "联网游戏"), webGame(20, "网页游");
        int value = 0;
        String message = "";

        private static TreeMap<Integer, NetworkType> _map;

        static {
            _map = new TreeMap<Integer, NetworkType>();
            for (NetworkType num : NetworkType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static NetworkType lookup(int value) {
            return _map.get(new Integer(value));
        }

        NetworkType(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public int value() {
            return this.value;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public static class ProductType {
        public static int none = 0;
        public static int recommend = 2;
        public static int newGame = 3;
        public static int chessGame = 4;
        public static int senseGame = 5;
        public static int freeGame = 6;
        public static int onlineGame = 7;
        public static int allLikeGame = 49;
        public static int searchRecommendGame = 50;
    }

    public enum SysParameterType {
        INT_CHANNEL_IS_ALL_MAX_RECORDS("INT_CHANNEL_IS_ALL_MAX_RECORDS"),
        CARD_COLLECTION_BEGIN_ROW("card_collection_begin_row"),
        CARD_COLLECTION_INTERVAL_ROW("card_collection_interval_row"),
        AB_UPGRADE_BASE_VERSION("ab_upgrade_base_version"),
        AB_UPGRADE_("ab_upgrade_"),
        is_show_recommend_games("is_show_recommend_games"), // 750个性推荐开关
        TV_SDK_VERSION("tv_sdk_version");// 写入tv计费文件的特定版本

        String value = "";

        SysParameterType(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public enum TerminalType {
        none(0), tv(1), phone(2), itv(4);
        int _value = 0;

        private static TreeMap<Integer, TerminalType> _map;

        static {
            _map = new TreeMap<Integer, TerminalType>();
            for (TerminalType num : TerminalType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static TerminalType lookup(int value) {
            return _map.get(new Integer(value));
        }

        TerminalType(int value) {
            this._value = value;
        }

        public int value() {
            return this._value;
        }
    }

    public enum WapType {
        none(0), simple(1), color(2), threeG(4);

        int _value = 0;

        private static TreeMap<Integer, WapType> _map;

        static {
            _map = new TreeMap<Integer, WapType>();
            for (WapType num : WapType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static WapType lookup(int value) {
            return _map.get(new Integer(value));
        }

        WapType(int value) {
            this._value = value;
        }

        public int value() {
            return this._value;
        }
    }

    public enum FeeType {
        feeSDK(1, "计费SDK"), feeSMS(2, "短信计费"), free(3, "免费"), rate(-1, "免费试玩");

        int _value = 0;
        String _message = "";

        public String getMessage() {
            return this._message;
        }

        private static TreeMap<Integer, FeeType> _map;

        static {
            _map = new TreeMap<Integer, FeeType>();
            for (FeeType num : FeeType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static FeeType lookup(int value) {
            FeeType type = _map.get(new Integer(value));
            if (type == null) {
                return rate;
            }
            return type;
        }

        FeeType(int value, String message) {
            this._value = value;
            this._message = message;
        }

        public int value() {
            return this._value;
        }
    }

    public enum PayType {
        none(-1, "请选择"),
        free(0, "免费"),
        downloadPay(1, "下载收费"),
        chargePay(2, "免费试玩"),
        payPerUse(3, "道具计费"),
        onLineFree(5, "免费"),
        payPerDownload(10, "下载收费"),
        payDownload(64, "收费下载");
        
        int _value = 0;
        String _message = "";

        public String getMessage() {
            return this._message;
        }

        private static TreeMap<Integer, PayType> _map;

        static {
            _map = new TreeMap<Integer, PayType>();
            for (PayType num : PayType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static PayType lookup(int value) {
            PayType type = _map.get(new Integer(value));
            if (type == null) {
                return none;
            }
            return type;
        }

        PayType(int value, String message) {
            this._value = value;
            this._message = message;
        }

        public int value() {
            return this._value;
        }
    }

    public enum DownloadStatus {
        download_all(0, "全部下载"), download_success(1, "下载成功"), download_install(2, "安装成功"), download_error(-1, "下载失败");
        private int _value = 0;

        private String _message = "";

        DownloadStatus(int value, String message) {
            this._value = value;
            this._message = message;
        }

        private static TreeMap<Integer, DownloadStatus> _map;

        static {
            _map = new TreeMap<Integer, DownloadStatus>();
            for (DownloadStatus num : DownloadStatus.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static DownloadStatus lookup(int value) {
            DownloadStatus type = _map.get(new Integer(value));
            if (type == null) {
                return download_error;
            }
            return type;
        }

        /**
         * @return the _value
         */
        public int getValue() {
            return _value;
        }

        /**
         * @return the _message
         */
        public String getMessage() {
            return _message;
        }

    }

    /** 通知CP地址类型ENUM，0：短代，4：支付宝 */
    public enum CpNotifyAddressType {
        isag(0, "联网短代"), alipay(4, "支付宝");

        private int _value = 0;
        private String _message = "";

        CpNotifyAddressType(int value, String message) {
            this._value = value;
            this._message = message;
        }

        private static TreeMap<Integer, CpNotifyAddressType> _map;

        static {
            _map = new TreeMap<Integer, CpNotifyAddressType>();
            for (CpNotifyAddressType num : CpNotifyAddressType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static CpNotifyAddressType lookup(int value) {
            CpNotifyAddressType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        /**
         * @return the _value
         */
        public int getValue() {
            return _value;
        }

        /**
         * @return the _message
         */
        public String getMessage() {
            return _message;
        }
    }

    public enum UserType {
        none(0), tourist(1), common_user(2), regist_user(3);

        private int _value = 0;

        UserType(int value) {
            this._value = value;
        }

        private static TreeMap<Integer, UserType> _map;

        static {
            _map = new TreeMap<Integer, UserType>();
            for (UserType num : UserType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static UserType lookup(int value) {
            UserType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        public int getValue() {
            return _value;
        }

    }

    public enum GameDevelopLanguageType {
        none(0), java(1), cPlusPlus(2);

        private int _value = 0;

        GameDevelopLanguageType(int value) {
            this._value = value;
        }

        private static TreeMap<Integer, GameDevelopLanguageType> _map;

        static {
            _map = new TreeMap<Integer, GameDevelopLanguageType>();
            for (GameDevelopLanguageType num : GameDevelopLanguageType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static GameDevelopLanguageType lookup(int value) {
            GameDevelopLanguageType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        public int getValue() {
            return _value;
        }

    }

    public enum UserFromType {
        none(0), wap(1), web(2), tv(3), sns(4), mobile(5), oldPlatForm(6);

        private int _value = 0;

        UserFromType(int value) {
            this._value = value;
        }

        private static TreeMap<Integer, UserFromType> _map;

        static {
            _map = new TreeMap<Integer, UserFromType>();
            for (UserFromType num : UserFromType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static UserFromType lookup(int value) {
            UserFromType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        public int getValue() {
            return _value;
        }

    }

    public enum UserAccountVaildType {
        none(0), egame(1), phone(2), email(4);

        private int _value = 0;

        UserAccountVaildType(int value) {
            this._value = value;
        }

        private static TreeMap<Integer, UserAccountVaildType> _map;

        static {
            _map = new TreeMap<Integer, UserAccountVaildType>();
            for (UserAccountVaildType num : UserAccountVaildType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static UserAccountVaildType lookup(int value) {
            UserAccountVaildType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        public int getValue() {
            return _value;
        }

    }

    public enum IMSIType {
        unknow(0), china_yidong(1), china_liantong(2), china_dianxin(3), hongkong(4), others(5);

        private int _value = 0;

        IMSIType(int value) {
            this._value = value;
        }

        private static TreeMap<Integer, IMSIType> _map;

        static {
            _map = new TreeMap<Integer, IMSIType>();
            for (IMSIType num : IMSIType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static IMSIType lookup(int value) {
            IMSIType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        public int getValue() {
            return _value;
        }

    }

    public static class UserAccountType {
        public final static int none = 0;
        public final static int phone = 1;
        public final static int email = 2;
        public final static int egame = 3;
        public final static int username = 4;
        public final static int third_part = 5;
        public final static int imsi = 6;
        public final static int mac = 7;
        public final static int wap = 8;
        public final static int phone_auto = 9;
        public final static int uid = 10;
        public final static int tianyi = 11;

    }

    public enum SMSValidateType {
        unknow(0), myw_default(1), myw_register(2), myw_findPwd(3), myw_bindPhone(4), myw_use_addressList(5);

        private int _value = 0;

        SMSValidateType(int value) {
            this._value = value;
        }

        private static TreeMap<Integer, SMSValidateType> _map;

        static {
            _map = new TreeMap<Integer, SMSValidateType>();
            for (SMSValidateType num : SMSValidateType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static SMSValidateType lookup(int value) {
            SMSValidateType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        public int getValue() {
            return _value;
        }
    }

    public enum SMSValidateContent {
        none(0, ""), myw_default(1, "��������֤��[#@#]"), myw_register(2, "��������֤��[#@#]��10��������Ч��С����Ƕ��ڵȴ���ļ���"), myw_findPwd(
                3, "��������֤�� �����һ�������֤��Ϊ:[#@#],�����Ʊ������֤�룬10��������Ч"), myw_bindPhone(4,
                "��������֤��[#@#]��10��������Ч�����ܱ�����Ҳ���õ������������"), myw_use_addressList(5,
                "��������֤��[#@#]��10��������Ч�����ͨѶ¼�������ܱ��棬�����");
        int value = 0;
        String message = "";

        public String getMessage() {
            return this.message;
        }

        private static TreeMap<Integer, SMSValidateContent> _map;

        static {
            _map = new TreeMap<Integer, SMSValidateContent>();
            for (SMSValidateContent num : SMSValidateContent.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static SMSValidateContent lookup(int value) {
            return _map.get(new Integer(value));
        }

        SMSValidateContent(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public int value() {
            return this.value;
        }
    }

    public enum EmailValidateContent {
        none(0, ""),
        myw_default(1, "蚂蚁屋验证码[#@#]"),
        ayx_registerByEmail(
                102,
                "亲爱的用户您好：\n"
                        + "&nbsp;&nbsp;感谢您注册爱游戏（play.cn）\n"
                        + "&nbsp;&nbsp;我们将为您提供最in游戏、最新资讯、最强攻略以及小编的各种神级吐血原创内容！\n\n"
                        + "&nbsp;&nbsp;您的登录账号为：#@email# \n\n"
                        + "&nbsp;&nbsp;点击以下链接即可完成注册："
                        + "&nbsp;&nbsp; <a href = \"#@USERURI#/api/v1/user/usercenter/activeEmailRegister.json?#@PARAMS#\">#@USERURI#/api/v1/user/usercenter/activeEmailRegister.json?#@PARAMS#</a> \n\n"
                        + "如果此链接不能直接点击，请将下面的地址拷贝到浏览器的地址栏中。\n\n " + "&nbsp;&nbsp;（链接72小时内有效。如果链接无法点击，请将它复制并粘贴到浏览器的地址栏中访\n" + "问。）\n\n"
                        + "&nbsp;&nbsp;邮件中包含您的个人信息，建议您保管好本邮件！ \n\n" + "&nbsp;&nbsp;如果您想随时随地下游戏，下载  爱游戏手机客户端 更方便，更快捷；\n"
                        + "&nbsp;&nbsp;您还可以手机登录play.cn，体验爱游戏门户手机版。\n\n" + "&nbsp;&nbsp;如果您有任何疑问或建议，欢迎拨打爱游戏客服热线：4008 289 289\n\n"
                        + "&nbsp;&nbsp;本邮件是系统自动发送的，请勿直接回复！感谢您的访问，祝您使用愉快！\n"
                        + "<img src = \"http://www.play.cn/r/cms/www/web/v01/main/img/footer_logo.png\">"),

        ayx_findPwdByEmail(
                103,
                "亲爱的爱游戏用户：您好！\n只需点击下方绑定链接，您的邮箱找回密码就完成了！此邮箱将用于您的账号密码找回。\n"
                        + "\n\n <a href = \"#@USERURI#/api/v1/user/usercenter/getResetPwdByEmailPage.json?check_code=#@CHECKCODE#\">请点击找回密码</a> \n\n"
                        + "如果此链接不能直接点击，请将下面的地址拷贝到浏览器的地址栏中。\n\n "
                        + "#@USERURI#/api/v1/user/usercenter/getResetPwdByEmailPage.json?check_code=#@CHECKCODE# \n\n"
                        + "感谢您使用爱游戏,  爱游戏play.cn敬上 ！"),

        ayx_bindEmail(104, "亲爱的爱游戏用户：您好！\n只需点击下方绑定链接，您的邮箱绑定就完成了！此邮箱将用于您找回密码、领奖等操作。\n"
                + "\n\n <a href = \"#@USERURI#/api/v1/user/security/bind_email.json?#@#\">请点击绑定邮箱</a> \n\n"
                + "如果此链接不能直接点击，请将下面的地址拷贝到浏览器的地址栏中。\n\n "
                + "#@USERURI#/api/v1/user/security/bind_email.json?email=#@EMAIL#&check_code=#@CHECKCODE#\n\n"
                + "感谢您参与爱游戏金鹏奖网络投票活动。支持您心目中最喜爱的手机游戏，投票即可参与抽奖，百分百中奖哦，"
                + "iPhone5S、植物大战僵尸正版玩偶、精美游戏周边、海量话费流量大礼每天狂送不止。 \n感谢您的支持！    爱游戏play.cn敬上 ！");
        int value = 0;
        String message = "";

        public String getMessage() {
            return this.message;
        }

        private static TreeMap<Integer, EmailValidateContent> _map;

        static {
            _map = new TreeMap<Integer, EmailValidateContent>();
            for (EmailValidateContent num : EmailValidateContent.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static EmailValidateContent lookup(int value) {
            return _map.get(new Integer(value));
        }

        EmailValidateContent(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public int value() {
            return this.value;
        }
    }

    public enum EmailValidateSubject {
        none(0, ""),
        // ayx_registerByEmail(101,"[爱游戏]亲爱的用户，请激活邮箱以完成注册"),
        // ayx_findPwdByEmail(102,"[爱游戏]您正在通过邮箱找回密码，如果不是本人操作，请忽视。"),
        // ayx_bindEmail(103,"[爱游戏]亲爱的用户，请激活邮箱以完成绑定");
        ayx_registerByEmail(102, "请激活邮箱以完成注册"),
        ayx_findPwdByEmail(103, "爱游戏邮箱找回密码验证"),
        ayx_bindEmail(104, "亲爱的用户，请激活邮箱以完成绑定");
        int value = 0;
        String message = "";

        public String getMessage() {
            return this.message;
        }

        private static TreeMap<Integer, EmailValidateSubject> _map;

        static {
            _map = new TreeMap<Integer, EmailValidateSubject>();
            for (EmailValidateSubject num : EmailValidateSubject.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static EmailValidateSubject lookup(int value) {
            return _map.get(new Integer(value));
        }

        EmailValidateSubject(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public int value() {
            return this.value;
        }
    }

    public enum EmailValidateType {
        unknow(0), ayx_registerByEmail(102), ayx_findPwdByEmail(103), ayx_bindEmail(104);
        private int _value = 0;

        EmailValidateType(int value) {
            this._value = value;
        }

        private static TreeMap<Integer, EmailValidateType> _map;

        static {
            _map = new TreeMap<Integer, EmailValidateType>();
            for (EmailValidateType num : EmailValidateType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static EmailValidateType lookup(int value) {
            EmailValidateType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        public int getValue() {
            return _value;
        }
    }

    public enum EmailValidateRedirectUrl {
        none(0, ""), ayx_yxhd_bindEmail(204, "http://act.play.cn/jpj/activeEmail?userId=#@userId#");
        int value = 0;
        String message = "";

        public String getMessage() {
            return this.message;
        }

        private static TreeMap<Integer, EmailValidateRedirectUrl> _map;

        static {
            _map = new TreeMap<Integer, EmailValidateRedirectUrl>();
            for (EmailValidateRedirectUrl num : EmailValidateRedirectUrl.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static EmailValidateRedirectUrl lookup(int value) {
            return _map.get(new Integer(value));
        }

        EmailValidateRedirectUrl(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public int value() {
            return this.value;
        }
    }

    public enum TokenStatus {
        normal(0), effectiveness(1);

        private int _value = 0;

        TokenStatus(int value) {
            this._value = value;
        }

        private static TreeMap<Integer, TokenStatus> _map;

        static {
            _map = new TreeMap<Integer, TokenStatus>();
            for (TokenStatus num : TokenStatus.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static TokenStatus lookup(int value) {
            TokenStatus type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        public int getValue() {
            return _value;
        }

    }

    /**
     * 广告位类型
     * 
     * circleAdv:循环广告位, doubleAdv:双屏广告位, singleAdv:单屏广告位, wordAdv:文件广告位,
     * insertAdv:推荐页插入广告位, game4GAdv:4G游戏使用 circleAdvNew:新的滚动广告位,图片横向全屏,
     * fourRow:四列广告位 popAdv:弹窗广告位, floatLayerAdv:浮标广告位
     */
    public enum AdvsLayoutType {
        circleAdv(1),
        doubleAdv(2),
        singleAdv(3),
        wordAdv(4),
        insertAdv(5),
        game4GAdv(6),
        circleAdvNew(7),
        fourRow(8),
        popAdv(9),
        floatLayerAdv(10);

        int _value = 0;

        private static TreeMap<Integer, AdvsLayoutType> _map;

        static {
            _map = new TreeMap<Integer, AdvsLayoutType>();
            for (AdvsLayoutType num : AdvsLayoutType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static AdvsLayoutType lookup(int value) {
            AdvsLayoutType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        AdvsLayoutType(int value) {
            this._value = value;
        }

        public int getValue() {
            return _value;
        }
    }

    /**
     * URL类型(广告接口,小编推荐,精选详情接口用到) 1:游戏详情, 2:频道 , 3:web页面 6:下载, 7:文字, 8:图片,
     * 9:单个游戏列表, 10:小编推荐, 11:视频, 12：精选双排游戏, 13:精选一排三个游戏 14:精选栏目详情, 15:隐藏栏目详情 ,
     * 16:爆笑资讯详情, 17:分类下根据标签过滤后的详情页,18： 礼包列表页 ,19:礼包详情页 ,20：开服,21：开测
     * ,22个人中心，23排行榜,24 新网游（746） 25 新品栏目
     */
    public enum UrlType {
        gameDetail(1),
        channel(2),
        webPage(3),
        subjectDetail(4),
        classifyDetail(5),
        gameDownload(6),
        word(7),
        pic(8),
        gameInfo(9),
        newTopicDetail(10),
        video(11),
        gameInfoDouble(12),
        gameInfoThree(13),
        choicenessDetail(14),
        hiddenDetail(15),
        laughDetail(16),
        classifyTagDetail(17),
        listGift(18),
        giftDetail(19),
        listOnlineGameServer(20),
        listOnlineGameTest(21),
        visit_user_center(22),
        gameRanking(23),
        NewOnlineGame(24),
        newColumn(25);
        int _value = 0;

        private static TreeMap<Integer, UrlType> _map;

        static {
            _map = new TreeMap<Integer, UrlType>();
            for (UrlType num : UrlType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static UrlType lookup(int value) {
            UrlType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        UrlType(int value) {
            this._value = value;
        }

        public int getValue() {
            return _value;
        }
    }

    public enum SearchSortType {
        none(0), downCountWeek(1105), firstOnlineTime(1101), shareCount(1104), commentCount(1103), downCountTotal(1102), starLevel(1106);
        int _value = 0;

        private static TreeMap<Integer, SearchSortType> _map;

        static {
            _map = new TreeMap<Integer, SearchSortType>();
            for (SearchSortType num : SearchSortType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static SearchSortType lookup(int value) {
            SearchSortType type = _map.get(new Integer(value));
            if (type == null) {
                return none;
            }
            return type;
        }

        SearchSortType(int value) {
            this._value = value;
        }

        public int getValue() {
            return _value;
        }
    }

    /**
     * 游戏顶和踩对应的枚举类型
     */
    public enum GameAgreeType {
        agree(1), disAgree(2);
        int _value = 0;

        private static TreeMap<Integer, GameAgreeType> _map;

        static {
            _map = new TreeMap<Integer, GameAgreeType>();
            for (GameAgreeType num : GameAgreeType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static GameAgreeType lookup(int value) {
            GameAgreeType type = _map.get(new Integer(value));
            if (type == null) {
                return agree;
            }
            return type;
        }

        GameAgreeType(int value) {
            this._value = value;
        }

        public int getValue() {
            return _value;
        }
    }

    public enum DownType {
        DOWNLOAD(0), DOWNLOAD_SUCCESS(1), INSTALL_SUCCESS(2);
        int _value = 0;

        DownType(int value) {
            this._value = value;
        }

        public int getValue() {
            return _value;
        }
    }

    /**
     * 
     * 1.星级;2.评论; 3.上线时间;4.下载;9.热门;10.分享
     */
    public enum OrderType {
        starLevel(1), commentCount(2), firstOnlineTime(3), downCountTotal(4), downCountWeek(9), shareCount(10);
        int _value = 0;
        private static TreeMap<Integer, OrderType> _map;

        static {
            _map = new TreeMap<Integer, OrderType>();
            for (OrderType num : OrderType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static OrderType lookup(int value) {
            OrderType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        OrderType(int value) {
            this._value = value;
        }

        public int getValue() {
            return _value;
        }
    }

    /**
     * 激活码状态 -1：不区分状态，0:未领，1：已领
     */
    public enum GiftCodeStatus {
        NONE(-1), UNOBTAIN(0), OBTAIN(1);
        int _value = 0;
        private static TreeMap<Integer, GiftCodeStatus> _map;

        static {
            _map = new TreeMap<Integer, GiftCodeStatus>();
            for (GiftCodeStatus num : GiftCodeStatus.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static GiftCodeStatus lookup(int value) {
            GiftCodeStatus type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        GiftCodeStatus(int value) {
            this._value = value;
        }

        public int getValue() {
            return _value;
        }
    }

    /**
     * 礼包领取类型 0:领号，1：淘号
     */
    public enum GiftObtainType {
        OBTAIN(0), PICK(1);
        int _value = 0;
        private static TreeMap<Integer, GiftObtainType> _map;

        static {
            _map = new TreeMap<Integer, GiftObtainType>();
            for (GiftObtainType num : GiftObtainType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static GiftObtainType lookup(int value) {
            GiftObtainType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        GiftObtainType(int value) {
            this._value = value;
        }

        public int getValue() {
            return _value;
        }
    }

    /**
     * 网游开测事件类型
     */
    public enum GameEventType {
        Technology(1, "技术测试"), DeleteArchive(2, "删档封测"), NoDeleteArchive(3, "不删档封测"), DeleteArchiveInner(4, "删档内测"), NoDeleteArchiveInner(
                5, "不删档内测"), PublicTest(6, "公测"), Business(7, "运营");

        private int _value = 0;
        private String _message = "";

        GameEventType(int value, String message) {
            this._value = value;
            this._message = message;
        }

        private static TreeMap<Integer, GameEventType> _map;

        static {
            _map = new TreeMap<Integer, GameEventType>();
            for (GameEventType num : GameEventType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static GameEventType lookup(int value) {
            GameEventType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        public int getValue() {
            return _value;
        }

        public String getMessage() {
            return _message;
        }
    }

    /**
     * 开服开测中用于展示今天，明天，未来一周，昨天，历史分组使用
     */
    public enum DateType {
        TODAY("今天"), TOMORROW("明天"), COMING_WEEK("未来一周"), YESTERDAY("昨天"), HISTORY("历史");
        String _value = "";

        DateType(String value) {
            this._value = value;
        }

        public String getValue() {
            return _value;
        }
    }

    public enum MobileNetworkType {
        DIANXIN(0, "电信"), LIANTONG(1, "联通"), YIDONG(2, "移动");
        private int _value = 0;
        private String _message = "";

        MobileNetworkType(int value, String message) {

            this._message = message;
            this._value = value;
        }

        private static TreeMap<Integer, MobileNetworkType> _map;

        static {
            _map = new TreeMap<Integer, MobileNetworkType>();
            for (MobileNetworkType num : MobileNetworkType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static MobileNetworkType lookup(int value) {
            MobileNetworkType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        public int getValue() {
            return _value;
        }

        public String getMessage() {
            return _message;
        }
    }

    public enum RuleFilterType {
        NetworkFilter("1"), ClientFilter("2");
        String _value = "";

        RuleFilterType(String value) {
            this._value = value;
        }

        public String getValue() {
            return _value;
        }
    }

    /**
     * 自动打的标签类型
     */
    public enum AutoRefTagType {
        ActiveTag(1, "活动状态标签"), GiftTag(2, "礼包状态标签"), OpenServerTag(3, "开服状态标签");

        private int _value = 0;
        private String _message = "";

        AutoRefTagType(int value, String message) {
            this._value = value;
            this._message = message;
        }

        private static TreeMap<Integer, AutoRefTagType> _map;

        static {
            _map = new TreeMap<Integer, AutoRefTagType>();
            for (AutoRefTagType num : AutoRefTagType.values()) {
                _map.put(new Integer(num.getValue()), num);
            }
        }

        public static AutoRefTagType lookup(int value) {
            AutoRefTagType type = _map.get(new Integer(value));
            if (type == null) {
                return null;
            }
            return type;
        }

        public int getValue() {
            return _value;
        }

        public String getMessage() {
            return _message;
        }
    }
}

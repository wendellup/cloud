package com.cloud

/**
 * Created by yuchao on 2016/4/15.
 */
class Article implements java.io.Serializable {

    static constraints = {
        enable nullable: true
        startTime nullable: true
        endTime nullable: true
        insertTime nullable: true
        updateTime nullable: true
        operatorId nullable: true


    }
    static mapping = {
        table 't_article'
        id column: 'id'
        paramId column: 'param_id'
        title column: 'title'
        content column: 'content'
        enable column: 'enable'
        startTime column: 'start_time'
        endTime column: 'end_time'
        insertTime column: 'insert_time'
        updateTime column: 'update_time'
        operatorId column: 'operator_id'
        version false
    }
    Integer id;
    Integer paramId;
    String title;
    String content;
    Boolean enable;
    Long startTime;
    Long endTime;
    Long insertTime;
    Long updateTime;
    Integer operatorId;
}

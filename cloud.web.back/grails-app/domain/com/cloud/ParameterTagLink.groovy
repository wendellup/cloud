package com.cloud

class ParameterTagLink implements java.io.Serializable {

    static constraints = {
        businessSortNo nullable: true
        tagSortNo nullable: true
        enable nullable: true
        startTime nullable: true
        endTime nullable: true
        updateTime nullable: true
        operatorId nullable: true
    }
    static mapping = {
        table 't_parameter_tag_link'
        businessId column: 'business_id'
        tagId column: 'tag_id'
        id composite: ['businessId', 'tagId']
        businessSortNo column: 'business_sort_no'
        tagSortNo column: 'tag_sort_no'
        enable column: 'enable'
        startTime column: 'start_time'
        endTime column: 'end_time'
        updateTime column: 'update_time'
        operatorId column: 'operator_id'
        version false
    }
    Integer businessId;
    Integer tagId;
    Integer businessSortNo;
    Integer tagSortNo;
    Boolean enable;
    Long startTime;
    Long endTime;
    Long updateTime;
    Integer operatorId;
}

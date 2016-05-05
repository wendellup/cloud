package com.cloud

class ParameterApp implements java.io.Serializable {
    static constraints = {
    }
    static mapping = {
//        datasource 'game'
        table 't_parameter_app'
        id column: 'id'
        parentId column: 'parent_id'
        type column: 'type'
        name column: 'name'
        param column: 'param'
        sort column: 'sort'
        enable column: 'enable'
        depth column: 'depth'
        remark column: 'remark'
        picId column: 'pic_id'
        beginTime column: 'begin_time'
        endTime column: 'end_time'
        updateTime column: 'update_time'
        operatorId column: 'operator_id'
        version false
    }
    Integer id;
    Integer parentId;
    Integer type;
    String name;
    String param;
    Integer sort;
    Boolean enable;
    Integer depth;
    String remark;
    Long picId;
    Date beginTime
    Date endTime
    Date updateTime
    Integer operatorId
}

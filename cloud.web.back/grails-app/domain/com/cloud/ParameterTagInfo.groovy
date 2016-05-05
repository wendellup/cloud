package com.cloud

class ParameterTagInfo implements java.io.Serializable{

    static mapping = {
        table 't_parameter_tag'
        id column: 'id'
        tagName column: 'tag_name'
        tagType column: 'tag_type'
        enable column: 'enable'
        remark column: 'remark'
        version false
    }

    static constraints = {
//        tagName nullable: true
//        tagType nullable: true
//        enable nullable: true
//        remark nullable: true
    }

    Integer id;
    String tagName;
    Integer tagType;
    boolean enable;
    String remark;
}

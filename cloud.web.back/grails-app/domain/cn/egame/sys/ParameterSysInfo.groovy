package cn.egame.sys

class ParameterSysInfo implements Serializable {

    static mapping = {
        table 't_parameter_sys'
        name column: 'name'
        value column: 'value'
        id composite: ['name']
        version false
    }

    static constraints = {
        name nullable: true
        value nullable: true
    }

    String name;
    String value;
}

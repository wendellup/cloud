package com.cloud

class ParameterAppService {

    /**
     * @Description: 根据父ID查询子ID的列表
     * @param parentId
     * @return List<Integer>
     * @Modified by none
     * @Modified Date
     */
    def List<Integer> listAppParameterIdsByParentId(int parentId) {
        def results = ParameterApp.withCriteria {
            projections { property('id') }
            eq('parentId', parentId)
            eq('enable', true)
            gt('endTime', new Date())
            order("sort", "desc")
        };
        return results
    }

    /**
     * 根据ID获取 ParameterApp
     * @param id
     * @return
     */
    def queryParameterAppById(int id) {
        if(id == null) {
            return null;
        }
        return ParameterApp.find("from ParameterApp a where a.enable = ? and a.id=?",[true, id]);
    }
}

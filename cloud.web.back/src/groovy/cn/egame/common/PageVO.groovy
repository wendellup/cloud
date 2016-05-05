package cn.egame.common

class PageVO {

    Integer currPage;

    Integer pageSize;

    Integer total;

    List<Object> results;

    PageVO(int currPage, int pageSize, List list) {
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.total = list ? list.size() : 0;

        int startIndex = (currPage - 1) * pageSize;
        if (startIndex >= total) {
            startIndex = 0;
        }
        pageSize = startIndex + pageSize >= total ? total - startIndex : pageSize;

		if (!list || list.isEmpty()) {
			this.results = new ArrayList();
		}
        this.results = list.subList(startIndex, startIndex + pageSize);
    }

    PageVO(int total, List list) {
        this.total = total;


        this.results = list;
    }
}

package cn.egame

import cn.egame.user.ResInfo


/**
 * Created by gjzhao on 14-7-25.
 */
class OrderFlagCompare implements Comparator {

	@Override
	int compare(Object o1, Object o2) {
		if (o1 != null && o2 != null) {
			ResInfo resInfo1 = (ResInfo) o1;
			ResInfo resInfo2 = (ResInfo) o2;
			int key1 = resInfo1.orderFlag;
			int key2 = resInfo2.orderFlag;
			return key1.compareTo(key2);
		}
		else {
			return 0;
		}
	}
}

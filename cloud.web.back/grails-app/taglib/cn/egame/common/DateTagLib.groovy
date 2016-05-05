package cn.egame.common

import cn.egame.utils.DateUtil

class DateTagLib {

	static namespace = "date"

	def parseLong = { attrs, body ->
		def dateStr = '';
		try {
			if (attrs.value) {
				dateStr = DateUtil.parseDate2String(attrs.value, attrs.fmt);
			}
		}
		catch (Exception e) {
			log.error("error", e);
		}
		out << dateStr;
	}

	def parseDate = { attrs, body ->
		def dateStr = '';
		try {
			if (attrs.value) {
				dateStr = DateUtil.parseDate(attrs.value, attrs.fmt);
			}
		}
		catch (Exception e) {
			log.error("error", e);
		}
		out << dateStr;
	}
}

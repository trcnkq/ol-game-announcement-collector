package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 梦三国
 * @author leon
 *
 */

public class M3guoSpider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "梦三国";
	}
	
	@Override
	public String getPageURL() {
		return "http://www.m3guo.com/news/bulletin/";
	}
	
	@Override
	public String getRegex() {
		return "<li><span><a  href=\"/news/bulletin/\" target=\"_blank\">\\[官方公告\\]</a></span><a  href=\"(.+?)\" target=\"_blank\">(.+?)</a><span class=\"date\">(\\d+)-(\\d+)-(\\d+)</span>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://www.m3guo.com/news/bulletin/" + matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3),matcher.group(4), matcher.group(5));
	}

}

package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * QQ幻想世界
 * @author leon
 *
 */
public class QQhxsjSpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "QQ幻想世界";
	}
	
	@Override
	public String getPageURL() {
		return "http://hxsj.qq.com/webplat/info/90/340/736/list_1.shtml";
	}
	
	@Override
	public String getRegex() {
		return "</a><a target=\"_blank\" href=\"(.+?)\">(.+?)</a></span> <span class=\"time\">(\\d+)-(\\d+)-(\\d+)</span></li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://hxsj.qq.com" + matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

}

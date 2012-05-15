package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 倩女幽魂
 * @author leon
 *
 */

public class QNSpider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "倩女幽魂";
	}
	
	@Override
	public String getPageURL() {
		return "http://qn.163.com/news/gonggao.html";
	}
	
	@Override
	public String getRegex() {
		return "<span class=\"time\">\\[(\\d+)/(\\d+)/(\\d+)\\]</span> <a href=\"(.+?)\" target=\"_blank\">(.+?)</a></li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}

}

package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 天龙八部3
 * @author leon
 *
 */

public class TL3Spider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "天龙八部3";
	}
	
	@Override
	public String getPageURL() {
		return "http://tl3.changyou.com/tl3/announce/official/official.shtml";
	}
	
	@Override
	public String getRegex() {
		return "<li><span class=\"time\">(\\d+)-(\\d+)-(\\d+)</span>.+?<a href=\"(.+?)\" title='(.+?)'";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://news.91.com/content/" + matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}

}

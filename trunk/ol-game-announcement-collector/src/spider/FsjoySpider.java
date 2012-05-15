package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 街头篮球
 * @author leon
 *
 */

public class FsjoySpider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "街头篮球";
	}
	
	@Override
	public String getPageURL() {
		return "http://www.fsjoy.com/fs2012/news/news3.html";
	}
	
	@Override
	public String getRegex() {
		return "<a href=\"(.+?)\" target=\"_parent\">(.+?)</a></span><span class=\"date\">(\\d+).(\\d+).(\\d+)</span></li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

}



package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 天下3
 * @author leon
 *
 */

public class Tx3Spider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "天下3";
	}
	
	@Override
	public String getPageURL() {
		return "http://tx3.163.com/news/notice/";
	}
	
	@Override
	public String getRegex() {
		return "<a href=\"(.+?)\" target=\"_blank\">(.+?)</a></span><span class=\"date\">\\[(\\d+)/(\\d+)/(\\d+)\\]</span></li>";
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

package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 艾尔之光
 * @author leon
 *
 */
public class ELSSpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "艾尔之光";
	}
	
	@Override
	public String getPageURL() {
		return "http://els.ztgame.com/html/news/system.shtml";
	}
	
	@Override
	public String getRegex() {
		return "<span class=\"date\">(\\d+)-(\\d+)-(\\d+)</span>\\s*<p>.+?<a href=\"(.+?)\" target=\"_blank\"  >(.+?)</a>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://els.ztgame.com" + matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}

}

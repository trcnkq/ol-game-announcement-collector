package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 梦幻西游
 * @author leon
 *
 */

public class XyqSpider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "梦幻西游";
	}
	
	@Override
	public String getPageURL() {
		return "http://xyq.163.com/news/bul.html";
	}
	
	@Override
	public String getRegex() {
		return "<span class=\"title\"><a href=\"(.+?)\">(.+?)</a></span>\\s*<span class=\"time\">\\[(\\d+)-(\\d+)-(\\d+)\\]</span>";
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

package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 星辰变
 * @author leon
 *
 */
public class XCBSpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "星辰变";
	}
	
	@Override
	public String getPageURL() {
		return "http://xcb.sdo.com/web6/news/news_list.asp?CategoryID=948";
	}
	
	@Override
	public String getRegex() {
		return "<a href=\"\\.\\.(.+?)\" target=.+?\">(.+?)</a>\\s*<span class=\"time\">(\\d+)-(\\d+)-(\\d+)</span>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://xcb.sdo.com" + matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

}

package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 龙之谷
 * @author leon
 *
 */

public class DNSpider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "龙之谷";
	}
	
	@Override
	public String getPageURL() {
		return "http://dn.sdo.com/web8/news/newsList2.asp";
	}
	
	@Override
	public String getRegex() {
		return "href=\"(.+?)\">(.+?)</a></span> <span class=\"newsDate\">(\\d+)-(\\d+)-(\\d+)</span> </li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://dn.sdo.com/web8/news/" + matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

}

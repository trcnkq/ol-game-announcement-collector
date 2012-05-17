package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 远征online
 * @author leon
 *
 */
public class YZSpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "远征online";
	}
	
	@Override
	public String getPageURL() {
		return "http://yz.szgla.com/NewsFile/T2/P1.html";
	}
	
	@Override
	public String getRegex() {
		return "<ul><li><span class=date>\\[(\\d+)-(\\d+)-(\\d+)\\]</span><img src=.+?> <a href=(.+?)>(.+?)</a></li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://yz.szgla.com" + matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}

}

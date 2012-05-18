package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 神武
 * @author leon
 *
 */
public class SWSpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "神武";
	}
	
	@Override
	public String getPageURL() {
		return "http://sw.henhaoji.com/news/";
	}
	
	@Override
	public String getRegex() {
		return "<a href='(.+?)' target='_blank'><font style=.+?>(.+?)</font></a><span>(\\d+)-(\\d+)-(\\d+)</span></li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://sw.henhaoji.com" + matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

}

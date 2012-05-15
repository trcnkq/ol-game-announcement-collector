package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 问道
 * @author Isun
 *
 */

public class WDSpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "问道";
	}
	
	@Override
	public String getPageURL() {
		return "http://wd.gyyx.cn/News/NewsList_New.aspx?NewsType=2";
	}
	
	@Override
	public String getRegex() {
		return "<li><span>\\[(\\d+)-(\\d+)-(\\d+)\\]</span><a href=\"(.+?)\".+?</em>(.+?)</a>";
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

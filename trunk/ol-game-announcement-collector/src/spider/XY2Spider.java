package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 大话西游
 * @author leon
 *
 */

public class XY2Spider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "大话西游2";
	}
	
	@Override
	public String getPageURL() {
		return "http://xy2.163.com/news/notice.html";
	}
	
	@Override
	public String getRegex() {
		return "<a href=\"(.+?)\" target=\"_blank\" class=\"news_link\">(.+?)</a></h2>\\s*<span class=\"news_time\">\\[(\\d+)/(\\d+)/(\\d+)\\]</span>";
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

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
		return "href=\"(/News/NewsDetail.+?)\"\\s*target=\"_blank\">(.+?)</a>[\\s\\S]+?(\\d{4})-(\\d+)-(\\d+)";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://wd.gyyx.cn" + matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

}

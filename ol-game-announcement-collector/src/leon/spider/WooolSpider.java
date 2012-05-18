package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 传奇世界
 * @author leon
 *
 */
public class WooolSpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "传奇世界";
	}
	
	@Override
	public String getPageURL() {
		return "http://woool.sdo.com/web2/news/news_list.asp?CategoryID=2402";
	}
	
	@Override
	public String getRegex() {
		return "<li><a href=\"(.+?)\" target=\"_blank\">(.+?)</a><span>(\\d+)-(\\d+)-(\\d+)</span></li>";
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

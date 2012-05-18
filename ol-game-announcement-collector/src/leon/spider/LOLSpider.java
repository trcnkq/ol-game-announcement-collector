package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 英雄联盟
 * @author leon
 *
 */

public class LOLSpider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "英雄联盟";
	}
	
	@Override
	public String getPageURL() {
		return "http://lol.qq.com/webplat/info/120/436/1019/list_1.shtml";
	}
	
	@Override
	public String getRegex() {
		return "<a href=\"(.+?)\" target=\"_blank\">(.+?)</a></li><li class=\"news_lst plr25\"><span class=\"fr\">(\\d+)-(\\d+)-(\\d+)</span>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://lol.qq.com" + matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

}

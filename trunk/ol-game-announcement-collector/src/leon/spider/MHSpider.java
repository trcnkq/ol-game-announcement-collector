package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 洛奇英雄传
 * @author leon
 *
 */

public class MHSpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "洛奇英雄传";
	}
	
	@Override
	public String getPageURL() {
		return "http://mh.tiancity.com/homepage/articlev7/Class_969_Time_1.html";
	}
	
	@Override
	public String getRegex() {
		return "</a></em><span><a href=\"(.+?)\" target=\"_blank\">(.+?)</a></span><q>\\[(\\d+)-(\\d+)-(\\d+)\\]</q></li>";
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

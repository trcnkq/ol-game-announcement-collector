package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * QQ音速
 * @author leon
 *
 */

public class R2Spider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "QQ音速";
	}
	
	@Override
	public String getPageURL() {
		return "http://r2.qq.com/webplat/info/77/248/list_1.shtml";
	}
	
	@Override
	public String getRegex() {
		return "<li><span>\\[(\\d+)-(\\d+)-(\\d+)\\]</span><em>&nbsp;<img src=.+?</a>　<a href=\"(.+?)\" >(.+?)</a></em> </li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://r2.qq.com" + matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}

}

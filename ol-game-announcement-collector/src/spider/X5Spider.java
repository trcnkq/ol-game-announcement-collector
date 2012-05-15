package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * QQ炫舞
 * @author leon
 *
 */

public class X5Spider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "QQ炫舞";
	}
	
	@Override
	public String getPageURL() {
		return "http://x5.qq.com/webplat/info/50/146/306/list_1.shtml";
	}
	
	@Override
	public String getRegex() {
		return "<li><span>\\s*(\\d+)-(\\d+)-(\\d+)\\s*</span><a target=\"_blank\" href=\".+?\">[\\s*公告\\s*]</a> <a target=\"_blank\" href=\"(.+?)\">(.+?)</a> </li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://x5.qq.com" + matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}

}

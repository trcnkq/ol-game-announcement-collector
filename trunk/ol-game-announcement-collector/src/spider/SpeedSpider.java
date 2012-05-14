package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * QQ飞车
 * @author Leon
 *
 */

public class SpeedSpider extends CanonicalSpider{
	@Override
	public String getGameName() {
		return "QQ飞车";
	}
	
	@Override
	public String getPageURL() {
		return "http://speed.qq.com/webplat/info/112/399/916/list_1.shtml";
	}
	
	@Override
	public String getRegex() {
		return "<li><span>\\s*(\\d+)-(\\d+)-(\\d+)\\s*</span><a target=\"_blank\" href=\"/webplat/info/112/399/916/list_1.shtml\">\\[\\s*公告\\s*\\]</a> <a target=\"_blank\" href=\"(.+?)\">(.+?)</a> </li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://speed.qq.com" + matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}


}

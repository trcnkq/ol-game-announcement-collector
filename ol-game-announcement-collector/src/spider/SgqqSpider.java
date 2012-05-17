package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * qq三国
 * @author leon
 *
 */
public class SgqqSpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "qq三国";
	}
	
	@Override
	public String getPageURL() {
		return "http://sg.qq.com/webplat/info/54/169/385/list_1.shtml";
	}
	
	@Override
	public String getRegex() {
		return "<span>(\\d+)-(\\d+)-(\\d+)</span>\\s*<a target=\"_blank\" href=\".+?\">.+?</a>\\s*<a target=\"_blank\" href=\"(.+?)\"><.+?><B>(.+?)</B></font></a>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://sg.qq.com" + matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}

}


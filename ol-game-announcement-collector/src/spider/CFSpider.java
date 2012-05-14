package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 穿越火线
 * @author Leon
 *
 */

public class CFSpider extends CanonicalSpider{

	@Override
	public String getGameName() {
		return "穿越火线";
	}
	
	@Override
	public String getPageURL() {
		return "http://cf.qq.com/webplat/info/news_version3/125/860/862/m639/list_1.shtml";
	}
	
	@Override
	public String getRegex() {
		return "</a><a target=\"_blank\" href=\"(.+?)\"><.+?>(.+?)</font></a> <span>(\\d+)-(\\d+)-(\\d+)</span> </li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://cf.qq.com" + matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

	
}

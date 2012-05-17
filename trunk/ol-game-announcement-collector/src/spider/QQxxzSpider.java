package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * QQ仙侠传
 * @author leon
 *
 */
public class QQxxzSpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "QQ仙侠传";
	}
	
	@Override
	public String getPageURL() {
		return "http://xxz.qq.com/webplat/info/news_version3/149/1711/1717/1719/m1633/list_1.shtml";
	}
	
	@Override
	public String getRegex() {
		return "<a target=\"_blank\" href=\"(.+?)\" .+?>(.+?)</a><span>(\\d+)-(\\d+)-(\\d+)</span></li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://xxz.qq.com" + matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

}

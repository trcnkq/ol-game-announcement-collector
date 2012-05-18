package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 魔域
 * @author leon
 *
 */

public class MYSpider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "魔域";
	}
	
	@Override
	public String getPageURL() {
		return "http://my.91.com/news/";
	}
	
	@Override
	public String getRegex() {
		return "<a href='http://news.91.com/content/(\\d+)-(\\d+)-(\\d+)/(.+?)'  target='_blank'>(.+?)</a></td><td>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://news.91.com/content/" + matcher.group(1) + "-" + matcher.group(2) + "-" +matcher.group(3) + "/" +matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}

}

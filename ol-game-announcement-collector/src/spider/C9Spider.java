package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 第九大陆
 * @author leon
 *
 */
public class C9Spider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "第九大陆";
	}
	
	@Override
	public String getPageURL() {
		return "http://c9.qq.com/webplat/info/news_version3/386/450/451/453/m286/list_1.shtml";
	}
	
	@Override
	public String getRegex() {
		return "href=\"(.+?)\"><fon.+?>(.+?)</font></a> <span class=\"date\">(\\d+)-(\\d+)-(\\d+)</span></li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://c9.qq.com" + matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

}

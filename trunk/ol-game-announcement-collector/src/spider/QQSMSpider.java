package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 全球使命
 * @author leon
 *
 */
public class QQSMSpider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "全球使命";
	}
	
	@Override
	public String getPageURL() {
		return "http://news.zygames.com/news/bulletin/";
	}
	
	@Override
	public String getRegex() {
		return "<li><a target=\"_blank\" href=\"(.+?)\"><i>(.+?)</i><code>(\\d+)-(\\d+)-(\\d+)</code></a></li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return  matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

}

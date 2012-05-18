package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 诛仙2
 * @author leon
 *
 */

public class ZhuxianSpider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "诛仙2";
	}
	
	@Override
	public String getPageURL() {
		return "http://zhuxian.wanmei.com/news/gamebroad/list.shtml";
	}
	
	@Override
	public String getRegex() {
		return "<li><ins>\\[(\\d+)-(\\d+)-(\\d+)\\]</ins><a href=\"(.+?)\" target=\"_blank\" title=\"(.+?)\"><em>·</em>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://zhuxian.wanmei.com" + matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}

}

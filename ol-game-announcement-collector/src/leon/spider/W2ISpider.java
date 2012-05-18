package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 完美国际
 * @author leon
 *
 */
public class W2ISpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "完美国际";
	}
	
	@Override
	public String getPageURL() {
		return "http://w2i.wanmei.com/news/bulletin/listnote.htm";
	}
	
	@Override
	public String getRegex() {
		return "<li><.+?>\\[(\\d+)-(\\d+)-(\\d+)\\]</span><a href=\"(.+?)\" target=\"_blank\">(.+?)</a></li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://w2i.wanmei.com" + matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}

}

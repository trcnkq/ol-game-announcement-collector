package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 反恐精英
 * @author Isun
 *
 */
public class CSSpider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "反恐精英";
	}
	
	@Override
	public String getPageURL() {
		return "http://csol.tiancity.com/homepage/article/Class_6_Time_1.html";
	}
	
	@Override
	public String getRegex() {
		return "<li><span>\\[(\\d+)-(\\d+)-(\\d+)\\]</span><a href=\"(.+?)\".+?</em>(.+?)</a>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}

}

package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 大唐无双
 * @author leon
 *
 */
public class DT2Spider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "大唐无双";
	}
	
	@Override
	public String getPageURL() {
		return "http://dt2.163.com/news/notice/";
	}
	
	@Override
	public String getRegex() {
		return "</span><span class=\"link\"><a href=\"(.+?)\">(.+?)</a></span><span class=\"date\">\\[(\\d+)-(\\d+)-(\\d+)\\]</span></li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String getHref(Matcher matcher) {
		return matcher.group(1);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(3), matcher.group(4), matcher.group(5));
	}

}

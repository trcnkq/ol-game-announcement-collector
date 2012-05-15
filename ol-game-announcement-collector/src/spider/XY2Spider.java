package spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 大话西游
 * @author leon
 *
 */

public class XY2Spider extends CanonicalSpider {

	@Override
	public String getGameName() {
		return "大话西游2";
	}
	
	@Override
	public String getPageURL() {
		return "http://xy2.163.com/news/notice.html";
	}
	
	@Override
	public String getRegex() {
		return "<span class=\"news_time\">\\[(\\d+)/(\\d+)/(\\d+)\\]</span>\\s*</div>\\s*<div class=\"news_li_bd clearfix\">\\s*<a target=\"_blank\" href=\"(.+?)\" class=\"news_pic\"><img src=\"/images/img_url_s.jpg\" style=\"width:128px;\" alt=\"(.+?)\" /></a>";
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

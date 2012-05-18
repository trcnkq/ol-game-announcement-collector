package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

public class DNFSpider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "地下城与勇士";
	}
	
	@Override
	public String getPageURL() {
		return "http://dnf.qq.com/webplat/info/news_version3/119/495/498/m394/list_1.shtml";
	}
	
	@Override
	public String getRegex() {
		return "<li><span>(\\d+)-(\\d+)-(\\d+)</span><a href=\".+?\" target=\"_blank\">\\[公告\\]</a><a href=\"(.+?)\" target=\"_blank\">(.+?)</a></li>";
	}

	@Override
	public String getTitle(Matcher matcher) {
		return matcher.group(5);
	}

	@Override
	public String getHref(Matcher matcher) {
		return "http://dnf.qq.com" + matcher.group(4);
	}

	@Override
	public Date getDate(Matcher matcher) {
		return getDate(matcher.group(1), matcher.group(2), matcher.group(3));
	}
}

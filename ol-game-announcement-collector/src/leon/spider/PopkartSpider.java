package leon.spider;

import java.util.Date;
import java.util.regex.Matcher;

/**
 * 跑跑卡丁车
 * @author leon
 *
 */

public class PopkartSpider extends CanonicalSpider {
	@Override
	public String getGameName() {
		return "跑跑卡丁车";
	}
	
	@Override
	public String getPageURL() {
		return "http://popkart.tiancity.com/homepage/article/Class_32_Time_1.html";
	}
	
	@Override
	public String getRegex() {
		return "href=\"(.+?)\" target=\"_blank\">(.+?)</a></td>\\s*<td width=\"142\"><div align=\"center\">(\\d+)/(\\d+)/(\\d+)</div></td>";
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

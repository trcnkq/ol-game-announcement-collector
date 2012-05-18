package leon.spider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import leon.model.Announcement;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;


/**
 * 典型抓取器
 * @author Isun
 *
 */
public abstract class CanonicalSpider extends Spider {
	
	@Override
	public List<Announcement> getAnnouncements() {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(getPageURL());
		String html = "";
		try {
			httpClient.executeMethod(getMethod);
			html = getHtml(getMethod, null);
			html = preProcess(html);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
//		System.out.println(html);

		List<Announcement> res = new ArrayList<Announcement>();

		Matcher matcher = Pattern.compile(getRegex()).matcher(html);
		while (matcher.find()) {
			Announcement announcement = new Announcement();
			announcement.setGameName(getGameName());
			announcement.setTitle(getTitle(matcher));
			announcement.setHref(getHref(matcher));
			announcement.setDate(getDate(matcher));
			res.add(announcement);
		}
		
		return res;
	}
	
	protected abstract String getPageURL();
	protected abstract String getRegex();

	protected abstract String getTitle(Matcher matcher);
	protected abstract String getHref(Matcher matcher);
	protected abstract Date getDate(Matcher matcher);

	
	/**
	 * 预处理html
	 * @param html
	 * @return
	 */
	protected String preProcess(String html) {
		return html;
	}

}

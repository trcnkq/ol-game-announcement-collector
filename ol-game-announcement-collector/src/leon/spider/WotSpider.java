package leon.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import leon.model.Announcement;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;


/**
 * 坦克世界
 * @author Isun
 *
 */
public class WotSpider extends Spider {
	
	@Override
	public List<Announcement> getAnnouncements() {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(getPageURL());
		String html = "";
		try {
			httpClient.executeMethod(getMethod);
			html = getHtml(getMethod, null);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
//		System.out.println(html);

		List<Announcement> res = new ArrayList<Announcement>();

		Matcher matcher = Pattern.compile("<a href=\"(.+?)\" target=\"_blank\">(.+?)</a>").matcher(html);
		while (matcher.find()) {
			Announcement announcement = new Announcement();
			announcement.setGameName(getGameName());
			announcement.setTitle(matcher.group(2));
			announcement.setHref(matcher.group(1));
			
			Matcher m = Pattern.compile("newsid=(\\d+)").matcher(announcement.getHref());
			m.find();
			String newsId = m.group(1); 
			GetMethod getMethod4Date = new GetMethod("http://wot.kongzhong.com/news/news_" + newsId + ".html");
			try {
				httpClient.executeMethod(getMethod4Date);
				String html4Date = getHtml(getMethod4Date, null);
				Matcher matcher4Date = Pattern.compile("日期：(\\d+)年(\\d+)月(\\d+)日").matcher(html4Date);
				if (matcher4Date.find()) {
					announcement.setDate(getDate(matcher4Date.group(1), matcher4Date.group(2), matcher4Date.group(3)));
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				getMethod.releaseConnection();
			}
			
			res.add(announcement);
		}
		
		return res;
	}
	
	protected String getPageURL() {
		return "http://wot.kongzhong.com/news/newgg.html";
	}
	
	@Override
	public String getGameName() {
		return "坦克世界";
	}

}

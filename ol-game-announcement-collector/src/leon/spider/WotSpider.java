package leon.spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import leon.model.Announcement;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


/**
 * 坦克世界
 * @author Isun
 *
 */
public class WotSpider extends Spider {
	
	@Override
	public List<Announcement> getAnnouncements() {
		final HttpClient httpClient = new HttpClient();
		final GetMethod getMethod = new GetMethod(getPageURL());
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000);  
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 6000);
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
			res.add(announcement);
		}
		
		for (final Announcement announcement : res) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Matcher m = Pattern.compile("newsid=(\\d+)").matcher(announcement.getHref());
					m.find();
					String newsId = m.group(1);

					final HttpClient httpClient4Date = new HttpClient();
					GetMethod getMethod4Date = new GetMethod("http://wot.kongzhong.com/news/news_" + newsId + ".html");
					httpClient4Date.getHttpConnectionManager().getParams().setConnectionTimeout(60000);  
					getMethod4Date.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
					try {
						httpClient4Date.executeMethod(getMethod4Date);
						String html4Date = getHtml(getMethod4Date, null);
						Matcher matcher4Date = Pattern.compile("日期：(\\d+)年(\\d+)月(\\d+)日").matcher(html4Date);
						if (matcher4Date.find()) {
							announcement.setDate(getDate(matcher4Date.group(1), matcher4Date.group(2), matcher4Date.group(3)));
						}
					} catch(Exception e) {
						e.printStackTrace();
					} finally {
						getMethod4Date.releaseConnection();
					}
					
					Integer aInteger;
					aInteger.n
					

					
					
					System.out.println(spider.getGameName() + " 开始抓取...");
					
					Date spiderBegin = new Date();

					List tmp = spider.getAnnouncements();
					res.addAll(tmp);
					counter.put(spider.getClass(), 1);
					
					System.out.println(spider.getGameName() + " 抓取完毕，用时 " + (new Date().getTime() - spiderBegin.getTime()) + "ms, 共 " + tmp.size() + " 条");
					System.out.println("总进度: " + counter.size() + "/" + spiders.length);

					if (counter.size() == spiders.length) {
						try {
							printHtml();
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println("\n抓取完毕，总共用时: " + (new Date().getTime() - begin.getTime()) + "ms");
					}
				}
			}).start();
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

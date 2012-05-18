package leon.spider;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import leon.model.Announcement;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.io.IOUtils;


public abstract class Spider {
	
	public abstract String getGameName();

	
	/**
	 * 获取最近若干条公告
	 * @return
	 */
	public abstract List<Announcement> getAnnouncements();

	
	/**
	 * 从html中判断字符编码并将内容转成String返回
	 * @param method http方法
	 * @param proposedCharset 推荐的charset
	 * @return
	 * @throws IOException
	 */
	public static String getHtml(HttpMethodBase method, String proposedCharset) throws IOException {
		byte[] contentInByte = IOUtils.toByteArray(method.getResponseBodyAsStream());
		Charset charset = null;
		try {
			charset = Charset.forName(proposedCharset);
		} catch (Exception e) {}
		if (charset == null) {
			Header header = method.getResponseHeader("Content-Type");
			if (header != null) {
				Matcher matcher = Pattern.compile("(?i)charset=([-_\\w]+)").matcher(header.getValue());
				if (matcher.find()) {
					try {
						charset = Charset.forName(matcher.group(1));
					} catch (Exception e) {}
				}
			}
		}
		if (charset == null) {
			String tmpHtml = new String(contentInByte, "UTF-8");
			Matcher matcher = Pattern.compile("(?i)charset\\s*=\\s*['\"]?([-_\\w]+)").matcher(tmpHtml);
			if (matcher.find()) {
				try {
					charset = Charset.forName(matcher.group(1));
				} catch (Exception e) {}
			}
		}
		if (charset == null) {
			charset = Charset.forName("UTF-8");
		}
		//System.out.println(charset.name());
		return new String(contentInByte, charset);
	}
	
	@SuppressWarnings("deprecation")
	public Date getDate(String year, String month, String date) {
		Integer i_year = Integer.parseInt(year, 10);
		Integer i_month = Integer.parseInt(month, 10);
		Integer i_date = Integer.parseInt(date, 10);
		return new Date(i_year - 1900, i_month - 1, i_date);
	}
}

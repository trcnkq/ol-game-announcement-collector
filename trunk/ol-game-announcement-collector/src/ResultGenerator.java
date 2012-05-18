import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import spider.Spider;
import bean.Announcement;


@SuppressWarnings("unchecked")
public class ResultGenerator {
	
	/**
	 * 为空则使用所有spiders,否则使用该处指定spiders
	 */
	private Spider[] spiders = {
	};

	private final List<Announcement> res = new ArrayList<Announcement>();
	
	////////////////////////////////////////////////////////////////////
	
	
	private void genarate() throws FileNotFoundException, UnsupportedEncodingException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		if (spiders.length == 0) {
			List<Spider> spiderList = new ArrayList<Spider>();
			
			ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
			provider.addIncludeFilter(new AssignableTypeFilter(Spider.class));
			
			Set<BeanDefinition> components = provider.findCandidateComponents("spider");
			for (BeanDefinition component : components) {
				Class clazz = Class.forName(component.getBeanClassName());
				spiderList.add((Spider) clazz.newInstance());
			}
			spiders = spiderList.toArray(new Spider[0]);
		}

		
		final Map counter = new ConcurrentHashMap();
		final Date begin = new Date();
		
		for (final Spider spider : spiders) {
			new Thread(new Runnable() {
				@Override
				public void run() {
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
	}
	
	private void printHtml() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Collections.sort(res, new Comparator<Announcement>() {
			@Override
			public int compare(Announcement o1, Announcement o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		});
		
		File file = new File("公告.html");
		FileOutputStream fos = new FileOutputStream(file);
		Writer out = new OutputStreamWriter(fos, "UTF-8");
		
		out.write("<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" +
				"<style type='text/css' title='currentStyle'>" +
				"@import 'http://datatables.net/release-datatables/media/css/demo_page.css'; @import 'http://datatables.net/media/css/header.ccss';	@import 'http://datatables.net/release-datatables/media/css/demo_table_jui.css';@import 'http://datatables.net/release-datatables/examples/examples_support/themes/smoothness/jquery-ui-1.8.4.custom.css';" +
				"</style>" +
				"<style type='text/css' media='screen'>td{padding:5px} a:link{text-decoration:none}</style>" +
				"<script type='text/javascript' src='http://datatables.net/release-datatables/media/js/jquery.js'></script>" +
				"<script type='text/javascript' src='http://datatables.net/release-datatables/media/js/jquery.dataTables.js'></script>" +
				"<script type='text/javascript'>$(document).ready(function() {$('table').dataTable({'iDisplayLength': 25,'bJQueryUI': true,'sPaginationType': 'full_numbers','aoColumns': [{'sType': 'html'},{'sType': 'html'},{'sType': 'date'}],'aaSorting': [[ 2, 'desc' ]],});} );</script>" +
				"</head><body><table class='display' style='width:100%' cellpadding='0' border='0'><thead><tr><th>游戏</th><th>标题</th><th>发布时间</th></thead><tbody>");
		for (Announcement announcement : res) {
			out.write("<tr><td>" + announcement.getGameName() + "</td><td><a target='_blank' href='" + announcement.getHref() + "'>" + announcement.getTitle() + "</a></td><td>" + sdf.format(announcement.getDate()) + "</td></tr>");
		}
		out.write("</tbody></table></body></html>");
		
		out.close();
		fos.close();
		
	}

	///////////////////////////////////////////////////////////
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		ResultGenerator rg = new ResultGenerator();
		rg.genarate();
	}

}

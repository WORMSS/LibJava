package net.wormss.comm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;

public class EasyHttp
{
	private List<Header> headers;
	public EasyHttp()
	{ 
		headers = new ArrayList<Header>();
	}
	
	public EasyHttp addHeader(String name, String value)
	{
		return addHeader(new BasicHeader(name, value));
	}
	
	public EasyHttp addHeader(Header header)
	{
		headers.add(header);
		return this;
	}
	
	public EasyHttp setHeaders(List<Header> headers)
	{
		this.headers = headers;
		return this;
	}
	
	public EasyHttp setHeaders(Header[] headers)
	{
		return setHeaders(Arrays.asList(headers));
	}
	
	public HttpGet get(String url)
	{
		HttpGet http = new HttpGet(url);
		http.setHeaders(headers.toArray(new Header[headers.size()]));
		return http;
	}
	
	public HttpPost post(String url)
	{
		HttpPost http = new HttpPost(url);
		http.setHeaders(headers.toArray(new Header[headers.size()]));
		return http;
	}
}

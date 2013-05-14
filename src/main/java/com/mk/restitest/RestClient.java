package com.mk.restitest;
import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author MaruthaMuthu
 *
 */
public abstract class RestClient {


	/**
	 * @param path
	 * @param headers
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	protected HttpResponse get(String path, List<Header> headers)throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(path);
		if(headers!=null){
			request.setHeaders(headers.toArray(new Header[headers.size()]));
		}
		return execute(request);
	}
	
	/**
	 * @param path
	 * @param headers
	 * @param requestBody
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	protected HttpResponse post(String path, List<Header> headers, String requestBody) throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost(path);
		if(headers!=null){
			request.setHeaders(headers.toArray(new Header[headers.size()]));
		}
		request.setEntity(new StringEntity(requestBody, "application/xml", "ISO-8859-1"));
		return execute(request);
	}
	
	/**
	 * @param path
	 * @param headers
	 * @param requestBody
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	protected HttpResponse put(String path, List<Header> headers, String requestBody) throws ClientProtocolException, IOException {
		HttpPut request = new HttpPut(path);
		request.setHeaders(headers.toArray(new Header[headers.size()]));
		request.setEntity(new StringEntity(requestBody, "application/xml", "ISO-8859-1"));
		return execute(request);
	}

	/**
	 * @param path
	 * @param headers
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	protected HttpResponse delete(String path, List<Header> headers) throws ClientProtocolException, IOException {
		HttpDelete request = new HttpDelete(path);
		request.setHeaders(headers.toArray(new Header[headers.size()]));
		return execute(request);
	}
	/**
	 * @param request
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResponse execute(HttpRequestBase request) throws ClientProtocolException, IOException{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);
		httpClient.getConnectionManager().shutdown();
		return response;
	}
	}

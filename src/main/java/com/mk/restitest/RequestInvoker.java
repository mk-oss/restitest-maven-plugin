package com.mk.restitest;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicHeader;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author MaruthaMuthu
 *
 */
public class RequestInvoker extends RestClient {
	String appPath;

	RequestInvoker(String appPath) {
		this.appPath = appPath;
	}

	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public HttpResponse process(Tests.Test.Request request) throws Exception {
		String requestBody = getRequestBody(request);
		String path = appPath + request.getPath();
		List<Header> headers = getHeaders(request);
		if (request.getMethod().equalsIgnoreCase(HttpGet.METHOD_NAME)) {
			return get(path, headers);
		} else if (request.getMethod().equalsIgnoreCase(HttpPost.METHOD_NAME)) {
			return post(path, headers, requestBody);
		} else if (request.getMethod().equalsIgnoreCase(HttpPut.METHOD_NAME)) {
			return put(path, headers, requestBody);
		} else if (request.getMethod().equalsIgnoreCase(HttpDelete.METHOD_NAME)) {
			return delete(path, headers);
		} else {
			return null;
		}
	}

	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private String getRequestBody(Tests.Test.Request request) throws Exception {
		String body = null;
		for (Element ele : request.getAny()) {
			if (ele.getTagName().equals("payload")) {
				body = getContentAsString(ele.getFirstChild());
			}
		}
		return body;
	}

	/**
	 * @param node
	 * @return
	 * @throws Exception
	 */
	private String getContentAsString(Node node) throws Exception {
		StringBuilder builder = new StringBuilder();
		StringWriter writer = new StringWriter();
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.transform(new DOMSource(node), new StreamResult(writer));
		builder.append(writer.toString());
		return builder.toString();
	}

	/**
	 * @param request
	 * @return
	 */
	private List<Header> getHeaders(Tests.Test.Request request) {
		List<Header> headers = new ArrayList<Header>();
		for (Tests.Test.Request.Header header : request.getHeaders()) {
			String name = header.getHeaderName();
			String val = header.getHeaderValue();
			if ((name != null && !name.isEmpty())
					&& (val != null && !val.isEmpty())) {
				headers.add(new BasicHeader(name, val));
			}
		}
		return headers;

	}
}

package com.mk.restitest;

import java.io.File;

import org.apache.http.HttpResponse;
import org.junit.Test;

public class restTest extends RestClient {
	final String path = "http://localhost:8080/rest/";
	final File file = new File("src/main/java/com/mk/restitest/Itest.xml");

	@Test
	public void test() throws Exception {
		Tests tests = JaxbFactory.createTestsObject(file);
		HttpResponse response = null;
		RequestInvoker invoker = new RequestInvoker(path);
		for (Tests.Test test : tests.getTest()) {
			response = invoker.process(test.getRequest());
			Tests.Test.Asserts asserts = test.getAsserts();
			System.out.println(response.getStatusLine().getStatusCode());
			ResultProcessor.buildResult(asserts, response);
		}
		JaxbFactory.createTestsXml(tests);
	}

}

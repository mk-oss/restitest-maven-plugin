package com.mk.restitest;

import org.apache.http.HttpResponse;

/**
 * @author MaruthaMuthu
 *
 */
public class ResultProcessor {
	
	private static final String HEADER = "header"; 
	private static final String STATUSLINE = "statusline"; 
	private static final String STATUSCODE = "statuscode"; 
	
	/**
	 * @param asserts
	 * @param response
	 */
	public static void buildResult(Tests.Test.Asserts asserts, HttpResponse response){
		for(Tests.Test.Asserts.Assertion assertion : asserts.getAssertList()){
			if(assertion.getElement().equalsIgnoreCase(HEADER)){
				String headerName = assertion.getKey();
				String headerValue = assertion.getValue();
				if(response.containsHeader(headerName) && response.getFirstHeader(headerName).getValue().equalsIgnoreCase(headerValue)){
					assertion.setResult(true);
				}
			}else if(assertion.getElement().equalsIgnoreCase(STATUSLINE)){
				int statusCode = response.getStatusLine().getStatusCode();
				if(assertion.getKey().equalsIgnoreCase(STATUSCODE) && statusCode == Integer.valueOf(assertion.getValue())){
					assertion.setResult(true);
				}
			}else{
				assertion.setResult(false);

			}
		}
	}
}

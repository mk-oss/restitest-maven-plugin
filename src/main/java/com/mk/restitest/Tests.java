package com.mk.restitest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.w3c.dom.Element;

@XmlRootElement(name = "tests")
public class Tests {

	@XmlElement
	private List<Test> test = new ArrayList<Test>();

	/**
	 * @return the tests
	 */
	public List<Test> getTest() {
		return test;
	}

	/**
	 * @param tests
	 *            the tests to set
	 */
	public void setTests(List<Test> test) {
		this.test = test;
	}

	@XmlType
	public static class Test {

		private String id;
		private String resource;
		private String scenario;
		private Request request;
		private Asserts asserts;

		/**
		 * @return the asserts
		 */
		public Asserts getAsserts() {
			return asserts;
		}

		/**
		 * @param asserts the asserts to set
		 */
		public void setAsserts(Asserts asserts) {
			this.asserts = asserts;
		}

		/**
		 * @return the id
		 */
		@XmlAttribute
		public String getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the resource
		 */
		@XmlAttribute
		public String getResource() {
			return resource;
		}

		/**
		 * @param resource
		 *            the resource to set
		 */
		public void setResource(String resource) {
			this.resource = resource;
		}

		/**
		 * @return the scenario
		 */
		@XmlAttribute
		public String getScenario() {
			return scenario;
		}

		/**
		 * @param scenario
		 *            the scenario to set
		 */
		public void setScenario(String scenario) {
			this.scenario = scenario;
		}

		/**
		 * @return the request
		 */
		@XmlElement
		public Request getRequest() {
			return request;
		}

		/**
		 * @param request
		 *            the request to set
		 */
		public void setRequest(Request request) {
			this.request = request;
		}


		@XmlType
		public static class Request {

			private String path;
			private String method;
			private List<Header> Headers = new ArrayList<Header>();
			private List<Element> any = new ArrayList<Element>();;

			/**
			 * @return the path
			 */
			@XmlAttribute
			public String getPath() {
				return path;
			}

			/**
			 * @param path
			 *            the path to set
			 */
			public void setPath(String path) {
				this.path = path;
			}

			/**
			 * @return the method
			 */
			@XmlAttribute
			public String getMethod() {
				return method;
			}

			/**
			 * @param method
			 *            the method to set
			 */
			public void setMethod(String method) {
				this.method = method;
			}

			/**
			 * @return the headers
			 */
			public List<Header> getHeaders() {
				return Headers;
			}

			/**
			 * @param headers
			 *            the headers to set
			 */
			public void setHeaders(List<Header> headers) {
				Headers = headers;
			}

			/**
			 * @return the any
			 */
			@XmlAnyElement
			public List<Element> getAny() {
				return any;
			}

			/**
			 * @param any
			 *            the any to set
			 */
			public void setAny(List<Element> any) {
				this.any = any;
			}

			@XmlType
			public static class Header {

				private String headerName;
				private String headerValue;

				/**
				 * @return the headerName
				 */
				@XmlAttribute
				public String getHeaderName() {
					return headerName;
				}

				/**
				 * @param headerName
				 *            the headerName to set
				 */
				public void setHeaderName(String headerName) {
					this.headerName = headerName;
				}

				/**
				 * @return the headerValue
				 */
				@XmlAttribute
				public String getHeaderValue() {
					return headerValue;
				}

				/**
				 * @param headerValue
				 *            the headerValue to set
				 */
				public void setHeaderValue(String headerValue) {
					this.headerValue = headerValue;
				}
			}
		}

		@XmlType
		public static class Asserts {

			private List<Assertion> assertList = new ArrayList<Assertion>();


			@XmlType
			public static class Assertion {

				private String key;
				private String value;
				private String errormsg;
				private String id;
				private String element;
				private boolean result;

				/**
				 * @return the key
				 */
				@XmlAttribute
				public String getKey() {
					return key;
				}

				/**
				 * @param key
				 *            the key to set
				 */
				public void setKey(String key) {
					this.key = key;
				}

				/**
				 * @return the value
				 */
				@XmlAttribute
				public String getValue() {
					return value;
				}

				/**
				 * @param value
				 *            the value to set
				 */
				public void setValue(String value) {
					this.value = value;
				}

				/**
				 * @return the errormsg
				 */
				@XmlAttribute
				public String getErrormsg() {
					return errormsg;
				}

				/**
				 * @param errormsg
				 *            the errormsg to set
				 */
				public void setErrormsg(String errormsg) {
					this.errormsg = errormsg;
				}
				@XmlAttribute
				public String getId() {
					return id;
				}

				public void setId(String id) {
					this.id = id;
				}
				@XmlAttribute
				public String getElement() {
					return element;
				}

				public void setElement(String element) {
					this.element = element;
				}
				@XmlAttribute
				public boolean isResult() {
					return result;
				}

				public void setResult(boolean result) {
					this.result = result;
				}

			}

			@XmlElement(name = "assert")
			public List<Assertion> getAssertList() {
				return assertList;
			}


			public void setAssertList(List<Assertion> assertList) {
				this.assertList = assertList;
			}
		}
	}
}

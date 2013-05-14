package com.mk.restitest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Result")
public class ResultList {

	
	private List<Result> test = new ArrayList<Result>();
	
	/**
	 * @return the results
	 */
	public List<Result> getTest() {
		return test;
	}

	/**
	 * @param results the results to set
	 */
	@XmlElement
	public void setTest(List<Result> test) {
		this.test = test;
	}

	@XmlType
	public static class Result{
		private String testId;
		private String resource;
		private String scenario;
		private AssertList assertList;
		
		public static class AssertList{
			
			private List<Assert> testAssertList = new ArrayList<Assert>();
			/**
			 * @return the test
			 */
			public List<Assert> getTestAssertList() {
				return testAssertList;
			}
			/**
			 * @param test the test to set
			 */
			@XmlElement(name="assert")
			public void setTestAssertList(List<Assert> testAssertList) {
				this.testAssertList = testAssertList;
			}
			@XmlType
			public static class Assert{
				private String assertId;
				private String key;
				private String expected;
				private String actual;
				private boolean status;
				/**
				 * @return the assertId
				 */
				public String getAssertId() {
					return assertId;
				}
				/**
				 * @param assertId the assertId to set
				 */
				@XmlAttribute
				public void setAssertId(String assertId) {
					this.assertId = assertId;
				}
				/**
				 * @return the key
				 */
				public String getKey() {
					return key;
				}
				/**
				 * @param key the key to set
				 */
				@XmlAttribute
				public void setKey(String key) {
					this.key = key;
				}
				/**
				 * @return the expected
				 */
				public String getExpected() {
					return expected;
				}
				/**
				 * @param expected the expected to set
				 */
				@XmlAttribute
				public void setExpected(String expected) {
					this.expected = expected;
				}
				/**
				 * @return the actual
				 */
				public String getActual() {
					return actual;
				}
				/**
				 * @param actual the actual to set
				 */
				@XmlAttribute
				public void setActual(String actual) {
					this.actual = actual;
				}
				/**
				 * @return the status
				 */
				public boolean isStatus() {
					return status;
				}
				/**
				 * @param status the status to set
				 */
				@XmlAttribute
				public void setStatus(boolean status) {
					this.status = status;
				}
			}
		}

		/**
		 * @return the testId
		 */
		public String getTestId() {
			return testId;
		}

		/**
		 * @param testId the testId to set
		 */
		@XmlAttribute
		public void setTestId(String testId) {
			this.testId = testId;
		}

		/**
		 * @return the resource
		 */
		public String getResource() {
			return resource;
		}

		/**
		 * @param resource the resource to set
		 */
		@XmlAttribute
		public void setResource(String resource) {
			this.resource = resource;
		}

		/**
		 * @return the scenario
		 */
		public String getScenario() {
			return scenario;
		}

		/**
		 * @param scenario the scenario to set
		 */
		@XmlAttribute
		public void setScenario(String scenario) {
			this.scenario = scenario;
		}

		/**
		 * @return the assertList
		 */
		public AssertList getAssertList() {
			return assertList;
		}

		/**
		 * @param assertList the assertList to set
		 */
		@XmlElement(name="asserts")
		public void setAssertList(AssertList assertList) {
			this.assertList = assertList;
		}
	}
}

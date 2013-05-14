package com.mk.restitest;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author MaruthaMuthu
 * 
 */
public class JaxbFactory {
	/**
	 * @param file
	 * @return
	 */
	public static Tests createTestsObject(File file) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Tests.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Tests tests = (Tests) jaxbUnmarshaller.unmarshal(file);
			return tests;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param tests
	 */
	public static void createTestsXml(Tests tests) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Tests.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(tests, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

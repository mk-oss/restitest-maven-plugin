package com.mk.restitest;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Goal which starts integration test framework
 * 
 * 
 */
@Mojo(name = "test", defaultPhase = LifecyclePhase.INTEGRATION_TEST)
public class RestIntegrationTest extends AbstractMojo {

	@Parameter(property = "test.file", defaultValue = "src/main/java/com/mk/restitest/Itest.xml", required = true)
	private String filePath;

	@Parameter(property = "test.host", defaultValue = "http://localhost:8080/", required = true)
	private String path;

	/* (non-Javadoc)
	 * @see org.apache.maven.plugin.AbstractMojo#execute()
	 */
	public void execute() throws MojoExecutionException {
		getLog().info("restItest started ..\nInput File:" + filePath);
		try {
			File file = new File(filePath);
			if (file.exists() && file.canRead()) {
				Tests tests = JaxbFactory.createTestsObject(file);
				RequestInvoker invoker = new RequestInvoker(path);
				for (Tests.Test test : tests.getTest()) {
					ResultProcessor.buildResult(test.getAsserts(),
							invoker.process(test.getRequest()));
				}
				JaxbFactory.createTestsXml(tests);
			}
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}
}

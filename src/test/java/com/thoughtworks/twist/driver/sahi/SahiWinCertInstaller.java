/*************************GO-LICENSE-START*********************************
 * Copyright 2015 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.twist.driver.sahi;

public class SahiWinCertInstaller implements CertInstallerStrategy {

	public void installCertificateFrom(String sahiBasePath) {
		 try {
		     CommandUtils.execute(addCertCommand(sahiBasePath));
		 } catch (Exception e) {
			 System.err.println(CERT_INSTALL_ERROR + certFile(sahiBasePath));
		 }
	}

	private String[] addCertCommand(String sahiBasePath) {
		String certutil = "certutil.exe";
		String action = "-addstore";
		String user = "-user";
		String certStore = "root";
		return new String[]{certutil, action, user, certStore, certFile(sahiBasePath)};
	}

	protected String certFile(String sahiBasePath) {
		return sahiBasePath + CERT_FILE;
	}

}

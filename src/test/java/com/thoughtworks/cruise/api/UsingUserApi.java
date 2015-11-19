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

package com.thoughtworks.cruise.api;

import com.thoughtworks.cruise.Urls;
import com.thoughtworks.cruise.client.TalkToCruise;
import com.thoughtworks.cruise.client.TalkToCruise.CruiseResponse;
import org.apache.commons.httpclient.NameValuePair;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UsingUserApi {
	private TalkToCruise talkToCruise;

	public UsingUserApi(TalkToCruise talkToCruise){
		this.talkToCruise = talkToCruise;
    }

	public void disableUser(String userName) throws Exception {
		NameValuePair operation = new NameValuePair("operation", "Disable");
		NameValuePair selected = new NameValuePair("selected[]", userName);
		CruiseResponse response = talkToCruise.post(Urls.urlFor("/admin/users/operate"), operation, selected);
		assertThat(response.getStatus(), is(302));
	}

	@com.thoughtworks.gauge.Step("Attempt to delete <username> user and should return <httpStatus>")
	public void attemptToDeleteUserAndShouldReturn(String username, String httpStatus) throws Exception {
		CruiseResponse response = talkToCruise.delete(Urls.urlFor(String.format("/api/users/%s", username)), true);
		assertThat(response.getStatus(), is(Integer.parseInt(httpStatus)));
	}
}

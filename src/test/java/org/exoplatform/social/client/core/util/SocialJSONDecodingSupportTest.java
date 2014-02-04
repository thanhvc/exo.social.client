/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.social.client.core.util;

import java.util.Map;

import org.exoplatform.social.client.api.model.RestActivity;
import org.exoplatform.social.client.api.util.SocialJSONDecodingSupport;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jun 30, 2011  
 */
public class SocialJSONDecodingSupportTest {

  @BeforeMethod
  public void setUp() throws Exception {
    
  }
  
  @AfterMethod
  public void tearDown() throws Exception {
    
  }
  
  @Test
  public void testJSONParserWithClassType() throws Exception {
    String jsonActivity = "{\"numberOfComments\":1,\"identityId\":\"d5039b437f0001010011fd153a4fcbd8\",\"liked\":true,}";
    RestActivity model = SocialJSONDecodingSupport.parser(RestActivity.class, jsonActivity);
    assertEquals(model.getIdentityId(), "d5039b437f0001010011fd153a4fcbd8");
  }
  
  @Test
  public void testJSONParser() throws Exception {
    String jsonActivity = "{\"numberOfComments\":1,\"identityId\":\"d5039b437f0001010011fd153a4fcbd8\",\"liked\":true,}";
    Map modelMap = SocialJSONDecodingSupport.parser(jsonActivity);
    assertEquals(modelMap.get("identityId"), "d5039b437f0001010011fd153a4fcbd8");
  }


  @Test
  public void testJsonArrayParser() throws Exception {
    String jsonActivity = "[" +
    		                      "{\"numberOfComments\":1,\"identityId\":\"d5039b437f0001010011fd153a4fcbd8\",\"liked\":true,}," +
    		                      "{\"numberOfComments\":2,\"identityId\":\"d5039b437f0001010011fd153a4fcba8\",\"liked\":false,}" +
    		                   "]";
    RestActivity model1 = SocialJSONDecodingSupport.JSONArrayObjectParser(RestActivity.class, jsonActivity).get(0);
    RestActivity model2 = SocialJSONDecodingSupport.JSONArrayObjectParser(RestActivity.class, jsonActivity).get(1);
    assertEquals(model1.getIdentityId(), "d5039b437f0001010011fd153a4fcbd8");
    assertEquals(model2.getIdentityId(), "d5039b437f0001010011fd153a4fcba8");
    
    
    String jsonActivity1 = "{\"activities\":[" +
    		                              "{" +
    		                              "\"appId\":null,\"identityId\":\"f845f6ed7f000101003ed4d98a09beb3\"," +
    		                              "\"totalNumberOfComments\":0,\"liked\":false,\"templateParams\":{}," +
    		                              "\"postedTime\":1309839511830,\"type\":\"DEFAULT_ACTIVITY\"," +
    		                              "\"posterIdentity\":null,\"activityStream\":null," +
    		                              "\"id\":\"f884d11a7f000101000230e5c0e8a602\"," +
    		                              "\"title\":\"hello\",\"priority\":null," +
    		                              "\"createdAt\":\"Tue Jul 5 11:18:31 +0700 2011\"," +
    		                              "\"likedByIdentities\":null,\"titleId\":null,\"comments\":null}" +
    		                   "]}";

    JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonActivity1);
    JSONArray jsonArray =  (JSONArray)jsonObject.get("activities");
    RestActivity model3 = SocialJSONDecodingSupport.JSONArrayObjectParser(RestActivity.class, jsonArray.toJSONString()).get(0);
    assertEquals(model3.getIdentityId(), "f845f6ed7f000101003ed4d98a09beb3");
  }
}

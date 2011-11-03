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

import junit.framework.Assert;

import org.exoplatform.social.client.api.model.Activity;
import org.exoplatform.social.client.core.model.ActivityImpl;
import org.exoplatform.social.client.core.spi.Provider;
import org.exoplatform.social.client.core.spi.utils.Stopwatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jun 30, 2011  
 */
public class SocialJSONDecodingSupportTest {

  private Stopwatch stopwatch = new Stopwatch();
  @Before
  public void setUp() throws Exception {
    
  }
  
  @After
  public void tearDown() throws Exception {
    stopwatch.reset();
  }
  
  @Test
  public void testJSONParserWithProvider1000times() throws Exception {
    stopwatch.start();
    Provider<Activity> provider = new Provider<Activity>() {

      @Override
      public Activity get() {
        return new ActivityImpl();
      }

      @Override
      public Class<Activity> getClazz() {
        return Activity.class;
      }

    };
    
    
    String jsonActivity = "{\"numberOfComments\":1,\"identityId\":\"d5039b437f0001010011fd153a4fcbd8\",\"liked\":true,\"comment\":\"My comments\"}";
    for (int i = 0; i < 1000; i++) {
      Activity model = (ActivityImpl) SocialJSONDecodingSupport.parser(provider, jsonActivity);
      Assert.assertEquals("d5039b437f0001010011fd153a4fcbd8", model.getIdentityId());
    }
    stopwatch.stop();
    System.out.println("testJSONParserWithProvider1000times()::" + stopwatch.toString());
    
  }
  
  @Test
  public void testJSONParserWithProvider() throws Exception {
    stopwatch.start();
    String jsonActivity = "{\"numberOfComments\":1,\"identityId\":\"d5039b437f0001010011fd153a4fcbd8\",\"liked\":true,\"comment\":\"My comments\"}";
    Activity model = (ActivityImpl) SocialJSONDecodingSupport.parser(new Provider<Activity>() {

      @Override
      public Activity get() {
        return new ActivityImpl();
      }

      @Override
      public Class<Activity> getClazz() {
        return Activity.class;
      }

    }, jsonActivity);
    Assert.assertEquals("d5039b437f0001010011fd153a4fcbd8", model.getIdentityId());
    stopwatch.stop();
    System.out.println("testJSONParserWithProvider()::" + stopwatch.toString());
    
  }
  
  @Test
  public void testJSONParserWithProvider2() throws Exception {
    stopwatch.start();
    String jsonActivity = "{\"numberOfComments\":1,\"identityId\":\"d5039b437f0001010011fd153a4fcbd8\",\"liked\":true}";
    Activity model = (ActivityImpl) SocialJSONDecodingSupport.parser(new Provider<Activity>() {

      @Override
      public Activity get() {
        return new ActivityImpl();
      }

      @Override
      public Class<Activity> getClazz() {
        return Activity.class;
      }

    }, jsonActivity);
    Assert.assertEquals("d5039b437f0001010011fd153a4fcbd8", model.getIdentityId());
    stopwatch.stop();
    System.out.println("testJSONParserWithProvider2()::" + stopwatch.toString());
    
  }
  
  @Test
  public void testJSONParserWithProvider3() throws Exception {
    stopwatch.start();
    String jsonActivity = "{\"numberOfComments\":1,\"identityId\":\"d5039b437f0001010011fd153a4fcbd8\",\"liked\":true,}";
    Activity model = (ActivityImpl) SocialJSONDecodingSupport.parser(new Provider<Activity>() {

      @Override
      public Activity get() {
        return new ActivityImpl();
      }

      @Override
      public Class<Activity> getClazz() {
        return Activity.class;
      }

    }, jsonActivity);
    Assert.assertEquals("d5039b437f0001010011fd153a4fcbd8", model.getIdentityId());
    stopwatch.stop();
    System.out.println("testJSONParserWithProvider3()::" + stopwatch.toString());
    
  }
  
  @Test
  public void testJSONParserWithClassType1000times() throws Exception {
    stopwatch.start();
    String jsonActivity = "{\"numberOfComments\":1,\"identityId\":\"d5039b437f0001010011fd153a4fcbd8\",\"liked\":true}";
    for (int i = 0; i < 1000; i++) {
      ActivityImpl model = SocialJSONDecodingSupport.parser(ActivityImpl.class, jsonActivity);
      Assert.assertEquals("d5039b437f0001010011fd153a4fcbd8", model.getIdentityId());
      
    }
    
    stopwatch.stop();
    System.out.println("testJSONParserWithClassType1000times()::" + stopwatch.toString());
    
  }
  
  @Test
  public void testJSONParserWithClassType() throws Exception {
    stopwatch.start();
    String jsonActivity = "{\"numberOfComments\":1,\"identityId\":\"d5039b437f0001010011fd153a4fcbd8\",\"liked\":true}";
    ActivityImpl model = SocialJSONDecodingSupport.parser(ActivityImpl.class, jsonActivity);
    Assert.assertEquals("d5039b437f0001010011fd153a4fcbd8", model.getIdentityId());
    stopwatch.stop();
    System.out.println("testJSONParserWithClassType()::" + stopwatch.toString());
    
  }
  
  @Test
  public void testJSONParserWithClassType1() throws Exception {
    stopwatch.start();
    String jsonActivity = "{\"numberOfComments\":1,\"identityId\":\"d5039b437f0001010011fd153a4fcbd8\",\"liked\":true,\"comment\":\"My comments\"}";
    ActivityImpl model = SocialJSONDecodingSupport.parser(ActivityImpl.class, jsonActivity);
    Assert.assertEquals("d5039b437f0001010011fd153a4fcbd8", model.getIdentityId());
    stopwatch.stop();
    System.out.println("testJSONParserWithClassType1()::" + stopwatch.toString());
    
  }
  
  
  
  @Test
  public void testJSONParser() throws Exception {
    stopwatch.start();
    String jsonActivity = "{\"numberOfComments\":1,\"identityId\":\"d5039b437f0001010011fd153a4fcbd8\",\"liked\":true,}";
    Map modelMap = SocialJSONDecodingSupport.parser(jsonActivity);
    Assert.assertEquals("d5039b437f0001010011fd153a4fcbd8", modelMap.get("identityId"));
    stopwatch.stop();
    System.out.println("testJSONParser()::" + stopwatch.toString());
  }
  
 
 
}

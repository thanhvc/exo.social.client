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
package org.exoplatform.social.client.core.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.exoplatform.social.client.api.model.RestActivity;
import org.exoplatform.social.client.api.service.ActivityService;
import org.exoplatform.social.client.api.service.ServiceException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit Test for {@link org.exoplatform.social.client.api.service.ActivityService}'s implementation.
 *
 * @author <a href="http://hoatle.net">hoatle (hoatlevan at gmail dot com)</a>
 * @since Jul 3, 2011
 */

public class ActivityServiceTest {
  
  @Mock private ActivityService<RestActivity> activityService;
   
  @Mock private RestActivity activity;

  @BeforeMethod
  public void setUp() {
    //using when you configure the @Mock annotation
    MockitoAnnotations.initMocks(this);
    //activityService = mock(ActivityServiceImpl.class);
    //activity = mock(RestActivityImpl.class);
  }

 
  @Test(groups = "ActivityServiceMock")
  public void testCreateActivity() {
    createMock();
    
    when(activityService.create(activity)).thenReturn(activity);
    
    RestActivity got = activityService.create(activity);
    assertThat(got.getId(), equalTo("123456789"));
    assertThat(got.getTitle(), equalTo("mockito"));
    
    verify(activity).getId();
    verify(activity).getTitle();
    verify(activityService).create(activity);
  }
  
  @Test(groups = "ActivityServiceMock")
  public void testGetActivity() {
    createMock();
    when(activityService.get("123456789")).thenReturn(activity);
    RestActivity got = activityService.get("123456789");
    
    assertThat(got.getId(), equalTo("123456789"));
    assertThat(got.getTitle(), equalTo("mockito"));
    
    verify(activity).getId();
    verify(activity).getTitle();
    verify(activityService).get("123456789");
  }
  
  @Test(groups = "ActivityServiceMock")
  public void testUpdateActivity() {
    createMock();
    when(activityService.update(activity)).thenThrow(new ServiceException(ActivityServiceImpl.class,"Do Not Support",null));
    RestActivity got = activityService.update(activity);
    Assert.fail("ServiceException must be thrown");
    
    verify(activityService).update(activity);
  }
  
  @Test(groups = "ActivityServiceMock")
  public void testDeleteActivity() {
    createMock();
    when(activityService.delete(activity)).thenReturn(activity);
    RestActivity got = activityService.delete(activity);
    
    assertThat(got.getId(), equalTo("123456789"));
    assertThat(got.getTitle(), equalTo("mockito"));
    
    verify(activity).getId();
    verify(activity).getTitle();
    verify(activityService).get("123456789");
  }
  
  /**
   * Creates the ActivityMock object.
   */
  private void createMock() {
    when(activity.getId()).thenReturn("123456789");
    when(activity.getTitle()).thenReturn("mockito");
  }
  
}

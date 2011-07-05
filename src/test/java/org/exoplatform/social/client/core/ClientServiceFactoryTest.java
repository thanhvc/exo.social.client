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
package org.exoplatform.social.client.core;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.exoplatform.social.client.api.model.Activity;
import org.exoplatform.social.client.api.model.Identity;
import org.exoplatform.social.client.api.service.ActivityService;
import org.exoplatform.social.client.api.service.IdentityService;
import org.exoplatform.social.client.api.service.VersionService;
import org.exoplatform.social.client.core.service.ActivityServiceImpl;
import org.exoplatform.social.client.core.service.IdentityServiceImpl;
import org.exoplatform.social.client.core.service.VersionServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link ClientServiceFactory}.
 *
 * @author <a href="http://hoatle.net">hoatle (hoatlevan at gmail dot com)</a>
 * @since Jul 1, 2011
 */
public class ClientServiceFactoryTest {

  @Before
  public void setUp() {
    
  }

  @After
  public void tearDown() {
  }
  
  @Test
  public void getIdentityServiceInstance() {
    IdentityService<Identity> identityService = ClientServiceFactory.newInstance(IdentityServiceImpl.class);
    assertThat("identityService must not be null", identityService, notNullValue());
  }
  
  @Test
  public void getActivityServiceInstance() {
    ActivityService<Activity> activityService = ClientServiceFactory.newInstance(ActivityServiceImpl.class);
    assertThat("activityService must not be null", activityService, notNullValue());
  }
  
  @Test
  public void getVersionServiceInstance() {
    VersionService versionService = ClientServiceFactory.newOtherInstance(VersionServiceImpl.class);
    assertThat("versionService must not be null", versionService, notNullValue());
  }

}

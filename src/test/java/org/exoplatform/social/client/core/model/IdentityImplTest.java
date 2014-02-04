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
package org.exoplatform.social.client.core.model;

import org.exoplatform.social.client.api.model.RestIdentity;
import org.exoplatform.social.client.api.model.RestProfile;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit Test for {@link RestIdentity}
 *
 * @author <a href="http://hoatle.net">hoatle (hoatlevan at gmail dot com)</a>
 * @since May 25, 2011
 */
public class IdentityImplTest {

  @Test
  public void shouldCreteInstanceAndGetFields() {
    RestProfile restProfile = new RestProfile();
    RestIdentity restIdentity = new RestIdentity("123", "organization", "demo", restProfile);
    assertThat("restIdentity.getId() must return 123", restIdentity.getId(), equalTo("123"));
    assertThat("restIdentity.getProviderId() must return organization",
               restIdentity.getProviderId(), equalTo("organization"));
    assertThat("restIdentity.getRemoteId() must return demo", restIdentity.getRemoteId(), equalTo("demo"));
    //gets default value
    assertThat("restIdentity.getProfile() must be null",
                restIdentity.getProfile(), equalTo((RestProfile)new RestProfile()));
  }
}

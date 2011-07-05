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

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.exoplatform.social.client.api.SocialClientContext;
import org.exoplatform.social.client.api.auth.AccessDeniedException;
import org.exoplatform.social.client.api.model.Identity;
import org.exoplatform.social.client.api.net.SocialHttpClient.POLICY;
import org.exoplatform.social.client.api.service.IdentityService;
import org.exoplatform.social.client.api.service.ServiceException;
import org.exoplatform.social.client.core.model.IdentityImpl;
import org.exoplatform.social.client.core.util.SocialHttpClientSupport;
import org.exoplatform.social.client.core.util.SocialJSONDecodingSupport;
import org.json.simple.parser.ParseException;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jun 30, 2011  
 */
public class IdentityServiceImpl extends ServiceBase<Identity, IdentityService<Identity>> implements IdentityService<Identity> {
  private static final String BASE_URL = SocialHttpClientSupport.buildCommonRestPathFromContext(true);
  
  @Override
  public Identity create(Identity newInstance) throws AccessDeniedException, ServiceException {
    return null;
  }

  @Override
  public Identity get(String uuid) throws AccessDeniedException, ServiceException {
    //"/" + SocialClientContext.getRestContextName() + "/private/api/social/v1-alpha1/socialdemo/identity/" + uid + ".json";
    final String targetURL = BASE_URL + "identity/" + uuid + ".json";
    HttpResponse response = SocialHttpClientSupport.executeGet(targetURL, POLICY.BASIC_AUTH);
    try {
      return SocialJSONDecodingSupport.parser(IdentityImpl.class, response);
    } catch (IOException ioex) {
      throw new ServiceException(ActivityServiceImpl.class, "IOException when reads Json Content.", ioex);
      
    } catch (ParseException pex) {
      throw new ServiceException(ActivityServiceImpl.class, "ParseException when reads Json Content.", pex);
    }
  }

  @Override
  public Identity update(Identity existingInstance) throws AccessDeniedException, ServiceException {
    return null;
  }

  @Override
  public Identity delete(Identity existingInstance) throws AccessDeniedException, ServiceException {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getIdentityId(String provider, String remoteId) throws ServiceException {
    final String targetURL =  "/" + SocialClientContext.getRestContextName() + "/" + SocialClientContext.getPortalContainerName() + "/social/identity/" + remoteId + "/id/show.json";
    HttpResponse response = SocialHttpClientSupport.executeGet(targetURL, POLICY.NO_AUTH);
    try {
      String content = SocialHttpClientSupport.getContent(response);
      Map map = SocialJSONDecodingSupport.parser(content);
      return (String) map.get("id");
    } catch (Exception ex) {
      throw new ServiceException(ActivityServiceImpl.class, "Exception when reads Json Content.", ex);
    }
  }
  
  /**
   * Gets the target url of get identity id.
   * 
   * @param remoteId
   * @return
   */
  private String getTargetURLIdentityId(String remoteId) {
    return "/" + SocialClientContext.getRestContextName() + "/"
            + SocialClientContext.getPortalContainerName() + "/"
            + "social/identity/" + remoteId + "/id/show.json";
  }
}

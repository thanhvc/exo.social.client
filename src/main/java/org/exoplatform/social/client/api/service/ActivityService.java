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
package org.exoplatform.social.client.api.service;

import org.exoplatform.social.client.api.auth.AccessDeniedException;
import org.exoplatform.social.client.api.common.RealtimeListAccess;
import org.exoplatform.social.client.api.model.Comment;
import org.exoplatform.social.client.api.model.Identity;
import org.exoplatform.social.client.api.model.Like;

/**
 * Activity Service.
 *
 * @author <a href="http://hoatle.net">hoatle (hoatlevan at gmail dot com)</a>
 * @since May 19, 2011
 */
public interface ActivityService<Activity> extends Service<Activity> {

  /**
   * Gets activity stream from an owner stream identity.
   *
   * For example:
   * <pre>
   *    Identity demoIdentity = identityService.get("123456789abcdef");
   *    RealtimeListAccess<Activity> activityListAccess = activityService.getActivityStream(demoIdentity);
   * </pre>
   *
   * or:
   * <pre>
   *   Identity spaceIdentity = new IdentityImpl();
   *   spaceIdentity.setProviderId("space");
   *   spaceIdentity.setRemoteId("hello_world");
   *   RealtimeListAccess<Activity> activityListAccess = activityService.getActivitySream(spaceIdentity);
   * </pre>
   *
   * @param  ownerStreamIdentity the owner stream identity, could be a user or space identity.
   * @return the real time list access
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  RealtimeListAccess<Activity> getActivityStream(Identity ownerStreamIdentity) throws AccessDeniedException,
                                                                                      ServiceException;

  /**
   * Gets all activities from spaces which a user is a member of that space aggregated into one stream.
   *
   * @param userIdentity the associated user identity
   * @return the real time list access
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  RealtimeListAccess<Activity> getSpacesActivityStream(Identity userIdentity) throws AccessDeniedException,
                                                                                     ServiceException;

  /**
   * Gets all connections' activities aggregated into one stream.
   *
   * @param userIdentity the associated user identity
   * @return the real time list access
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  RealtimeListAccess<Activity> getConnectionsActivityStream(Identity userIdentity) throws AccessDeniedException,
                                                                                          ServiceException;

  /**
   * Gets all activities from a stream owner, his connections and spaces aggregated into one stream.
   *
   * @param userIdentity the associated user identity
   * @return the realtime list access
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  RealtimeListAccess<Activity> getFeedActivityStream(Identity userIdentity) throws AccessDeniedException,
                                                                                   ServiceException;
  /**
   * Creates a new comment to this activity.
   *
   * @param existingActivity the existing activity
   * @param newComment       the new created comment
   * @return the created comment
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  Comment createComment(Activity existingActivity, Comment newComment) throws AccessDeniedException, ServiceException;

  /**
   * Gets an existing comment by its id.
   *
   * @param commentId the comment id
   * @return the existing comment with the specified id
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  Comment getComment(String commentId) throws AccessDeniedException, ServiceException;

  /**
   * Deletes an existing comment.
   *
   * @param existingComment the existing comment
   * @return the deleted comment
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  Comment deleteComment(Comment existingComment) throws AccessDeniedException, ServiceException;


  /**
   * The authenticate identity likes an existing activity.
   *
   * @param existingActivity the existing activity
   * @return the created {@link Like} instance
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  Like like(Activity existingActivity) throws AccessDeniedException, ServiceException;

  /**
   * The authenticated identity unlikes an existing activity.
   *
   * @param existingActivity the existing activity
   * @return the deleted {@link Like} instance.
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  Like unlike(Activity existingActivity) throws AccessDeniedException, ServiceException;

}

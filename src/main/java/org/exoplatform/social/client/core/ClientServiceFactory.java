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

import org.exoplatform.social.client.api.model.Model;
import org.exoplatform.social.client.api.service.Service;

/**
 * The main entry point to get eXo Social Services.
 *
 * @author <a href="http://hoatle.net">hoatle (hoatlevan at gmail dot com)</a>
 * @since June 28, 2011
 */
public class ClientServiceFactory {
  private static ClassLoader loader = null;
  static {
    loader = Thread.currentThread().getContextClassLoader();
  }
  
  /**
   * 
   * Gets eXo Social Service uses the ClassLoader.
   * example:
   *      ActivityService service = ClientServiceFactory.newInstance(ActivityServiceImpl.class);
   * 
   * @param <M>
   * @param <T> define <code>Service</code> generic
   * @param clazz <code>Service</code> type
   * @return
   */
  @SuppressWarnings("unchecked")
  public static <M extends Model, T extends Service<M>> T newInstance(Class<T> clazz) {
    try {
      Class<?> classType = loader.loadClass(clazz.getName());
      return (T) classType.newInstance();
    } catch (ClassNotFoundException e) {

    } catch (InstantiationException e) {

    } catch (IllegalAccessException e) {

    }
    return null;
  }
  /**
   * Gets eXo Social Service uses the ClassLoader. Only use to create the Service 
   * which can not define with the model generic such as VersionImpl
   *  
   * @param <T> define <code>Service</code> generic
   * @param <code>Service</code> type
   * @return
   */
  @SuppressWarnings("unchecked")
  public static <T> T newOtherInstance(Class<T> clazz) {
    try {
      Class<?> classType = loader.loadClass(clazz.getName());
      return (T) classType.newInstance();
    } catch (ClassNotFoundException e) {

    } catch (InstantiationException e) {

    } catch (IllegalAccessException e) {

    }
    return null;
  }
  
  
 

}

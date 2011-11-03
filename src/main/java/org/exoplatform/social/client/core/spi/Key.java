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
package org.exoplatform.social.client.core.spi;

import static org.exoplatform.social.client.core.spi.utils.Preconditions.checkNotNull;

/**
 * Created by The eXo Platform SAS
 * Author : thanh_vucong
 *          thanhvucong.78@gmail.com
 * Nov 3, 2011  
 */
public class Key<T> {

  final Class<T> type;
  final String name;
  final int hashCode;
  
  
  private Key(Class<T> type, String name) {
    checkNotNull(type, "Type is null.");
    checkNotNull(name, "Name is null.");
    
    this.type = type;
    this.name = name;

    hashCode = type.hashCode() * 31 + name.hashCode();
  }
  
  Class<T> getType() {
    return type;
  }

  String getName() {
    return name;
  }

  public int hashCode() {
    return hashCode;
  }

  public boolean equals(Object o) {
    if (!(o instanceof Key)) {
      return false;
    }
    if (o == this) {
      return true;
    }
    Key other = (Key) o;
    return name.equals(other.name) && type.equals(other.type);
  }

  public String toString() {
    return "[type=" + type.getName() + ", name='" + name + "']";
  }

  public static <T> Key<T> newInstance(Class<T> type, String name) {
    return new Key<T>(type, name);
  }
}

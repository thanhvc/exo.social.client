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


/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Oct 17, 2011  
 */
public class Key {

  public static KeyContext STRING_KEY_CONTEXT = new KeyContext(String.class) {};
  
  public static KeyContext LONG_KEY_CONTEXT = new KeyContext(Long.class) {};
  
  final KeyContext keyContext;
  final String name;
  
  public Key(KeyContext keyContext, String name) {
    this.keyContext = keyContext;
    this.name = name;
  }
  
  public KeyContext getKeyContext() {
    return keyContext;
  }

  public String getName() {
    return name;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    
    if ((obj.equals(this)) == false) {
      return false;
    }
    
    Key other = this.getClass().cast(obj);
    return this.name.equals(other.name) && this.keyContext.equals(other.keyContext);
  }

  
  static class KeyContext implements Cloneable {
    Class<?> type;

    Object value;

    public KeyContext(Class<?> type) {
      this.type = type;
    }

    public boolean isAssignFrom(Class<?> type) {
      return this.type.equals(type);
    }
    
    public void setValue(Object value) {
      this.value = value;
    }

    /**
     * Gets the value which is wrapped by the KeyContext.
     * 
     * @param <V>
     * @param targetType
     * @return
     */
    public <V> Object getValue(Class<V> targetType) {
      return type.equals(targetType) ? value : null;
    }
    
    
    @Override
    public boolean equals(Object obj) {
      if (obj == null) return false;
      
      if ((obj instanceof KeyContext) == false) {
        return false;
      }
      
      KeyContext other = (KeyContext) obj;
      return (this.value == other.value);
    }
    
    /**
     * Cloneable the KeyContext
     */
    public KeyContext clone() {
      KeyContext keyContext;
      try {
        keyContext = (KeyContext) super.clone();
        keyContext.value = this.value;
      } catch (CloneNotSupportedException e) {
        return null;
      }
      return keyContext;
    }
  }
}

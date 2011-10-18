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

import junit.framework.TestCase;

import org.exoplatform.social.client.core.service.Key.KeyContext;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Oct 18, 2011  
 */
public class KeyTest extends TestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }
  
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }
  
  
 
  public void testCloneKeyContextString() throws Exception {
    
    
    KeyContext keyContextString1 = Key.STRING_KEY_CONTEXT.clone();
    KeyContext keyContextString2 = Key.STRING_KEY_CONTEXT.clone();
    
    {
      assertNotNull(keyContextString1);
      assertNotNull(keyContextString2);
      assertTrue(keyContextString1.equals(keyContextString2));

      keyContextString1.setValue("notEqual");
      assertFalse(keyContextString1.equals(keyContextString2));
    }
    
    {
      KeyContext keyContextLong1 = Key.LONG_KEY_CONTEXT.clone();
      assertTrue("wrong compare ContextLong and ContextString", keyContextString1.equals(keyContextLong1));
    }
    
  }
  
}

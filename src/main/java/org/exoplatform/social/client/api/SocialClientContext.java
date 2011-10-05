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
package org.exoplatform.social.client.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

/**
 * The client context to hold states of: host, port, portalContainerName,
 * restContextName and rest version and auth schema.
 *
 * @author <a href="http://hoatle.net">hoatle (hoatlevan at gmail dot com)</a>
 * @since May 19, 2011
 */
public class SocialClientContext {

  public static final Option<String> V1_ALPHA1 = new Option<String>("v1-alpha1") {};  
  public static final Option<String> V1_ALPHA2 = new Option<String>("v1-alpha2") {};
  
  public static final Option<String> PRIVATE = new Option<String>("private") {};  
  public static final Option<String> PUBLIC = new Option<String>("public") {};
  
  /**
   * Gets host of the portal container to access services.
   *
   * @return the host
   */
  public static String getHost() {
    return host;
  }

  /**
   * Sets host of the portal container to access services.
   *
   * @param newHost the host
   */
  public static void setHost(String newHost) {
    host = newHost;
  }

  /**
   * Gets port of the portal container to access services.
   *
   * @return the port
   */
  public static int getPort() {
    return port;
  }

  /**
   * Sets port of the portal container to access services.
   *
   * @param newPort the port
   */
  public static void setPort(int newPort) {
    port = newPort;
  }

  /**
   * Gets portal container name of the portal container.
   * For example: socialdemo, portal, intranet...
   *
   * @return the portal container name
   */
  public static String getPortalContainerName() {
    return portalContainerName;
  }

  /**
   * Sets the portal container name of the portal container.
   *
   * @param newPortalContainerName the portal container name
   */
  public static void setPortalContainerName(String newPortalContainerName) {
    portalContainerName = newPortalContainerName;
  }

  /**
   * Gets the rest context name of the portal container.
   *
   * @return the rest context name
   */
  public static String getRestContextName() {
    return restContextName;
  }

  /**
   * Sets the rest context name of the portal container.
   *
   * @param newRestContextName the rest context name
   */
  public static void setRestContextName(String newRestContextName) {
    restContextName = newRestContextName;
  }


  /**
   * Gets the eXo Social Rest version.
   *
   * @return the eXo Social Rest version
   */
  public static String getRestVersion() {
    return restVersion;
  }

  /**
   * Sets the eXo Social Rest version.
   *
   * @param newRestVersion the eXo Social Rest version
   */
  public static void setRestVersion(String newRestVersion) {
    restVersion = newRestVersion;
  }

  /**
   * Gets the user name for basic authentication.
   *
   * @return the user name
   */
  public static String getUsername() {
    return username;
  }

  /**
   * Sets the user name for basic authentication.
   *
   * @param newUsername the user name
   */
  public static void setUsername(String newUsername) {
    username = newUsername;
  }

  /**
   * Gets the password for basic authentication
   *
   * @return the password
   */
  public static String getPassword() {
    return password;
  }

  /**
   * Sets the password for basic authentication.
   *
   * @param newPassword the password
   */
  public static void setPassword(String newPassword) {
    password = newPassword;
  }

  

  /**
   * Gets protocol of the portal container to access services.
   * @return
   */
  public static String getProtocol() {
    return protocol;
  }

  /**
   * Sets protocol of the portal container to access services.
   * @param protocol
   */
  public static void setProtocol(String protocol) {
    SocialClientContext.protocol = protocol;
  }

  /**
   * Gets current request and response context.
   *
   * @return current context
   */
  public Context getCurrentContext() {
    return currentContext;
  }
  

  /**
   * Checks to know if this lib is running on development mode to log more info.
   *
   * @return a boolean value
   */
  public static boolean isDeveloping() {
    return isDeveloping;
  }

  /**
   * Sets this lib to run on developing mode or not.
   *
   * @param isDevelopingMode a boolean value
   */
  public static void setIsDeveloping(boolean isDevelopingMode) {
    isDeveloping = isDevelopingMode;
  }

  /**
   * Sets current request and response context.
   *
   * @param currentContext the current context
   */
  public void setCurrentContext(Context currentContext) {
    this.currentContext = currentContext;
  }
  
  /**
   * Defines pattern URI for public URL which does not need 'private' part.
   *
   * @return
   */
  public static String getPublicBaseURL() {
    String publicURL = new StringBuffer().append("/")
                                         .append(getRestContextName())
                                         .append("/api/social/")
                                         .append("{restVersion}/")
                                         .append(SocialClientContext.getPortalContainerName())
                                         .append("/")
                                       .toString();
    return publicURL;
  }
  
  /**
   * Defines pattern URI for public URL which must have 'private' part.
   * @return
   */
  public static String getPrivateBaseURL() {
    String privateURL = new StringBuffer().append("/")
                                          .append(getRestContextName())
                                          .append("/{private}/api/social/")
                                          .append("{restVersion}/")
                                          .append(SocialClientContext.getPortalContainerName())
                                          .append("/")
                                          .toString();
    return privateURL;
  }
  /**
   * Create new instanse of PathBuilder
   * @param basePath
   * @return
   */
  public static PathBuilder fromPath(String basePath) {
    return new PathBuilder(basePath);
  }

  private static String host;
  private static int port;
  private static String protocol = "http";
  private static String portalContainerName;
  private static String restContextName;
  private static String restVersion = "v1-alpha1";
  private static String username;
  private static String password;
  private static boolean isDeveloping = false;
  
  private Context currentContext;


  /**
   * Keeping the HttpRequest and HttpResponse for Social RestService
   * @author thanh_vucong
   *
   */
  public class Context {
    private HttpRequest  request;

    private HttpResponse response;

    public Context(HttpRequest request, HttpResponse response) {
      this.request = request;
      this.response = response;
    }

    public HttpRequest getRequest() {
      return request;
    }

    public HttpResponse getResponse() {
      return response;
    }
  }

  /**
   * Defines the Option values for build URI.
   * Option uses when the user want to switch versions(v1-alpha1, v1-alpha2...etc).
   * @author thanh_vucong
   *
   * @param <T>
   */
  public abstract static class Option<T> {
    private final T value;
    public Option(final T value) {
      this.value = value;
    }
    public T getValue() {
      return value;
    }
  }
  
  /**
   * PathBuilder supports to build the BaseURL for RestService.
   * @author thanh_vucong
   *
   */
  public static class PathBuilder {
    private String basePath;

    private PathBuilder(String path) {
      this.basePath = path;
    }

    /**
     * Builds the BaseURL.
     * @param values
     * @return
     */
    public String build(String...values) {
      return URIPattern.createUriWithValues(this.basePath, values);
    }
    
  }
  
  
  public static class URIPattern {
    /**
     * Pattern for process URI parameters, for example /a/b/{x}/c .
     */
    public static final Pattern URI_PARAMETERS_PATTERN = Pattern.compile("\\{[^\\}^\\{]+\\}");
    
   
    /**
     * Utility method to build the Base URL with fill values.
     * 
     * Example: baseURL = '/rest-socialdemo/{private}/..../{restVersion}/'
     *          
     * this method processes to fill value for above and return such as:
     * '/rest-socialdemo/private|public/..../v1-alpha1|v1-alpha2/'
     *  - private|public and v1-alpha1|v1-alpha2 depend on the specified values argument.
     *  
     * @param basePath
     * @param values
     * @return
     */
    public static String createUriWithValues(String basePath, Object[] values) {
      StringBuffer sb = new StringBuffer();
      if (basePath != null) {
        appendUriPart(sb, basePath, values);
      }

      return sb.toString();
    }
    /**
     * @param sb the StringBuffer for appending URI part
     * @param basePath URI part
     * @param sourceValues the source array of values
     */
    private static void appendUriPart(StringBuffer sb,
                                     String basePath,
                                     Object[] sourceValues) {

      if (!hasUriTemplates(basePath)) {
        sb.append(basePath);
      }

      Matcher m = URI_PARAMETERS_PATTERN.matcher(basePath);

      int start = 0;
      int offset = 0;
      while (m.find()) {
        sb.append(basePath, start, m.start()); // 'static' part
        String param = basePath.substring(m.start() + 1, m.end() - 1);

        String value = "";
        // Value is unknown, we met it first time, then process it and keep in
        // map. Value will be encoded (or validate)before putting in map.
        if (offset < sourceValues.length)
          value = sourceValues[offset++].toString();

        if (value != null) {
          sb.append(value);
        } else {
          throw new IllegalArgumentException("Not found corresponding value for parameter " + param);
        }

        start = m.end();
      }

      // copy the last part or uriPart
      sb.append(basePath, start, basePath.length());
    }

    /**
     * Check does given URI string has templates.
     *
     * @param uri the URI which must be checked
     * @return true if URI has templates false otherwise
     */
    private static boolean hasUriTemplates(String uri)
    {
       return uri.indexOf('{') != -1;
    }
  }
}

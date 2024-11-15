/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.resource;

import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContextAdmin;
import org.apache.uima.UimaContextHolder;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.util.InvalidXMLException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Resource loader for ruta.
 *
 * This resource loader first looks in the resource paths and fallback in the classpath if it is not
 * found.
 */
public class RutaResourceLoader implements ResourceLoader {

  private final ResourceManager resMgr;

  private final ResourceLoader wrapped;

  private final ResourceLoader fallback;

  private final ResourceLoader fallback2;

  private final ClassLoader classLoader;

  /**
   * @param paths
   *          paths to search in priority.
   * @param classLoader
   *          optional classloader for fallback resource loader
   */
  public RutaResourceLoader(ResourceManager resMgr, String[] paths, ClassLoader classLoader) {
    this.resMgr = resMgr;
    this.wrapped = new PathMatchingResourcePatternResolver(new ResourcePathResourceLoader(
            paths));
    if(classLoader == null) {
      this.classLoader = this.getClass().getClassLoader();
    } else {
      this.classLoader = classLoader;
    }
    this.fallback = new DefaultResourceLoader(this.classLoader);
    this.fallback2 = new DefaultResourceLoader();
  }

  public RutaResourceLoader(String[] paths, ClassLoader classLoader) {
    this(null, paths, classLoader);
  }

  /**
   * @param paths
   *          paths to search in priority.
   */
  public RutaResourceLoader(String[] paths) {
    this(null, paths, null);
  }


  @Override
  public Resource getResource(String location) {
    Resource resource = this.wrapped.getResource(location);

    if (!resource.exists()) {
      resource = fallback.getResource(location);
    }

    if (!resource.exists()) {
      resource = fallback2.getResource(location);
    }

    return resource;
  }

  public Resource getResourceWithDotNotation(String location, String extension) {
    String path = location.replaceAll("[.]", "/") + extension;
    var resource = getResource(path);

    var rm = resMgr;
    if (rm == null && UimaContextHolder.getContext() instanceof UimaContextAdmin ctxAdm) {
      rm = ctxAdm.getResourceManager();
    }

    if (rm != null && !resource.exists()) {
      try {
        var descImport = UIMAFramework.getResourceSpecifierFactory().createImport();
        descImport.setName(location);
        var url = descImport.findAbsoluteUrl(rm);
        if (url != null) {
          return new UrlResource(url);
        }
      } catch (InvalidXMLException e) {
        // Unable to resolve the resource through the resource manager - let's fall back to the
        // non-existing resource from earlier
      }
    }

    return resource;
  }

  @Override
  public ClassLoader getClassLoader() {
    return classLoader;
  }

}

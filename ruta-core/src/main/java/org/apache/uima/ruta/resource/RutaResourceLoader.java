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

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Resource loader for ruta.
 *
 * This resource loader first looks in the resource paths and fallback in the classpath if it is not
 * found.
 */
public class RutaResourceLoader implements ResourceLoader {
  
  private final ResourceLoader wrapped;

  private final ResourceLoader fallback;

  private final ClassLoader classLoader;
  
  /**
   * @param paths
   *          paths to search in priority.
   */
  public RutaResourceLoader(String[] resourcePaths, ClassLoader classLoader) {
    this.wrapped = new PathMatchingResourcePatternResolver(new ResourcePathResourceLoader(
            resourcePaths));
    if(classLoader == null) {
      this.classLoader = this.getClass().getClassLoader();
    } else {
      this.classLoader = classLoader;
    }
    this.fallback = new DefaultResourceLoader(this.classLoader);
  }
  
  /**
   * @param paths
   *          paths to search in priority.
   */
  public RutaResourceLoader(String[] paths) {
    this(paths, null);
  }

  
  @Override
  public Resource getResource(String location) {
    Resource resource = this.wrapped.getResource(location);
    if (!resource.exists()) {
      resource = fallback.getResource(location);
    }
    return resource;
  }

  public Resource getResourceWithDotNotation(String location, String extension) {
    String path = location.replaceAll("[.]", "/") + extension;
    return getResource(path);
  }
  
  @Override
  public ClassLoader getClassLoader() {
    return classLoader;
  }

}

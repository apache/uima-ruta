package org.apache.uima.ruta.resource;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Resource loader for ruta.
 *
 * This resource loader first looks in the resource paths and fallback in the classpath if it is not found.
 */
public class RutaResourceLoader implements ResourceLoader {
  private final ResourceLoader wrapped;
  private final ResourceLoader fallback;

  /**
   * @param resourcePaths Resource paths to search in priority.
   */
  public RutaResourceLoader(String[] resourcePaths) {
    this.wrapped = new PathMatchingResourcePatternResolver(new ResourcePathResourceLoader(resourcePaths));
    this.fallback = new DefaultResourceLoader();
  }

  public Resource getResource(String location) {
    Resource resource = this.wrapped.getResource(location);
    if (!resource.exists()) {
      resource = fallback.getResource(location);
    }
    return resource;
  }

  public ClassLoader getClassLoader() {
     return getClass().getClassLoader();
  }
}

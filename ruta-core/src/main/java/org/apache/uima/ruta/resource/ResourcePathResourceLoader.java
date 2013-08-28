package org.apache.uima.ruta.resource;

import java.io.File;

import org.springframework.core.io.DescriptiveResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Load a resource from resource paths.
 */
public class ResourcePathResourceLoader implements ResourceLoader {
  private final String[] resourcePaths;

  /**
   * @param resourcePaths Resource paths to search in.
   */
  public ResourcePathResourceLoader(String[] resourcePaths) {
    this.resourcePaths = resourcePaths;
  }

  public Resource getResource(String location) {
    for (String parent : resourcePaths) {
      final File f = new File(parent, location);
      if (f.exists()) {
        return new FileSystemResource(f);
      }
    }

    return new DescriptiveResource(location + " was not found in resource paths");
  }

  public ClassLoader getClassLoader() {
    return getClass().getClassLoader();
  }
}

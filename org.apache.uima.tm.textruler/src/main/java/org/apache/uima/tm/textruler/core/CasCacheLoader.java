package org.apache.uima.tm.textruler.core;

import org.apache.uima.cas.CAS;

/**
 * Helper-Inferface for CasCache. The user of CasCache has to provide a method for loading a CAS
 * e.g. from a XMI-file.
 */
public interface CasCacheLoader {

  public CAS loadCAS(String fileName, CAS reuseCAS);

}

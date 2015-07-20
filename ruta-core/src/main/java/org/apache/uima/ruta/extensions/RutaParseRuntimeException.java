package org.apache.uima.ruta.extensions;

import org.apache.uima.ruta.parser.RutaParser;

/** Thrown by {@link RutaParser} in case of parsing error */
public class RutaParseRuntimeException extends RuntimeException {

  public RutaParseRuntimeException(String msg) {
    super(msg);
  }

  public RutaParseRuntimeException(Throwable t) {
    super(t);
  }

  private static final long serialVersionUID = 3650793211725645827L;

}

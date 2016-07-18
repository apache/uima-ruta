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

package org.apache.uima.ruta.ide.launching;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.util.CasIOUtils;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.SerializationFormat;
import org.apache.uima.util.impl.ProcessTrace_impl;
import org.xml.sax.SAXException;

public class RutaLauncher {

  public static final List<String> COMMON_PLAIN_TEXT_FILE_EXTENSIONS = Arrays.asList(new String[] {".txt", ".csv", "html", "xhtml"});
  
  public static final String URL_ENCODING = "UTF-8";

  private static File descriptor;

  private static File inputFolder;

  private static File outputFolder = null;

  private static boolean inputRecursive = false;

  private static boolean addSDI = false;

  private static String inputEncoding = "UTF-8";

  private static String launchMode = "run";

  private static String view = null;
  
  private static String defaultFormat = null;

  private static boolean parseCmdLineArgs(String[] args) throws UnsupportedEncodingException {
    int index = 0;
    int count = 0;
    while (index < args.length) {
      String each = args[index++];
      if (RutaLaunchConstants.ARG_INPUT_FOLDER.equals(each)) {
        if (index >= args.length) {
          return false;
        }
        count++;
        inputFolder = new File(URLDecoder.decode(args[index++], URL_ENCODING));
      } else if (RutaLaunchConstants.ARG_OUTPUT_FOLDER.equals(each)) {
        if (index >= args.length) {
          return false;
        }
        outputFolder = new File(URLDecoder.decode(args[index++], URL_ENCODING));
      } else if (RutaLaunchConstants.ARG_DESCRIPTOR.equals(each)) {
        if (index >= args.length) {
          return false;
        }
        count++;
        descriptor = new File(URLDecoder.decode(args[index++], URL_ENCODING));
      } else if (RutaLaunchConstants.ARG_RECURSIVE.equals(each)) {
        if (index >= args.length) {
          return false;
        }
        inputRecursive = Boolean.parseBoolean(args[index++]);
      } else if (RutaLaunchConstants.ARG_ADD_SDI.equals(each)) {
        if (index >= args.length) {
          return false;
        }
        addSDI = Boolean.parseBoolean(args[index++]);
      } else if (RutaLaunchConstants.ARG_RECURSIVE.equals(each)) {
        if (index >= args.length) {
          return false;
        }
        inputEncoding = args[index++];
      } else if (RutaLaunchConstants.ARG_MODE.equals(each)) {
        if (index >= args.length) {
          return false;
        }
        launchMode = args[index++];
      } else if (RutaLaunchConstants.ARG_VIEW.equals(each)) {
        if (index >= args.length) {
          return false;
        }
        view = args[index++];
      } else if (RutaLaunchConstants.ARG_FORMAT.equals(each)) {
        if (index >= args.length) {
          return false;
        }
        defaultFormat = args[index++];
      }
    }
    return count == 2;
  }

  public static void main(String[] args) throws Exception {
    if (!parseCmdLineArgs(args)) {
      throw new IllegalArgumentException("Passed arguments are invalid!");
    }

    AnalysisEngine ae = Ruta.wrapAnalysisEngine(descriptor.toURI().toURL(), view, true,
            inputEncoding);
    configure(ae);
    CAS cas = ae.newCAS();

    List<File> inputFiles = getFiles(inputFolder, inputRecursive);
    for (File file : inputFiles) {
      processFile(file, ae, cas);
    }

    ae.batchProcessComplete(new ProcessTrace_impl());
    ae.collectionProcessComplete(new ProcessTrace_impl());
    cas.release();
    ae.destroy();
  }

  private static void processFile(File file, AnalysisEngine ae, CAS cas) throws IOException,
          AnalysisEngineProcessException, SAXException {
    if (view != null) {
      boolean found = false;
      Iterator<CAS> viewIterator = cas.getViewIterator();
      while (viewIterator.hasNext()) {
        CAS each = (CAS) viewIterator.next();
        String viewName = each.getViewName();
        if (viewName.equals(view)) {
          cas = cas.getView(view);
          found = true;
          break;
        }
      }
      if (!found) {
        cas = cas.createView(view);
      }

    }
    
    SerializationFormat format = SerializationFormat.valueOf(defaultFormat);
    String extension = FilenameUtils.getExtension(file.getName());
    if(COMMON_PLAIN_TEXT_FILE_EXTENSIONS.contains(extension)) {
      String document = FileUtils.file2String(file, inputEncoding);
      cas.setDocumentText(document);
    } else {
      try {
        format = CasIOUtils.load(file, null, cas, true);
      } catch (Exception e) {
        // no format? maybe really a plain text format?
        String document = FileUtils.file2String(file, inputEncoding);
        cas.setDocumentText(document);
      }
    }
    
    if (addSDI) {
      RutaEngine.removeSourceDocumentInformation(cas);
      RutaEngine.addSourceDocumentInformation(cas, file);
    }
    ae.process(cas);
    if (outputFolder != null) {
      File outputFile = getOutputFile(file, inputFolder, outputFolder, format);
      FileOutputStream os = new FileOutputStream(outputFile);
      CasIOUtils.save(cas, os, format);
      IOUtils.closeQuietly(os);
    }
    cas.reset();
  }

  private static void configure(AnalysisEngine ae) throws ResourceConfigurationException {
    if ("debug".equals(launchMode)) {
      ae.setConfigParameterValue(RutaEngine.PARAM_DEBUG, true);
      ae.setConfigParameterValue(RutaEngine.PARAM_DEBUG_WITH_MATCHES, true);
      ae.setConfigParameterValue(RutaEngine.PARAM_PROFILE, true);
      ae.setConfigParameterValue(RutaEngine.PARAM_STATISTICS, true);
      ae.setConfigParameterValue(RutaEngine.PARAM_CREATED_BY, true);
    }
    ae.reconfigure();

  }

  private static List<File> getFiles(File dir, boolean recusive) {
    List<File> result = new ArrayList<File>();
    File[] listFiles = dir.listFiles();
    if (listFiles != null) {
      for (File each : listFiles) {
        // TODO: find a solution for this hotfix
        if (each.isHidden()) {
          continue;
        }
        if (each.isFile()) {
          result.add(each);
        } else if (each.isDirectory() && recusive) {
          result.addAll(getFiles(each, recusive));
        }
      }
    }
    return result;
  }


  private static File getOutputFile(File inputFile, File inputFolder, File outputFolder, SerializationFormat format) {
    URI relativize = inputFolder.toURI().relativize(inputFile.toURI());
    String path = relativize.getPath();
    String ext = "." + format.getDefaultFileExtension();
    if (!path.endsWith(ext)) {
      path += "ext";
    }
    File result = new File(outputFolder, path);
    result.getParentFile().mkdirs();
    return result;
  }

}

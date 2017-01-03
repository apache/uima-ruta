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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.SerialFormat;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.impl.ResourceManager_impl;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.util.CasIOUtils;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.impl.ProcessTrace_impl;
import org.xml.sax.SAXException;

public class RutaLauncher {

  public static final List<String> COMMON_PLAIN_TEXT_FILE_EXTENSIONS = Arrays
          .asList(new String[] { ".txt", ".csv", "html", "xhtml" });

  public static final String URL_ENCODING = "UTF-8";

  private static File descriptor;

  private static File inputFolder;

  private static File outputFolder = null;

  private static boolean inputRecursive = false;

  private static boolean addSDI = false;

  private static String inputEncoding = "UTF-8";

  private static String launchMode = "run";

  private static String view = null;

  private static String serialFormat = null;

  private static String classPath = null;

  private static void parseCmdLineArgs(String[] args)  {
    int index = 0;
    
    while (index < args.length) {
      String each = args[index++];
      if (RutaLaunchConstants.INPUT_FOLDER.equals(each)) {
        if (index >= args.length) {
          throwException("Not enough arguments! Value of input folder is missing", args, null);
        }
        String in = args[index++];
        try {
          inputFolder = new File(URLDecoder.decode(in, URL_ENCODING));
        } catch (UnsupportedEncodingException e) {
          throwException("Unable to decode input folder argument: " + in, args, e);
        }
      } else if (RutaLaunchConstants.OUTPUT_FOLDER.equals(each)) {
        if (index >= args.length) {
          throwException("Not enough arguments! Value of output folder is missing", args, null);
        }
        String out = args[index++];
        try {
          outputFolder = new File(URLDecoder.decode(out, URL_ENCODING));
        } catch (UnsupportedEncodingException e) {
          throwException("Unable to decode output folder argument: " + out, args, e);
        }
      } else if (RutaLaunchConstants.DESCRIPTOR.equals(each)) {
        if (index >= args.length) {
          throwException("Not enough arguments! Value of descriptor is missing", args, null);
        }
        String desc = args[index++];
        if(StringUtils.equals(desc, "null")) {
          throwException("Value for descriptor is missing", args, null);
        }
        try {
          descriptor = new File(URLDecoder.decode(desc, URL_ENCODING));
        } catch (UnsupportedEncodingException e) {
          throwException("Unable to decode descriptor argument: " + desc, args, e);
        }
      } else if (RutaLaunchConstants.RECURSIVE.equals(each)) {
        if (index >= args.length) {
          throwException("Not enough arguments! Value of recursive folder structure is missing", args, null);
        }
        inputRecursive = Boolean.parseBoolean(args[index++]);
      } else if (RutaLaunchConstants.ADD_SDI.equals(each)) {
        if (index >= args.length) {
          throwException("Not enough arguments! Value of adding source document information is missing", args, null);
        }
        addSDI = Boolean.parseBoolean(args[index++]);
      } else if (RutaLaunchConstants.ENCODING.equals(each)) {
        if (index >= args.length) {
          throwException("Not enough arguments! Value of encoding is missing", args, null);
        }
        inputEncoding = args[index++];
      } else if (RutaLaunchConstants.MODE.equals(each)) {
        if (index >= args.length) {
          throwException("Not enough arguments! Value of run mode is missing", args, null);
        }
        launchMode = args[index++];
      } else if (RutaLaunchConstants.VIEW.equals(each)) {
        if (index >= args.length) {
          throwException("Not enough arguments! Value of CAS view is missing", args, null);
        }
        view = args[index++];
      } else if (RutaLaunchConstants.FORMAT.equals(each)) {
        if (index >= args.length) {
          throwException("Not enough arguments! Value of serialization format is missing", args, null);
        }
        serialFormat = args[index++];
      } else if (RutaLaunchConstants.CLASSPATH.equals(each)) {
        if (index >= args.length) {
          throwException("Not enough arguments! Value of classpath is missing", args, null);
        }
        String cp = args[index++];
        try {
          classPath =  URLDecoder.decode(cp, URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
          throwException("Unable to decode classpath argument: " + cp, args, e);
        }
      }
    }
    if(descriptor == null) {
      throwException("Argument for descriptor is missing", args, null);
    }
    if(inputFolder == null) {
      throwException("Argument for input folder is missing", args, null);
    }
  }

  private static void throwException(String message, String[] args, Exception e) {
    if(e != null) {
      throw new IllegalArgumentException(message + " - Arguments: " + Arrays.toString(args), e);
    }
    throw new IllegalArgumentException(message + " - Arguments: " + Arrays.toString(args));
  }

  public static void main(String[] args) throws Exception {
    parseCmdLineArgs(args);

    ResourceManager resourceManager = null;
    if (classPath != null) {
      String[] split = classPath.split(File.pathSeparator);
      ClassLoader classLoader = getClassLoader(Arrays.asList(split));
      resourceManager = new ResourceManager_impl(classLoader);
    }
    AnalysisEngine ae = Ruta.wrapAnalysisEngine(descriptor.toURI().toURL(), view, true,
            inputEncoding, resourceManager);
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

  private static void processFile(File file, AnalysisEngine ae, CAS cas)
          throws IOException, AnalysisEngineProcessException, SAXException {
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

    SerialFormat format = SerialFormat.valueOf(serialFormat);
    if (format == SerialFormat.UNKNOWN) {
      format = SerialFormat.XMI;
    }
    String extension = FilenameUtils.getExtension(file.getName());
    if (COMMON_PLAIN_TEXT_FILE_EXTENSIONS.contains(extension)) {
      String document = FileUtils.file2String(file, inputEncoding);
      cas.setDocumentText(document);
    } else {
      try {
        CasIOUtils.load(file.toURI().toURL(), null, cas, true);
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

  private static File getOutputFile(File inputFile, File inputFolder, File outputFolder,
          SerialFormat format) {
    URI relativize = inputFolder.toURI().relativize(inputFile.toURI());
    String path = relativize.getPath();
    String ext = "." + format.getDefaultFileExtension();
    if (!path.endsWith(ext)) {
      path += ext;
    }
    File result = new File(outputFolder, path);
    result.getParentFile().mkdirs();
    return result;
  }
  
  private static ClassLoader getClassLoader(Collection<String> classPath) throws MalformedURLException {
   // TODO copied method to avoid extended classpath
    URL[] urls = new URL[classPath.size()];
    int counter = 0;
    for (String dep : classPath) {
      urls[counter] = new File(dep).toURI().toURL();
      counter++;
    }
    ClassLoader classLoader = new URLClassLoader(urls);
    return classLoader;
  }

}

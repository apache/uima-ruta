package org.apache.uima.tm.cev.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.tm.cev.CEVPlugin;
import org.apache.uima.tools.stylemap.StyleMapEntry;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.SAXException;


/**
 * Klasse fuer ein CAS-File/XMI-File Es enthaehlt mehrere CAS-Views
 * 
 * @author Marco Nehmeier
 */
public class CEVDocument {

  private ArrayList<CEVData> casData;

  private CAS mainCAS;

  private Object descriptor;

  private Map<String, StyleMapEntry> style;

  /**
   * Konstruktor Oeffnet das Cas-File...
   * 
   * @param descriptorFile
   *          Descriptor
   * @param casFile
   *          CAS-File
   * @throws IllegalArgumentException
   * @throws IOException
   * @throws InvalidXMLException
   * @throws SAXException
   * @throws ResourceInitializationException
   */
  public CEVDocument(IFile descriptorFile, IFile casFile) throws IllegalArgumentException,
          IOException, InvalidXMLException, SAXException, ResourceInitializationException {

    casData = new ArrayList<CEVData>();

    // Descriptor parsen
    descriptor = UIMAFramework.getXMLParser().parse(
            new XMLInputSource(descriptorFile.getLocation().toFile()));

    mainCAS = null;

    // CAS erzeugen
    if (descriptor instanceof AnalysisEngineDescription) {
      // not tested any more: use Type System!
      mainCAS = CasCreationUtils.createCas((AnalysisEngineDescription) descriptor);
    } else if (descriptor instanceof TypeSystemDescription) {
      TypeSystemDescription tsDesc = (TypeSystemDescription) descriptor;
      ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
      IProject project = descriptorFile.getProject();
      // TODO hotfix for import by name
      IFolder folder = project.getFolder("descriptor");
      resMgr.setDataPath(folder.getLocation().toPortableString());
      tsDesc.resolveImports(resMgr);
      mainCAS = CasCreationUtils.createCas(tsDesc, null, new FsIndexDescription[0]);
    } else {
      throw new IllegalArgumentException("Invalid Type System Descriptor");
    }

    // CAS-File einlesen und deserialisieren
    FileInputStream inputStream = null;
    try {
      inputStream = new FileInputStream(casFile.getLocation().toFile());
      XmiCasDeserializer.deserialize(inputStream, mainCAS, true);
    } finally {
      if (inputStream != null)
        inputStream.close();
    }

    // Stylemap einlesen (wenn vorhanden)
    try {
      String desc = descriptorFile.getFullPath().removeFileExtension().lastSegment();
      if (desc.toLowerCase().endsWith("typesystem")) {
        desc = desc.substring(0, desc.length() - 10);
      }
      String styleMap = desc.toLowerCase() + "stylemap.xml";

      for (IResource r : descriptorFile.getParent().members())
        if (r.getType() == IResource.FILE && r.getFullPath().lastSegment().startsWith(desc)
                && r.getFullPath().lastSegment().toLowerCase().equals(styleMap)) {

          style = StyleMapReader.parseStyleMapDOM(r.getLocation().toOSString());
          break;
        }

    } catch (CoreException e) {
      CEVPlugin.error(e);
    }

    Iterator viewIter = mainCAS.getViewIterator();

    // LinkedList<CAS> views = new LinkedList<CAS>();

    // einzenlnen Views auslesen
    while (viewIter.hasNext())
      casData.add(new CEVData((CAS) viewIter.next(), getStyleMap()));
  }

  /**
   * Anzahl der Views
   * 
   * @return Anzahl
   */
  public int count() {
    return casData.size();
  }

  /**
   * View mit entsprechenden index
   * 
   * @param index
   *          Index
   * @return View
   * @throws IndexOutOfBoundsException
   */
  public CEVData getCASData(int index) throws IndexOutOfBoundsException {
    return casData.get(index);
  }

  /**
   * alle Views
   * 
   * @return Views
   */
  public CEVData[] getCASData() {
    return casData.toArray(new CEVData[casData.size()]);
  }

  /**
   * Hauptview
   * 
   * @return View
   */
  public CAS getMainCas() {
    return mainCAS;
  }

  public void dispose() {
    for (CEVData each : casData) {
      each.dispose();
    }
  }

  public CAS createCas() throws ResourceInitializationException, InvalidXMLException {
    // CAS erzeugen
    if (descriptor instanceof AnalysisEngineDescription) {
      return CasCreationUtils.createCas((AnalysisEngineDescription) descriptor);
    } else if (descriptor instanceof TypeSystemDescription) {
      TypeSystemDescription tsDesc = (TypeSystemDescription) descriptor;
      tsDesc.resolveImports();
      return CasCreationUtils.createCas(tsDesc, null, new FsIndexDescription[0]);
    } else {
      throw new IllegalArgumentException("Invalid Type System Descriptor");
    }
  }

  public Map<String, StyleMapEntry> getStyleMap() {
    return style;
  }
}

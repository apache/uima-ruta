package org.apache.uima.tm.cev.views;

import org.apache.uima.tm.cev.CEVPlugin;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.data.tree.CEVAnnotationOrderedTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVFeatureTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVTypeTreeNode;
import org.apache.uima.tm.cev.data.tree.ICEVTreeNode;
import org.apache.uima.tm.cev.preferences.CEVPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.htmlparser.util.ParserUtils;
import org.htmlparser.util.Translate;


/**
 * LableProvider fuer den TreeView
 * 
 * @author Marco Nehmeier
 */
public class CEVAnnotationTreeViewLableProvider extends LabelProvider implements ILabelProvider {

  // zugrundeliegende CASData
  private CEVData casData;

  // Textrep
  private boolean text_repr;

  /**
   * Konstruktor
   * 
   * @param casData
   *          CASData
   */
  public CEVAnnotationTreeViewLableProvider(CEVData casData) {
    this.casData = casData;
    setTextRepr();
  }

  /**
   * setzen der Textrep anhand der Preferences
   */
  public void setTextRepr() {
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();
    String repr = store.getString(CEVPreferenceConstants.P_ANNOTATION_REPR);

    // Text
    if (repr.equals(CEVPreferenceConstants.P_ANNOTATION_REPR_TEXT))
      text_repr = true;
    // mit HTML-Tags
    else if (repr.equals(CEVPreferenceConstants.P_ANNOTATION_REPR_HTML))
      text_repr = false;
    else
      text_repr = true;
  }

  /**
   * aktuelle CAS setzen
   * 
   * @param casData
   *          CAS
   */
  public void setCASData(CEVData casData) {
    this.casData = casData;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object element) {
    if (element instanceof ICEVTreeNode && !(element instanceof CEVFeatureTreeNode))
      return casData.getIcon(((ICEVTreeNode) element).getType());
    /*
     * if (element instanceof CEVFeatureTreeNode) { if (((CEVFeatureTreeNode) element).hasChildren()
     * == true ) { return casData.getIcon() } }
     */
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element) {

    String text = "";

    if (element instanceof ICEVTreeNode) {
      if (element instanceof CEVAnnotationOrderedTreeNode) {
        text = ((CEVAnnotationOrderedTreeNode) element).getType().getShortName() + ": ";
      }

      String name = ((ICEVTreeNode) element).getName();

      if (name != null && text_repr) {
        name = ParserUtils.trimSpacesBeginEnd(ParserUtils
                .trimAllTags(Translate.decode(name), false), "");
        name = name.replaceAll("[\\n]", "").replaceAll("[\\r]", "");
      }
      if (element instanceof CEVTypeTreeNode) {
        text += TextUtils.shrinkNamespace(name);
      } else if (name != null) {
        text += name;
      }
      if (element instanceof CEVAnnotationTreeNode) {
        ICEVTreeNode parent = ((CEVAnnotationTreeNode) element).getParent();
        if (parent instanceof CEVTypeTreeNode) {
          text = name;
        } else if (parent instanceof CEVAnnotationTreeNode) {
          text = ((CEVAnnotationTreeNode) element).getType().getShortName() + ": " + name;
        }
      } else if (element instanceof CEVTypeTreeNode) {
        text += " [" + ((CEVTypeTreeNode) element).getChildren().length + "]";
      } else {
        // System.out.println(text);
      }
    }

    return text;
  }
}

/* First created by JCasGen Wed Apr 16 17:01:16 CEST 2008 */
package org.apache.uima.ruta.type;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.impl.TypeSystemImpl;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.tcas.Annotation;

/** 
 * Updated by JCasGen Fri Feb 16 11:29:59 CET 2018
 * XML source: C:/work/ws/ws-uima-ruta/ruta-v3/ruta-typesystem/target/jcasgen/typesystem.xml
 * @generated */
public class RutaBasic extends Annotation {

    private static final int INITIAL_CAPACITY = 2;

    private static final String ROOT_TYPE1 = "uima.tcas.Annotation";

    private static final String ROOT_TYPE2 = "uima.cas.AnnotationBase";

    private boolean lowMemoryProfile = false;

    private int[] partOf = new int[((TypeSystemImpl) getCAS().getTypeSystem()).getLargestTypeCode()];

    private Collection<?>[] beginMap = new ArrayList<?>[((TypeSystemImpl) getCAS().getTypeSystem()).getLargestTypeCode()];

    private Collection<?>[] endMap = new ArrayList<?>[((TypeSystemImpl) getCAS().getTypeSystem()).getLargestTypeCode()];

    private boolean empty = true;

    public boolean isEmpty() {
        return empty;
    }

    public boolean isLowMemoryProfile() {
        return lowMemoryProfile;
    }

    public void setLowMemoryProfile(boolean lowMemoryProfile) {
        this.lowMemoryProfile = lowMemoryProfile;
    }

    public void addPartOf(Type type) {
        int code = ((TypeImpl) type).getCode();
        addPartOf(code);
    }

    private void addPartOf(int code) {
        partOf[code] = partOf[code] + 1;
        if (!lowMemoryProfile) {
            int parentCode = getCAS().getTypeSystem().getLowLevelTypeSystem().ll_getParentType(code);
            if (parentCode > 0) {
                addPartOf(parentCode);
            }
        }
    }

    public void removePartOf(Type type) {
        int code = ((TypeImpl) type).getCode();
        removePartOf(code);
    }

    private void removePartOf(int code) {
        if (partOf[code] != 0) {
            partOf[code] = partOf[code] - 1;
            if (!lowMemoryProfile) {
                int parentCode = getCAS().getTypeSystem().getLowLevelTypeSystem().ll_getParentType(code);
                if (parentCode > 0) {
                    removePartOf(parentCode);
                }
            }
        }
    }

    public boolean isPartOf(Type type) {
        int code = ((TypeImpl) type).getCode();
        int count = partOf[code];
        if (count > 0) {
            return true;
        }
        if (lowMemoryProfile) {
            List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
            for (Type each : subsumedTypes) {
                int code2 = ((TypeImpl) each).getCode();
                int count2 = partOf[code2];
                if (count2 > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setPartOf(int[] partOf) {
        this.partOf = partOf;
    }

    public int[] getPartOf() {
        return partOf;
    }

    @SuppressWarnings("unchecked")
    public Collection<AnnotationFS> getBeginAnchors(Type type) {
        int code = ((TypeImpl) type).getCode();
        Collection<AnnotationFS> set = (Collection<AnnotationFS>) beginMap[code];
        if (lowMemoryProfile) {
            Collection<AnnotationFS> result = new ArrayList<AnnotationFS>();
            if (set != null) {
                result.addAll(set);
            }
            List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
            for (Type each : subsumedTypes) {
                int parentCode = ((TypeImpl) each).getCode();
                Collection<?> c = beginMap[parentCode];
                if (c != null) {
                    result.addAll((Collection<? extends AnnotationFS>) c);
                }
            }
            return result;
        } else {
            if (set == null) {
                return Collections.emptySet();
            } else {
                return set;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Collection<AnnotationFS> getEndAnchors(Type type) {
        int code = ((TypeImpl) type).getCode();
        Collection<AnnotationFS> set = (Collection<AnnotationFS>) endMap[code];
        if (lowMemoryProfile) {
            Collection<AnnotationFS> result = new ArrayList<AnnotationFS>();
            if (set != null) {
                result.addAll(set);
            }
            List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
            for (Type each : subsumedTypes) {
                int parentCode = ((TypeImpl) each).getCode();
                Collection<?> c = endMap[parentCode];
                if (c != null) {
                    result.addAll((Collection<? extends AnnotationFS>) c);
                }
            }
            return result;
        } else {
            if (set == null) {
                return Collections.emptySet();
            } else {
                return set;
            }
        }
    }

    public boolean beginsWith(Type type) {
        int code = ((TypeImpl) type).getCode();
        Collection<?> set = beginMap[code];
        boolean beginsWith = set != null && !set.isEmpty();
        if (beginsWith) {
            return true;
        }
        if (lowMemoryProfile) {
            List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
            for (Type each : subsumedTypes) {
                if (beginsWith(each)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean endsWith(Type type) {
        int code = ((TypeImpl) type).getCode();
        Collection<?> set = endMap[code];
        boolean endswith = set != null && !set.isEmpty();
        if (endswith) {
            return true;
        }
        if (lowMemoryProfile) {
            List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
            for (Type each : subsumedTypes) {
                if (endsWith(each)) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public void addBegin(AnnotationFS annotation, Type type) {
        empty = false;
        int code = ((TypeImpl) type).getCode();
        Collection<Object> set = (Collection<Object>) beginMap[code];
        if (set == null) {
            set = new ArrayList<Object>(INITIAL_CAPACITY);
            beginMap[code] = set;
        }
        set.add(annotation);
        if (!lowMemoryProfile && !type.getName().equals(ROOT_TYPE1) && !type.getName().equals(ROOT_TYPE2)) {
            TypeSystem typeSystem = getCAS().getTypeSystem();
            Type parent = typeSystem.getParent(type);
            if (parent != null) {
                addBegin(annotation, parent);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void addEnd(AnnotationFS annotation, Type type) {
        empty = false;
        int code = ((TypeImpl) type).getCode();
        Collection<Object> set = (Collection<Object>) endMap[code];
        if (set == null) {
            set = new ArrayList<Object>(INITIAL_CAPACITY);
            endMap[code] = set;
        }
        set.add(annotation);
        if (!lowMemoryProfile && !type.getName().equals(ROOT_TYPE1) && !type.getName().equals(ROOT_TYPE2)) {
            TypeSystem typeSystem = getCAS().getTypeSystem();
            Type parent = typeSystem.getParent(type);
            if (parent != null) {
                addEnd(annotation, parent);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void removeBegin(AnnotationFS annotation, Type type) {
        int code = ((TypeImpl) type).getCode();
        Collection<Object> set = (Collection<Object>) beginMap[code];
        if (set != null) {
            set.remove(annotation);
            if (set.isEmpty()) {
                beginMap[code] = null;
            }
        }
        if (!lowMemoryProfile) {
            TypeSystem typeSystem = getCAS().getTypeSystem();
            Type parent = typeSystem.getParent(type);
            if (parent != null) {
                removeBegin(annotation, parent);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void removeEnd(AnnotationFS annotation, Type type) {
        int code = ((TypeImpl) type).getCode();
        Collection<Object> set = (Collection<Object>) endMap[code];
        if (set != null) {
            set.remove(annotation);
            if (set.isEmpty()) {
                endMap[code] = null;
            }
        }
        if (!lowMemoryProfile) {
            TypeSystem typeSystem = getCAS().getTypeSystem();
            Type parent = typeSystem.getParent(type);
            if (parent != null) {
                removeEnd(annotation, parent);
            }
        }
    }

    public Collection<?>[] getBeginMap() {
        return beginMap;
    }

    public Collection<?>[] getEndMap() {
        return endMap;
    }

    public void setBeginMap(Collection<?>[] beginMap) {
        this.beginMap = beginMap;
        for (Collection<?> each : beginMap) {
            if (each != null && !each.isEmpty()) {
                this.empty = false;
                break;
            }
        }
    }

    public void setEndMap(Collection<?>[] endMap) {
        this.endMap = endMap;
        for (Collection<?> each : endMap) {
            if (each != null && !each.isEmpty()) {
                this.empty = false;
                break;
            }
        }
    }

    public void clearBeginMap() {
        this.beginMap = new ArrayList<?>[((TypeSystemImpl) getCAS().getTypeSystem()).getLargestTypeCode()];
    }

    public void clearEndMap() {
        this.endMap = new ArrayList<?>[((TypeSystemImpl) getCAS().getTypeSystem()).getLargestTypeCode()];
        for (Collection<?> each : beginMap) {
            if (each != null && !each.isEmpty()) {
                return;
            }
        }
        this.empty = true;
    }

    /** @generated
     * @ordered 
     */
    @SuppressWarnings ("hiding")
    public final static String _TypeName = "org.apache.uima.ruta.type.RutaBasic";
    
    /** @generated
     * @ordered 
     */
    @SuppressWarnings ("hiding")
    public final static int typeIndexID = JCasRegistry.register(RutaBasic.class);
    /** @generated
     * @ordered 
     */
    @SuppressWarnings ("hiding")
    public final static int type = typeIndexID;
    /** @generated
     * @return index of the type  
     */
    @Override
    public              int getTypeIndexID() {return typeIndexID;}
   
   
    /* *******************
     *   Feature Offsets *
     * *******************/ 
     
    public final static String _FeatName_replacement = "replacement";


    /* Feature Adjusted Offsets */
    private final static CallSite _FC_replacement = TypeSystemImpl.createCallSite(RutaBasic.class, "replacement");
    private final static MethodHandle _FH_replacement = _FC_replacement.dynamicInvoker();

     
    /** Never called.  Disable default constructor
     * @generated */
    protected RutaBasic() {/* intentionally empty block */}
      
    /** Internal - constructor used by generator 
     * @generated
     * @param casImpl the CAS this Feature Structure belongs to
     * @param type the type of this Feature Structure 
     */
    public RutaBasic(TypeImpl type, CASImpl casImpl) {
      super(type, casImpl);
      readObject();
    }
    
    /** @generated
     * @param jcas JCas to which this Feature Structure belongs 
     */
    public RutaBasic(JCas jcas) {
      super(jcas);
      readObject();   
    } 


    /** @generated
     * @param jcas JCas to which this Feature Structure belongs
     * @param begin offset to the begin spot in the SofA
     * @param end offset to the end spot in the SofA 
    */  
    public RutaBasic(JCas jcas, int begin, int end) {
      super(jcas);
      setBegin(begin);
      setEnd(end);
      readObject();
    }   

    /** 
     * <!-- begin-user-doc -->
     * Write your own initialization here
     * <!-- end-user-doc -->
     *
     * @generated modifiable 
     */
    private void readObject() {/*default - does nothing empty block */}
       
   
      
    //*--------------*
    //* Feature: replacement

    /** getter for replacement - gets 
     * @generated
     * @return value of the feature 
     */
    public String getReplacement() { return _getStringValueNc(wrapGetIntCatchException(_FH_replacement));}
      
    /** setter for replacement - sets  
     * @generated
     * @param v value to set into the feature 
     */
    public void setReplacement(String v) {
      _setStringValueNfc(wrapGetIntCatchException(_FH_replacement), v);
    }    
      

}

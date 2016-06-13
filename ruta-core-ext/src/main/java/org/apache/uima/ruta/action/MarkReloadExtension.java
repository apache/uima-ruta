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
package org.apache.uima.ruta.action;

import java.util.List;
import java.util.Map;

import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.extensions.IRutaActionExtension;
import org.apache.uima.ruta.extensions.RutaParseException;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;

public class MarkReloadExtension implements IRutaActionExtension{
  private final String[] knownExtensions = new String[] { "MARKFASTRELOAD", "MARKTABLERELOAD" };

  private final Class<?>[] extensions = new Class[] { MarkFastReloadAction.class, MarkTableReloadAction.class };

  public String verbalize(RutaElement element, RutaVerbalizer verbalizer) {
    if (element instanceof MarkFastReloadAction) {
      MarkFastAction a = (MarkFastAction) element;
      String list = "";
      if (a.getList() != null) {
        list = verbalizer.verbalize(a.getList());
      } else if (a.getStringList() != null) {
        list = verbalizer.verbalize(a.getStringList());
      }
      return knownExtensions[0] + verbalizer.verbalize(a.getType()) + ", " + list + ", "
              + verbalizer.verbalize(a.getIgnore()) + ", "
              + verbalizer.verbalize(a.getIgnoreLength()) + ", "
              + verbalizer.verbalize(a.getIgnoreWS()) + ")";
    }
    if (element instanceof MarkTableReloadAction) {
      MarkTableAction a = (MarkTableAction) element;
      ITypeExpression typeExpr = a.getTypeExpr();
      INumberExpression indexExpr = a.getIndexExpr();
      WordTableExpression tableExpr = a.getTableExpr();
      Map<IStringExpression, INumberExpression> featureMap = a.getFeatureMap();
      IBooleanExpression ignoreCase = a.getIgnoreCase();
      INumberExpression ignoreLength = a.getIgnoreLength();
      IStringExpression ignoreChar = a.getIgnoreChar();
      INumberExpression maxIgnoreChar = a.getMaxIgnoreChar();

      String type = verbalizer.verbalize(typeExpr);
      String index = verbalizer.verbalize(indexExpr);
      String table = verbalizer.verbalize(tableExpr);
      String map = "";
      if (featureMap != null) {
        map += ", ";
        for (IStringExpression each : featureMap.keySet()) {
          map += verbalizer.verbalize(each);
          map += " = ";
          map += verbalizer.verbalize(featureMap.get(each));
          map += ", ";
        }
      }
      String icase = ignoreCase == null ? "" : ", " + verbalizer.verbalize(ignoreCase);
      String ilength = ignoreCase == null ? "" : ", " + verbalizer.verbalize(ignoreLength);
      String ichar = ignoreCase == null ? "" : ", " + verbalizer.verbalize(ignoreChar);
      String mic = ignoreCase == null ? "" : ", " + verbalizer.verbalize(maxIgnoreChar);

      return knownExtensions[1] + type + ", " + index + ", " + table + map + icase + ilength + ichar + mic + ")";
    } else {
      return "UnknownAction";
    }
  }

  
  @SuppressWarnings("unchecked")
  @Override
  public AbstractRutaAction createAction(String name, List<RutaExpression> args)
          throws RutaParseException {
    INumberExpression ignoreLength = null;
    ITypeExpression typeExpr = null;
    switch (name) {
      case "MARKFASTRELOAD":
        
        if(args.size() < 2) {
          throw new RutaParseException("Not enough arguments for action " + name +": " + args);
        }
        IStringExpression listName= null;
        IBooleanExpression ignore= null;
        IBooleanExpression ignoreWS= null;
        if(args.get(0) instanceof ITypeExpression) {
          typeExpr = (ITypeExpression) args.get(0);
        } else {
          throw new RutaParseException("Expected typeExpr expression argument typeExpr for action " + name +" but got " + args.get(0).getClass().getName());
        }
        if(args.get(1) instanceof IStringExpression) {
          listName = (IStringExpression) args.get(1);
        } else {
          throw new RutaParseException("Expected string expression argument listName for action " + name +" but got " + args.get(1).getClass().getName());
        }
        if(args.size() > 2 &&  args.get(2) instanceof IBooleanExpression) {
          ignore = (IBooleanExpression) args.get(2);
        } else {
          throw new RutaParseException("Expected boolean expression argument ignore for action " + name +" but got " + args.get(2).getClass().getName());
        }
        if(args.size() > 3 &&  args.get(3) instanceof INumberExpression) {
          ignoreLength = (INumberExpression) args.get(3);
        } else {
          throw new RutaParseException("Expected number expression argument ignoreLength for action " + name +" but got " + args.get(3).getClass().getName());
        }
        if(args.size() > 4 &&  args.get(4) instanceof IBooleanExpression) {
          ignoreWS = (IBooleanExpression) args.get(4);
        } else {
          throw new RutaParseException("Expected boolean expression argument ignoreWS for action " + name +" but got " + args.get(4).getClass().getName());
        }

        return new MarkFastReloadAction(typeExpr, listName, ignore, ignoreLength, ignoreWS);

      case "MARKTABLERELOAD":
        
        if(args.size() < 3) {
          throw new RutaParseException("Not enough arguments for action " + name +": " + args);
        }
        
        INumberExpression indexExpr = null;
        IStringExpression tableName = null;
        Map<IStringExpression, INumberExpression> featureMap = null;
        IBooleanExpression ignoreCase = null;
        IStringExpression ignoreChar = null;
        INumberExpression maxIgnoreChar = null;
        if(args.get(0) instanceof ITypeExpression) {
          typeExpr = (ITypeExpression) args.get(0);
        } else {
          throw new RutaParseException("Expected type expression argument typeExpr for action " + name +" but got " + args.get(0).getClass().getName());
        }
        if(args.get(1) instanceof INumberExpression) {
          indexExpr = (INumberExpression) args.get(1);
        } else {
          throw new RutaParseException("Expected number expression argument indexExpr for action " + name +" but got " + args.get(1).getClass().getName());
        }
        if(args.get(2) instanceof IStringExpression) {
          tableName = (IStringExpression) args.get(2);
        } else {
          throw new RutaParseException("Expected string expression argument tableName for action " + name +" but got " + args.get(2).getClass().getName());
        }
        if(args.size() > 3 &&  args.get(3) instanceof Map) {
          featureMap = (Map<IStringExpression, INumberExpression>) args.get(3);
        } else {
          throw new RutaParseException("Expected map argument featureMap for action " + name +" but got " + args.get(3).getClass().getName());
        }
        if(args.size() > 4 &&  args.get(4) instanceof IBooleanExpression) {
          ignoreCase = (IBooleanExpression) args.get(4);
        } else {
          throw new RutaParseException("Expected boolean expression argument ignoreCase for action " + name +" but got " + args.get(4).getClass().getName());
        }
        if(args.size() > 5 &&  args.get(5) instanceof INumberExpression) {
          ignoreLength = (INumberExpression) args.get(5);
        } else {
          throw new RutaParseException("Expected number expression argument ignoreLength for action " + name +" but got " + args.get(5).getClass().getName());
        }
        if(args.size() > 6 &&  args.get(6) instanceof IStringExpression) {
          ignoreChar = (IStringExpression) args.get(6);
        } else {
          throw new RutaParseException("Expected boolean expression argument ignoreChar for action " + name +" but got " + args.get(6).getClass().getName());
        }
        if(args.size() > 7 &&  args.get(7) instanceof INumberExpression) {
          maxIgnoreChar = (INumberExpression) args.get(7);
        } else {
          throw new RutaParseException("Expected number expression argument maxIgnoreChar for action " + name +" but got " + args.get(7).getClass().getName());
        }
        return new MarkTableReloadAction(typeExpr, indexExpr, tableName, featureMap, ignoreCase, ignoreLength, ignoreChar, maxIgnoreChar);
      
      default:
        break;
    }
    return null;
  }


  public String verbalizeName(RutaElement element) {
    if(element instanceof MarkFastReloadAction) {
      return knownExtensions[0];
    }
    
    if(element instanceof MarkTableReloadAction) {
      return knownExtensions[1];
    }
    return "<unknown>";
  }

  public String[] getKnownExtensions() {
    return knownExtensions;
  }

  public Class<?>[] extensions() {
    return extensions;
  }

  
}

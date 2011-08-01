package org.apache.uima.tm.textmarker.testing.ui.views.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.tm.textmarker.testing.ui.views.TestCasData;
import org.eclipse.jface.viewers.TableViewer;


public class Caretaker {

  private class SaveStateAdapter implements Runnable {

    private TableViewer viewer;

    private ArrayList list;

    public SaveStateAdapter(TableViewer viewer, ArrayList list) {
      this.viewer = viewer;
      this.list = list;
    }

    @Override
    public void run() {
      viewer.setInput(list);

    }

  }

  LinkedList<ArrayList<TestCasData>> memento;

  int index;

  public Caretaker() {
    memento = new LinkedList();
    memento.add(new ArrayList<TestCasData>());
    index = 0;

  }

  public Caretaker(ArrayList<TestCasData> firstElement) {
    memento = new LinkedList();
    memento.add(firstElement);
    index = 0;
  }

  public void saveState(TableViewer viewer) {
    ArrayList state = (ArrayList) viewer.getInput();

    index++;

    ArrayList<TestCasData> newState = new ArrayList<TestCasData>();

    Iterator<TestCasData> iter = state.iterator();

    if (index < memento.size()) {
      for (int i = index; i < memento.size(); i++) {
        memento.remove(i);
      }
    }

    while (iter.hasNext()) {
      newState.add(iter.next());
    }
    memento.add(index, state);

    viewer.getControl().getDisplay().asyncExec(new SaveStateAdapter(viewer, newState));
  }

  public Object getMemento(int newIndex) {
    if (index <= 0) {
      index = 0;
      return memento.get(0);
    }

    if (memento.size() > index) {
      return memento.get(index);
    }

    if (memento.size() <= index) {
      index = memento.size() - 1;
      return memento.get(memento.size() - 1);
    }

    index = memento.size();
    return memento.get(index);
  }

  public Object getPreviousState() {
    index--;
    if (index < 0) {
      index = 0;
    }
    return getMemento(index);
  }

  public Object getNextState() {
    index++;
    if (index > memento.size() - 1) {
      index = memento.size() - 1;
    }
    return getMemento(index);
  }
}

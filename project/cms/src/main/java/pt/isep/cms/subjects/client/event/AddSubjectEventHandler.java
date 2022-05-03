package pt.isep.cms.subjects.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddSubjectEventHandler extends EventHandler {
  void onAddSubject(AddSubjectEvent event);
}

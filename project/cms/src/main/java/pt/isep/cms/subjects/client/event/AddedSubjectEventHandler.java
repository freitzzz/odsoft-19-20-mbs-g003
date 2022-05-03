package pt.isep.cms.subjects.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddedSubjectEventHandler extends EventHandler {
  void onAddedSubject(AddedSubjectEvent event);
}

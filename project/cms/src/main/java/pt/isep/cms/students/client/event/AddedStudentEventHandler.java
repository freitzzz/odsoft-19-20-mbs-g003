package pt.isep.cms.students.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddedStudentEventHandler extends EventHandler {
  void onAddedStudent(AddedStudentEvent event);
}

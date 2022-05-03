package pt.isep.cms.teachers.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddedTeacherEventHandler extends EventHandler {
  void onAddedTeacher(AddedTeacherEvent event);
}

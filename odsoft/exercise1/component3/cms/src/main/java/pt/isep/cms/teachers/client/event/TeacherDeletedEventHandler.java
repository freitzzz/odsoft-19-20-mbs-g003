package pt.isep.cms.teachers.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface TeacherDeletedEventHandler extends EventHandler {
  void onTeacherDeleted(TeacherDeletedEvent event);
}

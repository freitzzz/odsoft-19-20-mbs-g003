package pt.isep.cms.teachers.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class TeacherDeletedEvent extends GwtEvent<TeacherDeletedEventHandler>{
  public static Type<TeacherDeletedEventHandler> TYPE = new Type<TeacherDeletedEventHandler>();

  @Override
  public Type<TeacherDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(TeacherDeletedEventHandler handler) {
    handler.onTeacherDeleted(this);
  }
}

package pt.isep.cms.teachers.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddedTeacherEvent extends GwtEvent<AddedTeacherEventHandler> {
  public static Type<AddedTeacherEventHandler> TYPE = new Type<AddedTeacherEventHandler>();
  
  @Override
  public Type<AddedTeacherEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddedTeacherEventHandler handler) {
    handler.onAddedTeacher(this);
  }
}

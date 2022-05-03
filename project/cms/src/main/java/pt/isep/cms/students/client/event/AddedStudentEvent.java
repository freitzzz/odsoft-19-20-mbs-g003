package pt.isep.cms.students.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddedStudentEvent extends GwtEvent<AddedStudentEventHandler> {
  public static Type<AddedStudentEventHandler> TYPE = new Type<AddedStudentEventHandler>();
  
  @Override
  public Type<AddedStudentEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddedStudentEventHandler handler) {
    handler.onAddedStudent(this);
  }
}

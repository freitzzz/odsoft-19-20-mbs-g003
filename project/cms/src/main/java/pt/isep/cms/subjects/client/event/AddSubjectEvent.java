package pt.isep.cms.subjects.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddSubjectEvent extends GwtEvent<AddSubjectEventHandler> {
  public static Type<AddSubjectEventHandler> TYPE = new Type<AddSubjectEventHandler>();
  
  @Override
  public Type<AddSubjectEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddSubjectEventHandler handler) {
    handler.onAddSubject(this);
  }
}

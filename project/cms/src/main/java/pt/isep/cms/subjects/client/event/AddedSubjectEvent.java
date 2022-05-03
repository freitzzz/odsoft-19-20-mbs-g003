package pt.isep.cms.subjects.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddedSubjectEvent extends GwtEvent<AddedSubjectEventHandler> {
  public static Type<AddedSubjectEventHandler> TYPE = new Type<AddedSubjectEventHandler>();
  
  @Override
  public Type<AddedSubjectEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddedSubjectEventHandler handler) {
    handler.onAddedSubject(this);
  }
}

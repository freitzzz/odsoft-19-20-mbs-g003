package pt.isep.cms.classes.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddedCMSClassEvent extends GwtEvent<AddedCMSClassEventHandler> {
  public static Type<AddedCMSClassEventHandler> TYPE = new Type<AddedCMSClassEventHandler>();
  
  @Override
  public Type<AddedCMSClassEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddedCMSClassEventHandler handler) {
    handler.onAddedCMSClass(this);
  }
}

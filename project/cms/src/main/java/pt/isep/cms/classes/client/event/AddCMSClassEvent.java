package pt.isep.cms.classes.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddCMSClassEvent extends GwtEvent<AddCMSClassEventHandler> {
  public static Type<AddCMSClassEventHandler> TYPE = new Type<AddCMSClassEventHandler>();
  
  @Override
  public Type<AddCMSClassEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddCMSClassEventHandler handler) {
    handler.onAddCMSClass(this);
  }
}

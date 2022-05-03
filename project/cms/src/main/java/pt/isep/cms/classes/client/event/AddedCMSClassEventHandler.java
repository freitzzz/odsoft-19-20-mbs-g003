package pt.isep.cms.classes.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddedCMSClassEventHandler extends EventHandler {
  void onAddedCMSClass(AddedCMSClassEvent event);
}

package pt.isep.cms.teachers.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ViewTeacherDetailsEvent extends GwtEvent<ViewTeacherDetailsEventHandler> {
  public static Type<ViewTeacherDetailsEventHandler> TYPE = new Type<ViewTeacherDetailsEventHandler>();
  private final long id;

  public ViewTeacherDetailsEvent(long id) {
    this.id = id;
  }

  public long getId() { return id; }

  @Override
  public Type<ViewTeacherDetailsEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ViewTeacherDetailsEventHandler handler) {
    handler.onViewTeacherDetails(this);
  }
}

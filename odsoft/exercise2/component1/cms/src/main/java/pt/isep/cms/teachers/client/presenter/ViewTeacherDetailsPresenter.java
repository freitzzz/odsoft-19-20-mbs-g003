package pt.isep.cms.teachers.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.teachers.client.TeachersServiceAsync;
import pt.isep.cms.teachers.shared.Teacher;

public class ViewTeacherDetailsPresenter implements Presenter {
  public interface Display {
    HasValue<String> getId();

    HasValue<String> getName();

    HasValue<String> getGender();

    HasValue<String> getBirthDate();

    HasClickHandlers getCancelButton();

    void show();

    void hide();
  }

  private Teacher teacher;
  private final TeachersServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;

  public ViewTeacherDetailsPresenter(TeachersServiceAsync rpcService, HandlerManager eventBus, Display display, long id) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.teacher = new Teacher();
    this.display = display;

    rpcService.getTeacherRecordDetails(id, new AsyncCallback<Teacher>(){
    
      @Override
      public void onSuccess(Teacher result) {
        teacher = result;
        ViewTeacherDetailsPresenter.this.display.getId().setValue(String.valueOf(teacher.id()));
        ViewTeacherDetailsPresenter.this.display.getName().setValue(teacher.name());
        ViewTeacherDetailsPresenter.this.display.getGender().setValue(teacher.gender());

        DateTimeFormat format = DateTimeFormat.getFormat("yyyy-MM-dd");
        String dateString = format.format(teacher.birthDate());

        ViewTeacherDetailsPresenter.this.display.getBirthDate().setValue(dateString);
      }
    
      @Override
      public void onFailure(Throwable caught) {
        Window.alert("Error retrieving teacher");
      }
    });
    bind();
  }

  public void bind() {
    display.getCancelButton().addClickHandler(new ClickHandler(){
    
      @Override
      public void onClick(ClickEvent event) {
        display.hide();
      }
    });
  }

  @Override
  public void go(HasWidgets container) {
    display.show();
  }

}
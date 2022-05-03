package pt.isep.cms.teachers.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import pt.isep.cms.teachers.client.TeachersServiceAsync;
import pt.isep.cms.teachers.client.event.AddTeacherEvent;
import pt.isep.cms.teachers.client.event.AddedTeacherEvent;
import pt.isep.cms.teachers.shared.Gender;
import pt.isep.cms.teachers.shared.Teacher;

public class AddTeacherPresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getFirstName();

		HasValue<String> getLastName();
		
		String getGender();
		
		HasValue<java.util.Date> getBirthDate();

		void show();

		void hide();
	}

	private Teacher teacher;
	private final TeachersServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public AddTeacherPresenter(TeachersServiceAsync rpcService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();
	}

	public AddTeacherPresenter(TeachersServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

	}

	public void bind() {
		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					doSave();
					display.hide();
				}catch(IllegalArgumentException e) {
					Window.alert(e.getMessage());
				}catch(Exception unknownException) {
					Window.alert("An unexpected error occurred while adding the teacher");
					display.hide();
				}
			}
		});

		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				display.hide();
			}
		});
	}

	public void go(final HasWidgets container) {
		display.show();
	}

	private void doSave() {
		teacher = new Teacher(
			display.getFirstName().getValue(),
			display.getLastName().getValue(),
			Gender.parse(display.getGender()),
			display.getBirthDate().getValue()
		);
			
		// Adding new teacher
		rpcService.addTeacher(teacher, new AsyncCallback<Teacher>() {
			public void onSuccess(Teacher result) {
				eventBus.fireEvent(new AddedTeacherEvent());
			}

			public void onFailure(Throwable caught) {
				throw new IllegalStateException(caught);
			}
		});
	}

}

package pt.isep.cms.students.client.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.classes.client.CMSClassesServiceAsync;
import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.classes.shared.CMSClassDetails;
import pt.isep.cms.common.shared.Gender;
import pt.isep.cms.students.client.StudentsServiceAsync;
import pt.isep.cms.students.client.event.AddedStudentEvent;
import pt.isep.cms.students.shared.Student;

public class AddStudentPresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getFirstName();

		HasValue<String> getLastName();
		
		CMSClassDetails getCMSClass();
		
		String getGender();
		
		HasValue<java.util.Date> getBirthDate();
		
		void setCMSClasses(List<CMSClassDetails> cmsClassesDetails);

		void show();

		void hide();
	}

	private Student student;
	private final StudentsServiceAsync rpcService;
	private final CMSClassesServiceAsync cmsClassesRpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public AddStudentPresenter(StudentsServiceAsync rpcService, CMSClassesServiceAsync cmsClassesRpcService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.cmsClassesRpcService = cmsClassesRpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();
	}

	public AddStudentPresenter(StudentsServiceAsync rpcService, CMSClassesServiceAsync cmsClassesRpcService, HandlerManager eventBus, Display display, String id) {
		this.rpcService = rpcService;
		this.cmsClassesRpcService = cmsClassesRpcService;
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
					unknownException.printStackTrace();
					Window.alert(unknownException.toString());
					Window.alert("An unexpected error occurred while adding the student");
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
		fetchClassesDetails();
	}

	private void doSave() {
		
		CMSClass cmsClass = new CMSClass(display.getCMSClass().externalIdentifier() ,display.getCMSClass().name());
		
		student = new Student(
			new Random().nextInt(),
			display.getFirstName().getValue(),
			display.getLastName().getValue(),
			Gender.parse(display.getGender()),
			display.getBirthDate().getValue(),
			cmsClass
		);
			
		// Adding new student
		rpcService.addStudent(student, new AsyncCallback<Student>() {
			public void onSuccess(Student result) {
				eventBus.fireEvent(new AddedStudentEvent());
			}

			public void onFailure(Throwable caught) {
				throw new IllegalStateException(caught);
			}
		});
	}
	
	private void fetchClassesDetails() {
		
		cmsClassesRpcService.getCMSClassDetails(new AsyncCallback<ArrayList<CMSClassDetails>>() {
			public void onSuccess(ArrayList<CMSClassDetails> result) {
				display.setCMSClasses(result);
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				Window.alert("Error fetching cms classes details");
			}
		});
	}

}

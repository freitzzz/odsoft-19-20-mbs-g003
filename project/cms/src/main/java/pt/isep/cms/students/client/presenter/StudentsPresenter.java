package pt.isep.cms.students.client.presenter;

import pt.isep.cms.students.client.StudentsServiceAsync;
import pt.isep.cms.students.client.event.AddStudentEvent;
import pt.isep.cms.students.shared.StudentDetails;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

public class StudentsPresenter implements Presenter {

	private List<StudentDetails> studentDetails;

	public interface Display {
		HasClickHandlers getAddButton();

		void setData(List<String> data);
		
		Widget asWidget();
	}

	private final StudentsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public StudentsPresenter(StudentsServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddStudentEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

		fetchStudentDetails();
	}

	public void setStudentDetails(List<StudentDetails> studentDetails) {
		this.studentDetails = studentDetails;
	}

	private void fetchStudentDetails() {
		rpcService.getStudentDetails(new AsyncCallback<ArrayList<StudentDetails>>() {
			public void onSuccess(ArrayList<StudentDetails> result) {
				studentDetails = result;
				
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(studentDetails.get(i).name());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				Window.alert("Error fetching student details");
			}
		});
	}
}

package pt.isep.cms.teachers.client.presenter;

import pt.isep.cms.teachers.client.TeachersServiceAsync;
import pt.isep.cms.teachers.client.event.AddTeacherEvent;
import pt.isep.cms.teachers.shared.TeacherDetails;

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

public class TeachersPresenter implements Presenter {

	private List<TeacherDetails> teacherDetails;

	public interface Display {
		HasClickHandlers getAddButton();

		void setData(List<String> data);
		
		Widget asWidget();
	}

	private final TeachersServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public TeachersPresenter(TeachersServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddTeacherEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

		fetchTeacherDetails();
	}

	public void setTeacherDetails(List<TeacherDetails> teacherDetails) {
		this.teacherDetails = teacherDetails;
	}

	private void fetchTeacherDetails() {
		rpcService.getTeacherDetails(new AsyncCallback<ArrayList<TeacherDetails>>() {
			public void onSuccess(ArrayList<TeacherDetails> result) {
				teacherDetails = result;
				
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(teacherDetails.get(i).name());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				Window.alert("Error fetching teacher details");
			}
		});
	}
}

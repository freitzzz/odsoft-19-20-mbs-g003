package pt.isep.cms.teachers.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import pt.isep.cms.teachers.client.TeachersServiceAsync;
import pt.isep.cms.teachers.client.event.AddTeacherEvent;
import pt.isep.cms.teachers.client.event.ViewTeacherDetailsEvent;
import pt.isep.cms.teachers.shared.TeacherDetails;

public class TeachersPresenter implements Presenter {

	private List<TeacherDetails> teacherDetails;

	public interface Display {
		HasClickHandlers getList();
		int getClickedRow(ClickEvent event);

		HasClickHandlers getAddButton();

		HasClickHandlers getDeleteButton();

		void setData(List<String> data);
		
		Widget asWidget();

		List<Integer> getSelectedRows();
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

		display.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.out.println("ONCLICK");
				deleteSelectedTeachers();
			}
		});

		display.getList().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				int selectedRow = display.getClickedRow(event);

				if (selectedRow >= 0) {
					long id = teacherDetails.get(selectedRow).id();

					eventBus.fireEvent(new ViewTeacherDetailsEvent(id));
				}
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

	public void sortTeacherDetails() {

		// Yes, we could use a more optimized method of sorting, but the
		// point is to create a test case that helps illustrate the higher
		// level concepts used when creating MVP-based applications.
		//
		for (int i = 0; i < teacherDetails.size(); ++i) {
			for (int j = 0; j < teacherDetails.size() - 1; ++j) {
				if (teacherDetails.get(j).name()
						.compareToIgnoreCase(teacherDetails.get(j + 1).name()) >= 0) {
					TeacherDetails tmp = teacherDetails.get(j);
					teacherDetails.set(j, teacherDetails.get(j + 1));
					teacherDetails.set(j + 1, tmp);
				}
			}
		}
	}

	private void deleteSelectedTeachers() {
		System.out.println("CONANJSKADNJAKSDNJASK");
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> ids = new ArrayList<>();

		for (int i = 0; i < selectedRows.size(); ++i) {
			ids.add(String.valueOf(teacherDetails.get(selectedRows.get(i)).id()));
		}

		rpcService.deleteTeachers(ids, new AsyncCallback<ArrayList<TeacherDetails>>() {
			public void onSuccess(ArrayList<TeacherDetails> result) {
				teacherDetails = result;
				sortTeacherDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(teacherDetails.get(i).name());
				}

				display.setData(data);

			}

			public void onFailure(Throwable caught) {
				Window.alert("Error deleting selected teachers");
			}
		});
	}
}

package pt.isep.cms.subjects.client.presenter;

import pt.isep.cms.subjects.client.SubjectsServiceAsync;
import pt.isep.cms.subjects.client.event.AddSubjectEvent;
import pt.isep.cms.subjects.shared.SubjectDetails;

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

public class SubjectsPresenter implements Presenter {

	private List<SubjectDetails> subjectDetails;

	public interface Display {
		HasClickHandlers getAddButton();

		void setData(List<String> data);
		
		Widget asWidget();
	}

	private final SubjectsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public SubjectsPresenter(SubjectsServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddSubjectEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

		fetchSubjectDetails();
	}

	public void setSubjectDetails(List<SubjectDetails> subjectDetails) {
		this.subjectDetails = subjectDetails;
	}

	private void fetchSubjectDetails() {
		rpcService.getSubjectDetails(new AsyncCallback<ArrayList<SubjectDetails>>() {
			public void onSuccess(ArrayList<SubjectDetails> result) {
				subjectDetails = result;
				
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(subjectDetails.get(i).name());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				Window.alert("Error fetching subject details");
			}
		});
	}
}

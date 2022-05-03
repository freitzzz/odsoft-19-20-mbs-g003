package pt.isep.cms.subjects.client.presenter;

import java.util.Random;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.subjects.client.SubjectsServiceAsync;
import pt.isep.cms.subjects.client.event.AddedSubjectEvent;
import pt.isep.cms.subjects.shared.Subject;

public class AddSubjectPresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getName();

		HasValue<String> getAcronym();

		void show();

		void hide();
	}

	private Subject subject;
	private final SubjectsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public AddSubjectPresenter(SubjectsServiceAsync rpcService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();
	}

	public AddSubjectPresenter(SubjectsServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
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
					Window.alert("An unexpected error occurred while adding the subject");
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
		
		subject = new Subject(
			new Random().nextInt(),
			display.getName().getValue(),
			display.getAcronym().getValue()
		);
			
		// Adding new subject
		rpcService.addSubject(subject, new AsyncCallback<Subject>() {
			public void onSuccess(Subject result) {
				eventBus.fireEvent(new AddedSubjectEvent());
			}

			public void onFailure(Throwable caught) {
				throw new IllegalStateException(caught);
			}
		});
	}

}

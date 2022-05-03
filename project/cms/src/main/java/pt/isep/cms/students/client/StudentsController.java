package pt.isep.cms.students.client;

import pt.isep.cms.classes.client.CMSClassesServiceAsync;
import pt.isep.cms.client.ShowcaseConstants;

import pt.isep.cms.students.client.event.AddStudentEvent;
import pt.isep.cms.students.client.event.AddStudentEventHandler;
import pt.isep.cms.students.client.event.AddedStudentEvent;
import pt.isep.cms.students.client.event.AddedStudentEventHandler;
import pt.isep.cms.students.client.presenter.StudentsPresenter;
import pt.isep.cms.students.client.presenter.AddStudentPresenter;
import pt.isep.cms.students.client.presenter.Presenter;
import pt.isep.cms.students.client.view.StudentsView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.students.client.view.AddStudentDialog;

public class StudentsController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final StudentsServiceAsync rpcService;
	private final CMSClassesServiceAsync cmsClassesRpcService;
	private HasWidgets container;

	public static interface CwConstants extends Constants {
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	public StudentsController(StudentsServiceAsync rpcService, CMSClassesServiceAsync cmsClassesRpcService, HandlerManager eventBus, ShowcaseConstants constants) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.cmsClassesRpcService = cmsClassesRpcService;
		this.constants = constants;
		this.globalConstants = constants;

		bind();
	}

	private void bind() {
		// (ATB) No History at this level
		// History.addValueChangeHandler(this);

		eventBus.addHandler(AddStudentEvent.TYPE, new AddStudentEventHandler() {
			public void onAddStudent(AddStudentEvent event) {
				doAddNewStudent();
			}
		});
		
		eventBus.addHandler(AddedStudentEvent.TYPE, new AddedStudentEventHandler() {
			
			@Override
			public void onAddedStudent(AddedStudentEvent event) {
				doAddedNewStudent();
			}
		});
	}

	private void doAddNewStudent() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new AddStudentPresenter(rpcService, cmsClassesRpcService , eventBus, new AddStudentDialog(globalConstants));
		presenter.go(container);

	}
	
	private void doAddedNewStudent() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new StudentsPresenter(rpcService, eventBus, new StudentsView());
		presenter.go(container);

	}

	public void go(final HasWidgets container) {
		this.container = container;

		Presenter presenter = new StudentsPresenter(rpcService, eventBus, new StudentsView());
		presenter.go(container);
	}

}

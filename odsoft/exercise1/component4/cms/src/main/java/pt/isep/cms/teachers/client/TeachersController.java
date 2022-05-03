package pt.isep.cms.teachers.client;

import pt.isep.cms.client.ShowcaseConstants;

import pt.isep.cms.teachers.client.event.AddTeacherEvent;
import pt.isep.cms.teachers.client.event.AddTeacherEventHandler;
import pt.isep.cms.teachers.client.event.AddedTeacherEvent;
import pt.isep.cms.teachers.client.event.AddedTeacherEventHandler;
import pt.isep.cms.teachers.client.presenter.TeachersPresenter;
import pt.isep.cms.teachers.client.presenter.AddTeacherPresenter;
import pt.isep.cms.teachers.client.presenter.Presenter;
import pt.isep.cms.teachers.client.view.TeachersView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.teachers.client.view.AddTeacherDialog;

public class TeachersController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final TeachersServiceAsync rpcService;
	private HasWidgets container;

	public static interface CwConstants extends Constants {
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	public TeachersController(TeachersServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.constants = constants;
		this.globalConstants = constants;

		bind();
	}

	private void bind() {
		// (ATB) No History at this level
		// History.addValueChangeHandler(this);

		eventBus.addHandler(AddTeacherEvent.TYPE, new AddTeacherEventHandler() {
			public void onAddTeacher(AddTeacherEvent event) {
				doAddNewTeacher();
			}
		});
		
		eventBus.addHandler(AddedTeacherEvent.TYPE, new AddedTeacherEventHandler() {
			
			@Override
			public void onAddedTeacher(AddedTeacherEvent event) {
				doAddedNewTeacher();
			}
		});
	}

	private void doAddNewTeacher() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new AddTeacherPresenter(rpcService, eventBus, new AddTeacherDialog(globalConstants));
		presenter.go(container);

	}
	
	private void doAddedNewTeacher() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new TeachersPresenter(rpcService, eventBus, new TeachersView());
		presenter.go(container);

	}

	public void go(final HasWidgets container) {
		this.container = container;

		Presenter presenter = new TeachersPresenter(rpcService, eventBus, new TeachersView());
		presenter.go(container);
	}

}

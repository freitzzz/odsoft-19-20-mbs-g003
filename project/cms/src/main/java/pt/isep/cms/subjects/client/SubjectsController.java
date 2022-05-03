package pt.isep.cms.subjects.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.subjects.client.event.AddSubjectEvent;
import pt.isep.cms.subjects.client.event.AddSubjectEventHandler;
import pt.isep.cms.subjects.client.event.AddedSubjectEvent;
import pt.isep.cms.subjects.client.event.AddedSubjectEventHandler;
import pt.isep.cms.subjects.client.presenter.AddSubjectPresenter;
import pt.isep.cms.subjects.client.presenter.Presenter;
import pt.isep.cms.subjects.client.presenter.SubjectsPresenter;
import pt.isep.cms.subjects.client.view.AddSubjectDialog;
import pt.isep.cms.subjects.client.view.SubjectsView;

public class SubjectsController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final SubjectsServiceAsync rpcService;
	private HasWidgets container;

	public static interface CwConstants extends Constants {
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	public SubjectsController(SubjectsServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.constants = constants;
		this.globalConstants = constants;

		bind();
	}

	private void bind() {
		// (ATB) No History at this level
		// History.addValueChangeHandler(this);

		eventBus.addHandler(AddSubjectEvent.TYPE, new AddSubjectEventHandler() {
			public void onAddSubject(AddSubjectEvent event) {
				doAddNewSubject();
			}
		});
		
		eventBus.addHandler(AddedSubjectEvent.TYPE, new AddedSubjectEventHandler() {
			
			@Override
			public void onAddedSubject(AddedSubjectEvent event) {
				doAddedNewSubject();
			}
		});
	}

	private void doAddNewSubject() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new AddSubjectPresenter(rpcService, eventBus, new AddSubjectDialog(globalConstants));
		presenter.go(container);

	}
	
	private void doAddedNewSubject() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new SubjectsPresenter(rpcService, eventBus, new SubjectsView());
		presenter.go(container);

	}

	public void go(final HasWidgets container) {
		this.container = container;

		Presenter presenter = new SubjectsPresenter(rpcService, eventBus, new SubjectsView());
		presenter.go(container);
	}

}

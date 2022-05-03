package pt.isep.cms.classes.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.classes.client.event.AddCMSClassEvent;
import pt.isep.cms.classes.client.event.AddCMSClassEventHandler;
import pt.isep.cms.classes.client.event.AddedCMSClassEvent;
import pt.isep.cms.classes.client.event.AddedCMSClassEventHandler;
import pt.isep.cms.classes.client.presenter.AddCMSClassPresenter;
import pt.isep.cms.classes.client.presenter.CMSClassesPresenter;
import pt.isep.cms.classes.client.presenter.Presenter;
import pt.isep.cms.classes.client.view.AddCMSClassDialog;
import pt.isep.cms.classes.client.view.CMSClassView;
import pt.isep.cms.client.ShowcaseConstants;

public class CMSClassesController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final CMSClassesServiceAsync rpcService;
	private HasWidgets container;

	public static interface CwConstants extends Constants {
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	public CMSClassesController(CMSClassesServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.constants = constants;
		this.globalConstants = constants;

		bind();
	}

	private void bind() {
		// (ATB) No History at this level
		// History.addValueChangeHandler(this);

		eventBus.addHandler(AddCMSClassEvent.TYPE, new AddCMSClassEventHandler() {
			public void onAddCMSClass(AddCMSClassEvent event) {
				doAddNewCMSClass();
			}
		});
		
		eventBus.addHandler(AddedCMSClassEvent.TYPE, new AddedCMSClassEventHandler() {
			
			@Override
			public void onAddedCMSClass(AddedCMSClassEvent event) {
				doAddedNewCMSClass();
			}
		});
	}

	private void doAddNewCMSClass() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new AddCMSClassPresenter(rpcService, eventBus, new AddCMSClassDialog(globalConstants));
		presenter.go(container);

	}
	
	private void doAddedNewCMSClass() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new CMSClassesPresenter(rpcService, eventBus, new CMSClassView());
		presenter.go(container);

	}

	public void go(final HasWidgets container) {
		this.container = container;

		Presenter presenter = new CMSClassesPresenter(rpcService, eventBus, new CMSClassView());
		presenter.go(container);
	}

}

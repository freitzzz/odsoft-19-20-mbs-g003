package pt.isep.cms.classes.client.presenter;

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
import pt.isep.cms.classes.client.event.AddedCMSClassEvent;
import pt.isep.cms.classes.shared.CMSClass;

public class AddCMSClassPresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getAcronym();

		void show();

		void hide();
	}

	private CMSClass cmsClass;
	private final CMSClassesServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public AddCMSClassPresenter(CMSClassesServiceAsync rpcService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();
	}

	public AddCMSClassPresenter(CMSClassesServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
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
					Window.alert("An unexpected error occurred while adding the cmsClass");
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
		
		cmsClass = new CMSClass(
			new Random().nextInt(),
			display.getAcronym().getValue()
		);
			
		// Adding new cmsClass
		rpcService.addCMSClass(cmsClass, new AsyncCallback<CMSClass>() {
			public void onSuccess(CMSClass result) {
				eventBus.fireEvent(new AddedCMSClassEvent());
			}

			public void onFailure(Throwable caught) {
				throw new IllegalStateException(caught);
			}
		});
	}

}

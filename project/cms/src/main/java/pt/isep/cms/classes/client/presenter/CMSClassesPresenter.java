package pt.isep.cms.classes.client.presenter;

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

import pt.isep.cms.classes.client.CMSClassesServiceAsync;
import pt.isep.cms.classes.client.event.AddCMSClassEvent;
import pt.isep.cms.classes.shared.CMSClassDetails;

public class CMSClassesPresenter implements Presenter {

	private List<CMSClassDetails> cmsClassDetails;

	public interface Display {
		HasClickHandlers getAddButton();

		void setData(List<String> data);
		
		Widget asWidget();
	}

	private final CMSClassesServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public CMSClassesPresenter(CMSClassesServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddCMSClassEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

		fetchCMSClassDetails();
	}

	public void setCMSClassDetails(List<CMSClassDetails> cmsClassDetails) {
		this.cmsClassDetails = cmsClassDetails;
	}

	private void fetchCMSClassDetails() {
		rpcService.getCMSClassDetails(new AsyncCallback<ArrayList<CMSClassDetails>>() {
			public void onSuccess(ArrayList<CMSClassDetails> result) {
				cmsClassDetails = result;
				
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(cmsClassDetails.get(i).name());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				Window.alert("Error fetching cmsClass details");
			}
		});
	}
}

package pt.isep.cms.teachers.client.view;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import pt.isep.cms.teachers.client.presenter.TeachersPresenter;

public class TeachersView extends Composite implements TeachersPresenter.Display {
	private final Button addButton;
	private FlexTable contactsTable;
	private final FlexTable contentTable;
	// private final VerticalPanel vPanel ;

	public TeachersView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("18em");

		contentTable = new FlexTable();
		contentTable.setWidth("100%");
		contentTable.getCellFormatter().addStyleName(0, 0, "teachers-ListContainer");
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);

		// vPanel = new VerticalPanel();
		// initWidget(vPanel);

		// Create the menu
		//
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		addButton = new Button("Add");
		hPanel.add(addButton);
		
		// vPanel.add(hPanel);

		contentTable.getCellFormatter().addStyleName(0, 0, "teachers-ListMenu");
		contentTable.setWidget(0, 0, hPanel);

		// Create the teachers list
		//
		contactsTable = new FlexTable();
		contactsTable.setCellSpacing(0);
		contactsTable.setCellPadding(0);
		contactsTable.setWidth("100%");
		contactsTable.addStyleName("teachers-ListContents");
		contactsTable.getColumnFormatter().setWidth(0, "15px");

		// vPanel.add(contactsTable);

		contentTable.setWidget(1, 0, contactsTable);

		contentTableDecorator.add(contentTable);
	}

	public HasClickHandlers getList() {
		return contactsTable;
	}

	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = contactsTable.getCellForEvent(event);

		if (cell != null) {
			// Suppress clicks if the user is actually selecting the
			// check box
			//
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
			}
		}

		return selectedRow;
	}

	public HasClickHandlers getAddButton() {
		return addButton;
	}
	
	public void setData(List<String> data) {
		contactsTable.removeAllRows();

		for (int i = 0; i < data.size(); ++i) {
			contactsTable.setWidget(i, 0, new CheckBox());
			contactsTable.setText(i, 1, data.get(i));
		}
	}

	public Widget asWidget() {
		return this;
	}
}

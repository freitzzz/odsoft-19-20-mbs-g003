/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pt.isep.cms.subjects.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.subjects.client.presenter.AddSubjectPresenter;

/**
 * Dialog Box for adding subjects
 */
public class AddSubjectDialog implements AddSubjectPresenter.Display {
	
	/**
	 * The constants used in this Content Widget.
	 */
	public static interface CwConstants extends Constants {
		
		String cwAddSubjectDialogCaption();
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	// Widgets
	private final TextBox name;
	private final TextBox acronym;
	private final FlexTable detailsTable;
	
	private final Button saveButton;
	private final Button cancelButton;

	private void initDetailsTable() {
		detailsTable.setWidget(0, 0, new Label("Name"));
		detailsTable.setWidget(0, 1, name);
		
		detailsTable.setWidget(1, 0, new Label("Acronym"));
		detailsTable.setWidget(1, 1, acronym);
		
		name.setFocus(true);
	}

	DecoratorPanel contentDetailsDecorator;
	final DialogBox dialogBox;
	
	
	public AddSubjectDialog(ShowcaseConstants constants) {
		// super(constants.cwDialogBoxName(), constants.cwDialogBoxDescription());

		this.constants = constants;
		this.globalConstants = constants;

		// Init the widgets of the dialog
		contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("30em"); // em = size of current font
		// initWidget(contentDetailsDecorator);

		VerticalPanel contentDetailsPanel = new VerticalPanel();
		contentDetailsPanel.setWidth("100%");

		// Create the subjects list
		//
		detailsTable = new FlexTable();
		
		detailsTable.setCellSpacing(0);
		detailsTable.setWidth("100%");
		detailsTable.addStyleName("subjects-ListContainer");
		detailsTable.getColumnFormatter().addStyleName(1, "add-subject-input");
		
		name = new TextBox();
		
		acronym = new TextBox();
		
		initDetailsTable();
		contentDetailsPanel.add(detailsTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);
		contentDetailsPanel.add(menuPanel);
		contentDetailsDecorator.add(contentDetailsPanel);

		dialogBox = new DialogBox();
		dialogBox.ensureDebugId("cwDialogBox");
		
		dialogBox.setText(constants.cwAddSubjectDialogCaption());
			
		dialogBox.add(contentDetailsDecorator);

		dialogBox.setGlassEnabled(true);
		dialogBox.setAnimationEnabled(true);
	}

	public void displayDialog() {
		// Create the dialog box
		// final DialogBox dialogBox = createDialogBox();

		dialogBox.center();
		dialogBox.show();
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		// return null;
		displayDialog();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		// return null;
		dialogBox.hide();
	}

	@Override
	public HasValue<String> getName() {
		return name;
	}

	@Override
	public HasValue<String> getAcronym() {
		return acronym;
	}

}

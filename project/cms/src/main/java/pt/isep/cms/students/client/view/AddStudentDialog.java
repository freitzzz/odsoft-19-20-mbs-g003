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
package pt.isep.cms.students.client.view;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import pt.isep.cms.classes.shared.CMSClassDetails;
import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.students.client.presenter.AddStudentPresenter;

/**
 * Dialog Box for adding students
 */
public class AddStudentDialog implements AddStudentPresenter.Display {

	/**
	 * The constants used in this Content Widget.
	 */
	public static interface CwConstants extends Constants {

		String cwAddStudentDialogCaption();
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	private List<CMSClassDetails> cmsClassesDetails;

	// Widgets
	private final TextBox firstName;
	private final TextBox lastName;
	private final ListBox cmsClasses;
	private final ListBox gender;
	private final DatePicker birthDate;

	private final FlexTable detailsTable;

	private final Button saveButton;
	private final Button cancelButton;

	private void initDetailsTable() {
		detailsTable.setWidget(0, 0, new Label("First name"));
		detailsTable.setWidget(0, 1, firstName);

		detailsTable.setWidget(1, 0, new Label("Last name"));
		detailsTable.setWidget(1, 1, lastName);

		detailsTable.setWidget(2, 0, new Label("Class"));
		detailsTable.setWidget(2, 1, cmsClasses);

		detailsTable.setWidget(3, 0, new Label("Gender"));
		detailsTable.setWidget(3, 1, gender);

		detailsTable.setWidget(4, 0, new Label("Birth date"));
		detailsTable.setWidget(4, 1, birthDate);

		firstName.setFocus(true);
	}

	DecoratorPanel contentDetailsDecorator;
	final DialogBox dialogBox;

	public AddStudentDialog(ShowcaseConstants constants) {
		// super(constants.cwDialogBoxName(), constants.cwDialogBoxDescription());

		this.constants = constants;
		this.globalConstants = constants;

		// Init the widgets of the dialog
		contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("30em"); // em = size of current font
		// initWidget(contentDetailsDecorator);

		VerticalPanel contentDetailsPanel = new VerticalPanel();
		contentDetailsPanel.setWidth("100%");

		// Create the students list
		//
		detailsTable = new FlexTable();

		detailsTable.setCellSpacing(0);
		detailsTable.setWidth("100%");
		detailsTable.addStyleName("students-ListContainer");
		detailsTable.getColumnFormatter().addStyleName(1, "add-student-input");

		firstName = new TextBox();

		lastName = new TextBox();

		cmsClasses = new ListBox();

		gender = new ListBox();

		gender.addItem("Male");

		gender.addItem("Female");

		birthDate = new DatePicker();

		birthDate.setValue(new Date(System.currentTimeMillis()));

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

		dialogBox.setText(constants.cwAddStudentDialogCaption());

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
	public HasValue<String> getFirstName() {
		return firstName;
	}

	@Override
	public HasValue<String> getLastName() {
		return lastName;
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
	public String getGender() {
		return gender.getSelectedItemText();
	}

	@Override
	public HasValue<Date> getBirthDate() {
		return birthDate;
	}

	@Override
	public CMSClassDetails getCMSClass() {

		String selectedItem = cmsClasses.getSelectedItemText();

		if (selectedItem != null) {

			long externalIdentifier = Long.parseLong(selectedItem.split("\\|")[1]);

			for (CMSClassDetails cmsClassDetails : cmsClassesDetails) {

				if (cmsClassDetails.externalIdentifier() == externalIdentifier) {

					return cmsClassDetails;

				}
			}
		}

		return null;
	}

	@Override
	public void setCMSClasses(List<CMSClassDetails> cmsClassesDetails) {

		cmsClasses.clear();

		for (CMSClassDetails cmsClassDetails : cmsClassesDetails) {

			cmsClasses.addItem(cmsClassDetails.name()+"|"+cmsClassDetails.externalIdentifier());

		}

	}

}

package pt.isep.cms.teachers.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import pt.isep.cms.teachers.client.presenter.ViewTeacherDetailsPresenter;

public class TeacherDetailsDialog implements ViewTeacherDetailsPresenter.Display {
  final DialogBox dialogBox;

  private final TextBox id;
  private final TextBox name;
  private final TextBox gender;
  private final TextBox birthDate;
  private final Button cancelButton;

  private final FlexTable detailsTable;

  DecoratorPanel contentDetailsDecorator;

  public TeacherDetailsDialog() {
    dialogBox = new DialogBox();

    contentDetailsDecorator = new DecoratorPanel();
    contentDetailsDecorator.setWidth("30em");

    VerticalPanel contentDetailsPanel = new VerticalPanel();
    contentDetailsPanel.setWidth("100%");

    id = new TextBox();
    id.setReadOnly(true);

    name = new TextBox();
    name.setReadOnly(true);

    gender = new TextBox();
    gender.setReadOnly(true);

    birthDate = new TextBox();
    birthDate.setReadOnly(true);

    detailsTable = new FlexTable();
    detailsTable.setCellSpacing(0);
    detailsTable.setWidth("100%");

    cancelButton = new Button("Cancel");

    detailsTable.setWidget(0, 0, new Label("ID"));
    detailsTable.setWidget(0, 1, id);
    detailsTable.setWidget(1, 0, new Label("Name"));
    detailsTable.setWidget(1, 1, name);
    detailsTable.setWidget(2, 0, new Label("Gender"));
    detailsTable.setWidget(2, 1, gender);
    detailsTable.setWidget(3, 0, new Label("Birth date"));
    detailsTable.setWidget(3, 1, birthDate);
    detailsTable.setWidget(4, 0, cancelButton);

    contentDetailsPanel.add(detailsTable);
    contentDetailsDecorator.add(contentDetailsPanel);
    dialogBox.add(contentDetailsDecorator);
    dialogBox.setGlassEnabled(true);
    dialogBox.setAnimationEnabled(true);
  }

  @Override
  public HasValue<String> getName() {
    // TODO Auto-generated method stub
    return name;
  }

  @Override
  public HasValue<String> getGender() {
    // TODO Auto-generated method stub
    return gender;
  }

  @Override
  public HasValue<String> getBirthDate() {
    // TODO Auto-generated method stub
    return birthDate;
  }

  public void displayDialog() {
    // Create the dialog box
    // final DialogBox dialogBox = createDialogBox();

    dialogBox.center();
    dialogBox.show();
  }

  @Override
  public void show() {
    displayDialog();
  }

  @Override
  public void hide() {
    dialogBox.hide();
  }

  @Override
  public HasClickHandlers getCancelButton() {
    // TODO Auto-generated method stub
    return cancelButton;
  }

  @Override
  public HasValue<String> getId() {
    // TODO Auto-generated method stub
    return id;
  }
  
}
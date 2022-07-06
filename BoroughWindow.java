import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * This class deals with the borough window, which is opened, when the user
 * clicks on a borough button.
 *
 * @author israfeel_ashraf  K21008936
 * @version 22/03/22
 */
public class BoroughWindow {

    private AirbnbFilteredListings filteredListings;
    private ArrayList<AirbnbListing> filteredBoroughListings, hostNameSort, priceSort, reviewsSort;

    //Ways to sort the listings that the user sees.
    private SortingTypes sortByHostName = new SortByHostName(), sortByPrice = new SortByPrice(), sortByReviews = new SortByReviews();

    //A table view to show the data to the user.
    private TableView tableView;
    private TableColumn<AirbnbListing, String> column1, column2, column3, column4;

    /**
     * Constructor to set up the filtered listings.
     */
    public BoroughWindow() {
        filteredListings = new AirbnbFilteredListings();
    }

    /**
     * The most important method of this class, as it creates the new window and sets
     * up the components of the gui.
     *
     * @param button The borough button that was clicked.
     * @return The whole stage/window to show the user.
     */
    public Stage openBoroughWindow(Button button) {

        BorderPane mainBorderPain = new BorderPane();

        BorderPane centralContentPane = new BorderPane();
        mainBorderPain.setCenter(centralContentPane);

        //Create the scene of this window.
        Scene root = new Scene(mainBorderPain, 750, 500);

        Stage boroughStage = new Stage();
        boroughStage.setScene(root);

        HashMap<String, String> boroughsDict = filteredListings.createBoroughDictionary();

        String name = boroughsDict.get(button.getText());

        MenuBar menuBar = makeMenuBar(mainBorderPain);
        centralContentPane.setTop(menuBar);

        BorderPane topPane = (BorderPane) titlePane(name);
        mainBorderPain.setTop(topPane);

        //Create the table view and add it into the centre of the root view.
        BorderPane tableView = createTableView();
        centralContentPane.setCenter(tableView);

        //Add a styling sheet to style this window.
        root.getStylesheets().add("Panel_2_New_Window_StyleSheet.css");

        //Show the title with the borough's name.
        boroughStage.setTitle("Borough: " + name);

        return boroughStage;
    }

    /**
     * Create the title pane of this window
     * @param boroughName The name of the borough to display.
     * @return The title pane to be at the top of the root view.
     */
    private Pane titlePane(String boroughName) {
        BorderPane titlePane = new BorderPane();
        Label labelShowingBorough = new Label("BOROUGH SELECTED: " + boroughName.toUpperCase());

        titlePane.setLeft(labelShowingBorough);
        titlePane.setId("titlePane");

        return titlePane;
    }

    /**
     * A method to make the menubar, which will allow the user to filter based on hostname, price and the number of reviews.
     * @param parent The pane to add the menubar to.
     * @return The menubar.
     */
    private MenuBar makeMenuBar(Pane parent) {

        //Create a menubar and set its id for styling.
        MenuBar menuBar = new MenuBar();
        menuBar.setId("menuBar");

        parent.getChildren().add(menuBar);

        //Create a new menu.
        Menu sortMenu = new Menu("Sort by: ");

        //Add items to the menu
        MenuItem reviewsItem = new MenuItem("Reviews");
        MenuItem priceItem = new MenuItem("Price");
        MenuItem alphabeticalOrderHostNameItem = new MenuItem("Host Name");

        //Add all of these items to the menu.
        sortMenu.getItems().addAll(reviewsItem, priceItem, alphabeticalOrderHostNameItem);

        //Add the menu to the menubar.
        menuBar.getMenus().addAll(sortMenu);


        //Set the items on the openItemCLick method.
        reviewsItem.setOnAction(this::onOpenItemClick);
        priceItem.setOnAction(this::onOpenItemClick);
        alphabeticalOrderHostNameItem.setOnAction(this::onOpenItemClick);

        return menuBar;
    }

    /**
     * A method to deal with updating the table columns when the user wants to sort the data.
     */
    private void removeColumns() {
        tableView.getItems().clear();
        tableView.getColumns().removeAll(column1, column2, column3, column4);
    }

    /**
     * An action event method which deals with the menu items being clicked.
     * @param event The event which occured.
     */
    private void onOpenItemClick(ActionEvent event) {

        //Get the menu item that was clicked.
        MenuItem menuItemClicked = (MenuItem) event.getSource();

        //If the menu item text is reviews, then sort based on reviews
        //and make that column the first one in the table.
        if (menuItemClicked.getText().equals("Reviews")) {

            reviewsSort = sortDependingOnType(sortByReviews);

            //reverse the list to make it go from the highest reviews to lowest.
            Collections.reverse(reviewsSort);

            removeColumns();

            //Add the columns back with the reviews column at the front.
            tableView.getColumns().addAll(column4, column1, column2, column3);

            //Repopulate the data into the table.
            for (int i = 0; i < reviewsSort.size(); i++) {
                tableView.getItems().add(reviewsSort.get(i));
            }
        }

        //The same occurs here, but just for price.
        else if (menuItemClicked.getText().equals("Price")) {

            priceSort = sortDependingOnType(sortByPrice);
            Collections.reverse(priceSort);

            removeColumns();

            //Add the columns back with price at the front.
            tableView.getColumns().addAll(column2, column3, column4, column1);

            //Repopulate the table with data.
            for (int i = 0; i < priceSort.size(); i++) {
                tableView.getItems().add(priceSort.get(i));
            }

        }

        //The same occurs here, but for the host name.
        else if (menuItemClicked.getText().equals("Host Name")) {

            hostNameSort = sortDependingOnType(sortByHostName);

            removeColumns();
            tableView.getColumns().addAll(column1, column2, column3, column4);

            for (int i = 0; i < hostNameSort.size(); i++) {
                tableView.getItems().add(hostNameSort.get(i));
            }
        }
    }

    /**
     * Set the filtered borough listings field as the list passed in.
     * @param listingsToReceive The list to pass in.
     */
    public void loadFilteredBoroughListings(ArrayList<AirbnbListing> listingsToReceive) {
        filteredBoroughListings = listingsToReceive;
    }

    /**
     * A method to sort the listings based on the sort method the user selects.
     * @param sortingType The type of sort the user wants to do (host name etc.)
     * @return The sorted list.
     */
    private ArrayList<AirbnbListing> sortDependingOnType(SortingTypes sortingType) {
        return sortingType.sort(filteredBoroughListings);
    }

    /**
     * A method to create the table to show the data,
     * and deal with the row selection.
     *
     * @return The table created and populated.
     */
    private BorderPane createTableView() {

        tableView = new TableView();

        //If there are no houses available, display a standard label.
        tableView.setPlaceholder(new Label("No houses available in this borough and price range."));

        //Create the different columns populate them from the airbnbListing type according to hostname, price, minimumnights and numberofreviews.
        column1 = new TableColumn<>("Host Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("host_name"));

        column2 = new TableColumn<>("Price (£)");
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));

        column3 = new TableColumn<>("Minimum Number Of Nights");
        column3.setCellValueFactory(new PropertyValueFactory<>("minimumNights"));

        column4 = new TableColumn<>("Number of Reviews");
        column4.setCellValueFactory(new PropertyValueFactory<>("numberOfReviews"));

        //Add these columns to the table.
        tableView.getColumns().addAll(column1, column2, column3, column4);

        //Make the sorting and reordering unavailable to the user as this will be done by the menubar.
        column1.setSortable(false); column2.setSortable(false); column3.setSortable(false); column4.setSortable(false);
        column1.setReorderable(false); column2.setReorderable(false); column3.setReorderable(false); column4.setReorderable(false);

        //Add a sizing policy to the table.
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Do not allow the user to select a cell, as the rows are more important.
        tableView.getSelectionModel().setCellSelectionEnabled(false);


        //Populate the data into the table from the filtered borough arraylist.
        for (int i = 0; i < filteredBoroughListings.size(); i++) {
            tableView.getItems().add(filteredBoroughListings.get(i));
        }

        BorderPane vbox = new BorderPane(tableView);


        //A lambda is used to get the rows of the table when selected.
        tableView.setRowFactory(tv -> {

            //Create a new table row.
            TableRow<AirbnbListing> row = new TableRow<>();

            //On the mouse click of the row, if the row is populated, and a double click occurs, then get the Airbnblistng from the row.
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2) {

                    AirbnbListing clickedRow = row.getItem();

                    //Create the new description window, to show the description of the property.
                    DescriptionWindow description = new DescriptionWindow();

                    //Show the window, giving the window the borough name and the description of the property.
                    Stage windowToShow = description.openDescriptionWindow(clickedRow.getNeighbourhood(), clickedRow.getName());
                    windowToShow.show();

                }
            });
            return row ;
        });

        //return the tableview.
        return vbox;
    }
}


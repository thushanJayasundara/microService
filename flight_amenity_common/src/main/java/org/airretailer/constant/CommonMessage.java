package org.airretailer.constant;

public class CommonMessage {
    private CommonMessage() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static final String CREATE_DATA = "Successfully created";
    public static final String CREATE_UPDATE = "Successfully updated";
    public static final String CREATE_DELETE = "Successfully deleted";
    public static final String RETRIEVE_SUCCESS = "Successfully retrieve";
    public static final String FLIGHT_ALREADY_EXIST = "Flight Already Exist";
    public static final String FLIGHT_DETAIL_NOT_FOUND = "Flight Detail Not Found";
    public static final String ENTER_FLIGHT_NUMBER = "Invalid Flight Number";
    public static final String DATA_NOT_FOUND = "Data Not Found";
    public static final String NO_SEATS_FOUND = "No seats fount for the flight %s in cabin type %s.";
    public static final String UNEXPECTED_ERROR = "An unexpected error occurred. Please try again.";

}

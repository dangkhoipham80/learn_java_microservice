package com.khoipd8.accounts.constants;

public class AccountsConstants {

    private AccountsConstants() {
        // restrict instantiation
    }

    // Account Types
    public static final String SAVINGS = "Savings";

    // Default Address
    public static final String ADDRESS = "123 Main Street, New York";

    // Success Codes
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Account created successfully";

    // Client Errors
    public static final String STATUS_400 = "400";
    public static final String MESSAGE_400 = "Invalid request data";

    public static final String STATUS_401 = "401";
    public static final String MESSAGE_401 = "Unauthorized access";

    public static final String STATUS_403 = "403";
    public static final String MESSAGE_403 = "Access forbidden";

    public static final String STATUS_404 = "404";
    public static final String MESSAGE_404 = "Account not found";

    // Server Errors
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "Internal server error, please try again later";
}

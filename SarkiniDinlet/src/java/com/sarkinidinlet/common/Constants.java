package com.sarkinidinlet.common;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: </p>
 * @author Ozkan SARI
 * @version 1.0
 *
 */
public class Constants {

    // Backing bean keys

    public final static String VISIT_KEY_SCOPE = "sessionScope.";
    public final static String VISIT_KEY = "visit";
    // Model object keys
    public final static String PROJECT_COORDINATOR_SCOPE = "applicationScope.";
    public final static String PROJECT_COORDINATOR_KEY = "projectCoordinator";
    public final static String STATUS_COORDINATOR_SCOPE = "applicationScope.";
    public final static String STATUS_COORDINATOR_KEY = "statusCoordinator";
    public final static String USER_COORDINATOR_SCOPE = "applicationScope.";
    public final static String USER_COORDINATOR_KEY = "userCoordinator";
    // Authorization
    public final static String ORIGINAL_VIEW_SCOPE = "sessionScope";
    public final static String ORIGINAL_VIEW_KEY = "originalTreeId";
    public final static String PROTECTED_DIR = "protected";
    public final static String EDIT_DIR = "protected/edit";
    public final static String LOGIN_VIEW = "/faces/login.jsp";
    // Action outcomes
    public final static String SUCCESS_READONLY_OUTCOME = "success_readonly";
    public final static String SUCCESS_READWRITE_OUTCOME = "success_readwrite";
    public final static String SUCCESS_OUTCOME = "success";
    public final static String CANCEL_READONLY_OUTCOME = "cancel_readonly";
    public final static String CANCEL_READWRITE_OUTCOME = "cancel_readwrite";
    public final static String CANCEL_OUTCOME = "cancel";
    public final static String FAILURE_OUTCOME = "failure";
    public final static String ERROR_OUTCOME = "error";
    // Resource bundle keys
    public final static String BUNDLE_BASENAME = "ptrackResources";
    // For JDBC ResultSet example only
    public final static String RESULT_SET_PROJECT_COORDINATOR_SCOPE =
            "applicationScope.";
    public final static String RESULT_SET_PROJECT_COORDINATOR_KEY =
            "resultSetProjectCoordinator";
}


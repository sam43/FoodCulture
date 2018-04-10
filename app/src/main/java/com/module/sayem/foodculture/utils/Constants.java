package com.module.sayem.foodculture.utils;

public interface Constants {

    class FRAGMENT {
        public static final String HOME = "HomeFragment";
        public static final String ORDER = "OrdersFragment";

        public static final String PRODUCT = "ProductsFragment";
        public static final String PRODUCT_LIST = "ProductListFragment";
        public static final String PRODUCT_TYPE_LIST = "ProductTypeListFragment";

        public static final String CREATE_INVENTORY = "CreateInventoryFragment";
        public static final String INVENTORY_REPORT = "InventoryReportFragment";
        public static final String NEW_SALE = "NewSaleFragment";
        public static final String HOME_MENU = "DashboardFragment";
        public static final String PAYMENT = "PaymentFragment";


        public static final String BANK_LIST = "BankListFragment";
        public static final String CREATE_NOTIFICATION = "CreateNotificationFragment";
        public static final String DASHBOARD = "DashboardFragment";
        public static final String MILL_LIST = "MillListFragment";
        public static final String DRIVER_LIST = "DriverListFragment";
        public static final String USER_LIST = "UserListFragment";
        public static final String SET_RATES ="SetRatesFragment";

        public static final String CLIENT ="Client.ClientFragment";
        public static final String CLIENT_ADD ="Client.ClientAddFragment";
        public static final String CLIENT_LIST ="Client.ClientListFragment";
        public static final String CLIENT_APPROVAL ="Client.ClientApprovalFragment";

        public static final String REPORT ="ReportFragment";
        public static final String REPORT_PART_LEDGER ="ReportParyLedgerFragment";
        public static final String REPORT_PART_LEDGER_ANGLE ="ReportPartLedgerAngleFragment";
        public static final String REPORT_PART_LEDGER_ROD ="ReportPartyLedgerRodFragment";
        public static final String DELIVERY ="DeliveryFragment";
        public static final String CONFIGURATION ="ConfigurationFragment";


        public static final String REPORT_PAYMENT ="ReportPaymentFragment";
        public static final String REPORT_ORDER ="ReportOrderFragment";
        public static final String REPORT_DELIVERY ="ReportDeliveryFragment";
    }

    class ACTION{
        public static final int EDIT = 0;
        public static final int DELETE = 1;

        public static final String EDIT_TITLE = "Update";
        public static final String DELETE_TITLE = "Delete";

        public static final String EDIT_MESSAGE = "Are you sure you want to update?";
        public static final String DELETE_MESSAGE = "Are you sure you want to delete?";
    }

    class STORAGE{
        public static final String LOGGED_IN_USER = "LOGGED_IN_USER";
        public static boolean LOGGED_IN = false;
    }

    class PRODUCT_RATE_TYPE{
        public static final String CORPORATE = "Corporate Rates";
        public static final String REGULAR = "Regular Rates";
        public static final String ONE_TIME = "One Time Rates";

    }
}

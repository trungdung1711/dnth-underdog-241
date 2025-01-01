package com.dnth_underdog_241.online_fashion_shopping.config.paypal.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class PayPalOrderCaptureResponseDto
{
    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("payment_source")
    private PaymentSource paymentSource;

    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits;

    @JsonProperty("payer")
    private Payer payer;

    @JsonProperty("links")
    private List<Link> links;

    @Data
    public static class PaymentSource
    {
        @JsonProperty("paypal")
        private PayPal paypal;

        @Data
        public static class PayPal
        {
            @JsonProperty("name")
            private Name name;

            @JsonProperty("email_address")
            private String emailAddress;

            @JsonProperty("account_id")
            private String accountId;

            @Data
            public static class Name {
                @JsonProperty("given_name")
                private String givenName;

                @JsonProperty("surname")
                private String surname;
            }
        }
    }

    @Data
    public static class PurchaseUnit
    {
        @JsonProperty("reference_id")
        private String referenceId;

        @JsonProperty("shipping")
        private Shipping shipping;

        @JsonProperty("payments")
        private Payments payments;

        @Data
        public static class Shipping
        {
            @JsonProperty("address")
            private Address address;

            @Data
            public static class Address
            {
                @JsonProperty("address_line_1")
                private String addressLine1;

                @JsonProperty("address_line_2")
                private String addressLine2;

                @JsonProperty("admin_area_2")
                private String adminArea2;

                @JsonProperty("admin_area_1")
                private String adminArea1;

                @JsonProperty("postal_code")
                private String postalCode;

                @JsonProperty("country_code")
                private String countryCode;
            }
        }

        @Data
        public static class Payments
        {
            @JsonProperty("captures")
            private List<Capture> captures;

            @Data
            public static class Capture
            {
                @JsonProperty("id")
                private String id;

                @JsonProperty("status")
                private String status;

                @JsonProperty("amount")
                private Amount amount;

                @JsonProperty("seller_protection")
                private SellerProtection sellerProtection;

                @JsonProperty("final_capture")
                private boolean finalCapture;

                @JsonProperty("disbursement_mode")
                private String disbursementMode;

                @JsonProperty("seller_receivable_breakdown")
                private SellerReceivableBreakdown sellerReceivableBreakdown;

                @JsonProperty("create_time")
                private String createTime;

                @JsonProperty("update_time")
                private String updateTime;

                @JsonProperty("links")
                private List<Link> links;

                @Data
                public static class Amount
                {
                    @JsonProperty("currency_code")
                    private String currencyCode;

                    @JsonProperty("value")
                    private String value;
                }

                @Data
                public static class SellerProtection
                {
                    @JsonProperty("status")
                    private String status;

                    @JsonProperty("dispute_categories")
                    private List<String> disputeCategories;
                }

                @Data
                public static class SellerReceivableBreakdown
                {
                    @JsonProperty("gross_amount")
                    private Amount grossAmount;

                    @JsonProperty("paypal_fee")
                    private Amount paypalFee;

                    @JsonProperty("net_amount")
                    private Amount netAmount;
                }
            }
        }
    }

    @Data
    public static class Payer
    {
        @JsonProperty("name")
        private Name name;

        @JsonProperty("email_address")
        private String emailAddress;

        @JsonProperty("payer_id")
        private String payerId;

        @Data
        public static class Name
        {
            @JsonProperty("given_name")
            private String givenName;

            @JsonProperty("surname")
            private String surname;
        }
    }

    @Data
    public static class Link
    {
        @JsonProperty("href")
        private String href;

        @JsonProperty("rel")
        private String rel;

        @JsonProperty("method")
        private String method;
    }
}

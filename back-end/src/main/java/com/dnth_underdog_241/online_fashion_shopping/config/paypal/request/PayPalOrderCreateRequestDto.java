package com.dnth_underdog_241.online_fashion_shopping.config.paypal.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayPalOrderCreateRequestDto
{

    @JsonProperty("intent")
    private String intent;


    @NotNull
    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits;


    @JsonProperty("payment_source")
    private PaymentSource paymentSource;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PaymentSource
    {

        @JsonProperty("paypal")
        private PayPalDetails paypal;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PayPalDetails
    {

        @JsonProperty("experience_context")
        private ExperienceContext experienceContext;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ExperienceContext
    {
        @JsonProperty("payment_method_preference")
        private String paymentMethodPreference;

        @JsonProperty("landing_page")
        private String landingPage;

        @JsonProperty("shipping_preference")
        private String shippingPreference;

        @JsonProperty("user_action")
        private String userAction;

        @JsonProperty("return_url")
        private String returnUrl;

        @JsonProperty("cancel_url")
        private String cancelUrl;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PurchaseUnit
    {
        @JsonProperty("invoice_id")
        private String invoiceId;

        @JsonProperty("amount")
        private Amount amount;

        @JsonProperty("items")
        private List<Item> items;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Amount
    {

        @JsonProperty("currency_code")
        private String currencyCode;

        @JsonProperty("value")
        private String value;

        @JsonProperty("breakdown")
        private AmountBreakdown breakdown;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AmountBreakdown
    {
        @JsonProperty("item_total")
        private Money itemTotal;

        @JsonProperty("shipping")
        private Money shipping;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Money
    {
        @JsonProperty("currency_code")
        private String currencyCode;

        @JsonProperty("value")
        private String value;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Item
    {
        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;

        @JsonProperty("unit_amount")
        private Money unitAmount;

        @JsonProperty("quantity")
        private String quantity;

        @JsonProperty("category")
        private String category;

        @JsonProperty("sku")
        private String sku;

        @JsonProperty("image_url")
        private String imageUrl;
    }
}
package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class PaymentView {

  @ApiModelProperty(required = true)
  private String id;

  @ApiModelProperty(required = true)
  private Integer version;

  @ApiModelProperty(value = "A reference to the customer this payment belongs to.",required = false)
  private Reference customer;

  @ApiModelProperty(value = "This ID can be used as an additional identifier for external systems like the systems involved in order or receivables management.",
          required = false)
  private String externalId;

  @ApiModelProperty(value = "The identifier that is used by the interface that manages the payment (usually the PSP). Cannot be changed once it has been set. " +
          "The combination of this ID and the PaymentMethodInfo paymentInterface must be unique.",
          required = false)
  private String interfaceId;

  @ApiModelProperty(value = "How much money this payment intends to receive from the customer. " +
          "The value usually matches the cart or order gross total.", required = true)
  private Money amountPlanned;

  @ApiModelProperty(value = "The amount of money that has been authorized (i.e. reliably reserved, but not transferred).", required = false)
  private Money amountAuthorized;

  @ApiModelProperty(value = "Until when the authentication is valid. Can only be set when amountAuthorized is set, too.", required = false)
  private String authorizedUntil;

  @ApiModelProperty(value = "The amount of money that has been received from the customer. " +
          "This value is updated during the financial process.",
          required = false)
  private Money amountPaid;

  @ApiModelProperty(value = "The amount of money that has been refunded to the customer.",
          required = false)
  private Money amountRefunded;

  @ApiModelProperty(required = true)
  private PaymentMethodInfo paymentMethodInfo;

  @ApiModelProperty(required = true)
  private PaymentStatus paymentStatus;

  @ApiModelProperty(value = "Array of TransactionModel A list of financial transactions of different TransactionTypes with different TransactionStates.", required = true)
  private List<TransactionModel> transactions;

  @ApiModelProperty(required = true)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  @ApiModelProperty(required = true)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;
}
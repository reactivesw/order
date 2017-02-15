package io.reactivesw.order.application.model.mapper;

import io.reactivesw.model.Reference;
import io.reactivesw.order.application.model.ShippingInfoView;
import io.reactivesw.order.domain.model.value.ShippingInfoValue;
import io.reactivesw.order.infrastructure.enums.ReferenceTypes;

/**
 * Created by Davis on 17/2/6.
 */
public final class ShippingInfoMapper {
  /**
   * Instantiates a new Shipping info mapper.
   */
  private ShippingInfoMapper() {
  }

  /**
   * Model to entity shipping info value.
   *
   * @param model the model
   * @return the shipping info value
   */
  public static ShippingInfoValue modelToEntity(ShippingInfoView model) {
    ShippingInfoValue entity = new ShippingInfoValue();

    if (model != null) {
      entity.setShippingMethodName(model.getShippingMethodName());
      entity.setPrice(MoneyMapper.modelToEntity(model.getPrice()));
      entity.setShippingRate(ShippingRateMapper.modelToEntity(model.getShippingRate()));
      entity.setTaxedPrice(TaxedItemPriceMapper.modelToEntity(model.getTaxedPrice()));
      entity.setTaxRate(TaxRateMapper.modelToEntity(model.getTaxRate()));
      if (model.getTaxCategory() != null) {
        entity.setTaxCategory(model.getTaxCategory().getId());
      }
      if (model.getShippingMethod() != null) {
        entity.setShippingMethod(model.getShippingMethod().getId());
      }
      entity.setDeliveries(null);
      entity.setDiscountedPrice(null);
    }

    return entity;
  }

  /**
   * Entity to model shipping info.
   *
   * @param entity the shipping info
   * @return the shipping info
   */
  public static ShippingInfoView entityToModel(ShippingInfoValue entity) {
    ShippingInfoView model = new ShippingInfoView();

    if (entity != null) {
      model.setShippingMethod(new Reference(
          ReferenceTypes.SHIPPING_METHOD.toString(), entity.getShippingMethod()));
      model.setShippingMethodName(entity.getShippingMethodName());
      model.setPrice(MoneyMapper.entityToModel(entity.getPrice()));
      model.setShippingRate(ShippingRateMapper.entityToModel(entity.getShippingRate()));
      model.setTaxedPrice(TaxedItemPriceMapper.entityToModel(entity.getTaxedPrice()));
      model.setTaxRate(TaxRateMapper.entityToModel(entity.getTaxRate()));
      model.setTaxCategory(new Reference(
          ReferenceTypes.TAXCATEGORY.toString(), entity.getTaxCategory()));
    }

    return model;
  }
}

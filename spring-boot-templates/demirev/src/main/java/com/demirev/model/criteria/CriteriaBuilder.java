package com.demirev.model.criteria;


import com.demirev.model.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class CriteriaBuilder {

    private CriteriaBuilder() {
    }

    public static Specification<BusinessUser> filterBusinessUsers(BusinessUserCriteria businessUserCriteria) {
        return (root, criteriaQuery, builder) -> {
            Predicate p = builder.conjunction();
            if (businessUserCriteria.getId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(BusinessUser_.id).as(String.class), "%" + businessUserCriteria.getId() + "%"));
            }

            if (businessUserCriteria.getEmail() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(BusinessUser_.email), "%" + businessUserCriteria.getEmail() + "%"));
            }

            if (businessUserCriteria.getFirstName() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(BusinessUser_.firstName), "%" + businessUserCriteria.getFirstName() + "%"));
            }

            if (businessUserCriteria.getLastName() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(BusinessUser_.lastName), "%" + businessUserCriteria.getLastName() + "%"));
            }

            if (businessUserCriteria.getPassword() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(BusinessUser_.password), "%" + businessUserCriteria.getPassword() + "%"));
            }

            if (businessUserCriteria.getPhoneNumber() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(BusinessUser_.phoneNumber), "%" + businessUserCriteria.getPhoneNumber() + "%"));
            }

            if (businessUserCriteria.getRegistrationDate() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(BusinessUser_.registrationDate).as(String.class), "%" + businessUserCriteria.getRegistrationDate() + "%"));
            }

            if (businessUserCriteria.getRole() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(BusinessUser_.role), "%" + businessUserCriteria.getRole() + "%"));
            }

            if (businessUserCriteria.getFacilityId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(BusinessUser_.facility).get(Facility_.id).as(String.class), "%" + businessUserCriteria.getFacilityId() + "%"));
            }

            if (businessUserCriteria.getCountryId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(BusinessUser_.country).get(Country_.id).as(String.class), "%" + businessUserCriteria.getCountryId() + "%"));
            }

            return p;
        };
    }

    public static Specification<EndUser> filterEndUsers(EndUserCriteria endUserCriteria) {
        return (root, criteriaQuery, builder) -> {
            Predicate p = builder.conjunction();
            if (endUserCriteria.getId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(EndUser_.id).as(String.class), "%" + endUserCriteria.getId() + "%"));
            }

            if (endUserCriteria.getEmail() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(EndUser_.email), "%" + endUserCriteria.getEmail() + "%"));
            }

            if (endUserCriteria.getFirstName() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(EndUser_.firstName), "%" + endUserCriteria.getFirstName() + "%"));
            }

            if (endUserCriteria.getLastName() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(EndUser_.lastName), "%" + endUserCriteria.getLastName() + "%"));
            }

            if (endUserCriteria.getPassword() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(EndUser_.password), "%" + endUserCriteria.getPassword() + "%"));
            }

            if (endUserCriteria.getPhoneNumber() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(EndUser_.phoneNumber), "%" + endUserCriteria.getPhoneNumber() + "%"));
            }

            if (endUserCriteria.getRegistrationDate() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(EndUser_.registrationDate).as(String.class), "%" + endUserCriteria.getRegistrationDate() + "%"));
            }

            if (endUserCriteria.getRole() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(EndUser_.role), "%" + endUserCriteria.getRole() + "%"));
            }

            if (endUserCriteria.getCountryId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(EndUser_.country).get(Country_.id).as(String.class), "%" + endUserCriteria.getCountryId() + "%"));
            }

            return p;
        };
    }

    public static Specification<City> filterCities(CityCriteria cityCriteria) {
        return (root, criteriaQuery, builder) -> {
            Predicate p = builder.conjunction();
            if (cityCriteria.getId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(City_.id).as(String.class), "%" + cityCriteria.getId() + "%"));
            }

            if (cityCriteria.getName() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(City_.name), "%" + cityCriteria.getName() + "%"));
            }

            if (cityCriteria.getCountryId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(City_.country).get(Country_.id).as(String.class), "%" + cityCriteria.getCountryId() + "%"));
            }

            return p;
        };
    }

    public static Specification<Country> filterCountries(CountryCriteria countryCriteria) {
        return (root, criteriaQuery, builder) -> {
            Predicate p = builder.conjunction();
            if (countryCriteria.getId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Country_.id).as(String.class), "%" + countryCriteria.getId() + "%"));
            }

            if (countryCriteria.getName() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Country_.name), "%" + countryCriteria.getName() + "%"));
            }

            if (countryCriteria.getCodeIso() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Country_.codeIso), "%" + countryCriteria.getCodeIso() + "%"));
            }

            if (countryCriteria.getCodeIso3() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Country_.codeIso3), "%" + countryCriteria.getCodeIso3() + "%"));
            }

            return p;
        };
    }

    public static Specification<Facility> filterFacilities(FacilityCriteria facilityCriteria) {
        return (root, criteriaQuery, builder) -> {
            Predicate p = builder.conjunction();
            if (facilityCriteria.getId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Facility_.id).as(String.class), "%" + facilityCriteria.getId() + "%"));
            }

            if (facilityCriteria.getName() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Facility_.name), "%" + facilityCriteria.getName() + "%"));
            }

            if (facilityCriteria.getLatitude() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Facility_.latitude).as(String.class), "%" + facilityCriteria.getLatitude() + "%"));
            }

            if (facilityCriteria.getLongitude() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Facility_.longitude).as(String.class), "%" + facilityCriteria.getLongitude() + "%"));
            }

            if (facilityCriteria.getParentFacilityId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Facility_.parentFacility).get(Facility_.id).as(String.class), "%" + facilityCriteria.getParentFacilityId() + "%"));
            }

            if (facilityCriteria.getCountryId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Facility_.country).get(Country_.id).as(String.class), "%" + facilityCriteria.getCountryId() + "%"));
            }

            if (facilityCriteria.getCityId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Facility_.city).get(City_.id).as(String.class), "%" + facilityCriteria.getCityId() + "%"));
            }

            return p;
        };
    }

    public static Specification<ItemCategory> filterItemCategories(ItemCategoryCriteria itemCategoryCriteria) {
        return (root, criteriaQuery, builder) -> {
            Predicate p = builder.conjunction();
            if (itemCategoryCriteria.getId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(ItemCategory_.id).as(String.class), "%" + itemCategoryCriteria.getId() + "%"));
            }

            if (itemCategoryCriteria.getName() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(ItemCategory_.name), "%" + itemCategoryCriteria.getName() + "%"));
            }

            if (itemCategoryCriteria.getParentCategoryId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(ItemCategory_.parentCategory).get(ItemCategory_.id).as(String.class), "%" + itemCategoryCriteria.getParentCategoryId() + "%"));
            }

            return p;
        };
    }

    public static Specification<Item> filterItems(ItemCriteria itemCriteria) {
        return (root, criteriaQuery, builder) -> {
            Predicate p = builder.conjunction();
            if (itemCriteria.getId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Item_.id).as(String.class), "%" + itemCriteria.getId() + "%"));
            }

            if (itemCriteria.getName() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Item_.name), "%" + itemCriteria.getName() + "%"));
            }

            if (itemCriteria.getDescription() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Item_.description), "%" + itemCriteria.getDescription() + "%"));
            }

            if (itemCriteria.getPicture() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Item_.picture), "%" + itemCriteria.getPicture() + "%"));
            }

            if (itemCriteria.getBrand() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Item_.brand), "%" + itemCriteria.getBrand() + "%"));
            }

            if (itemCriteria.getInternalIdentifier() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Item_.internalIdentifier), "%" + itemCriteria.getInternalIdentifier() + "%"));
            }

            if (itemCriteria.getFacilityId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Item_.facility).get(Facility_.id).as(String.class), "%" + itemCriteria.getFacilityId() + "%"));
            }

            if (itemCriteria.getSubCategoryId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Item_.subCategory).get(ItemCategory_.id).as(String.class), "%" + itemCriteria.getSubCategoryId() + "%"));
            }

            if (itemCriteria.getParentCategoryId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Item_.parentCategory).get(ItemCategory_.id).as(String.class), "%" + itemCriteria.getParentCategoryId() + "%"));
            }

            return p;
        };
    }

    public static Specification<WarrantyCondition> filterWarrantyConditions(WarrantyConditionCriteria warrantyConditionCriteria) {
        return (root, criteriaQuery, builder) -> {
            Predicate p = builder.conjunction();
            if (warrantyConditionCriteria.getId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(WarrantyCondition_.id).as(String.class), "%" + warrantyConditionCriteria.getId() + "%"));
            }

            if (warrantyConditionCriteria.getName() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(WarrantyCondition_.name), "%" + warrantyConditionCriteria.getName() + "%"));
            }

            if (warrantyConditionCriteria.getDescription() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(WarrantyCondition_.description), "%" + warrantyConditionCriteria.getDescription() + "%"));
            }

            if (warrantyConditionCriteria.getText() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(WarrantyCondition_.text), "%" + warrantyConditionCriteria.getText() + "%"));
            }

            if (warrantyConditionCriteria.getFacilityId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(WarrantyCondition_.facility).get(Facility_.id).as(String.class), "%" + warrantyConditionCriteria.getFacilityId() + "%"));
            }

            return p;
        };
    }

    public static Specification<Warranty> filterWarranties(WarrantyCriteria warrantyCriteria) {
        return (root, criteriaQuery, builder) -> {
            Predicate p = builder.conjunction();
            if (warrantyCriteria.getId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Warranty_.id).as(String.class), "%" + warrantyCriteria.getId() + "%"));
            }

            if (warrantyCriteria.getDurationMonths() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Warranty_.durationMonths).as(String.class), "%" + warrantyCriteria.getDurationMonths() + "%"));
            }

            if (warrantyCriteria.getExpirationDate() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Warranty_.expirationDate).as(String.class), "%" + warrantyCriteria.getExpirationDate() + "%"));
            }

            if (warrantyCriteria.getIssueDate() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Warranty_.issueDate).as(String.class), "%" + warrantyCriteria.getIssueDate() + "%"));
            }

            if (warrantyCriteria.getItemSerialNumber() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Warranty_.itemSerialNumber), "%" + warrantyCriteria.getItemSerialNumber() + "%"));
            }
            if (warrantyCriteria.getEndUserId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Warranty_.endUserId).as(String.class), "%" + warrantyCriteria.getEndUserId() + "%"));
            }

            if (warrantyCriteria.getFacilityId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Warranty_.facility).get(Facility_.id).as(String.class), "%" + warrantyCriteria.getFacilityId() + "%"));
            }

            if (warrantyCriteria.getItemId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Warranty_.item).get(Item_.id).as(String.class), "%" + warrantyCriteria.getItemId() + "%"));
            }

            if (warrantyCriteria.getWarrantyConditionId() != null) {
                p.getExpressions()
                        .add(builder.like(root.get(Warranty_.warrantyCondition).get(WarrantyCondition_.id).as(String.class), "%" + warrantyCriteria.getWarrantyConditionId() + "%"));
            }

            return p;
        };
    }

}
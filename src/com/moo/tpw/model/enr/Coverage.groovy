package com.moo.tpw.model.enr

import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.LocalDate

class Coverage {
    String planId
    String secondaryPlanId
    Long coverageId
    Long sequence
    MutualProduct productType
    CompassProductType compassProductType //Used for MemberDetails EA
    CompassProductType secondaryCompassProductType
    PersonType relationship
    CompassAppliedToType compassAppliedToType //Used for MemberDetails EA
    boolean priorCoverage
    BigDecimal priorVolume
    BigDecimal priorSecondaryVolume

    //ContribType contribType
    //ContribType dependentContribType

    String description
    String secondaryDescription
    //ParticipationTier tier
    BigDecimal selectedVolume
    BigDecimal approvedVolume
    BigDecimal excessVolume
    // secondary volumes apply to products that have more than one volume, like AD&D
    BigDecimal secondarySelectedVolume
    BigDecimal secondaryApprovedVolume
    BigDecimal secondaryExcessVolume
    BigDecimal selectedSalaryMultiplier
    BigDecimal secondarySelectedSalaryMultiplier
    BigDecimal premium
    BigDecimal secondaryPremium
    boolean lateEntrant
    LocalDate cobraTerminationDate
    boolean cobraInformationProvided
    Boolean grandfatheredPlan
    LocalDate coverageEffectiveDate
    LocalDate secondaryCoverageEffectiveDate
    LocalDate terminationDate
    LocalDate secondaryTerminationDate
    Boolean grandfatheredSecondaryPlan
    Boolean reducedCoverage
    Boolean smokerRated
    BigDecimal guaranteedIssueAmount
    BigDecimal secondaryGuaranteedIssueAmount
    Boolean section125Plan
    Boolean portable

    //List<ParticipationTier> availableParticipationTierList = []
    //List<IncrementValue> availableIncrementList = []
    //List<IncrementValue> secondaryAvailableIncrementList = []
    BigDecimal salaryMultiplier
    BigDecimal secondarySalaryMultiplier
    //EmployeeBenefitType benefitType
    //ADDBenefitType addBenefitType
    LocalDate enrollmentEffectiveDate
    //ContractTerminationType contractTermination
    //ContractTerminationType secondaryContractTermination
    Boolean hasActiveEnrollment = false
    Boolean isEditable
    Boolean isRemovable
    Boolean lifeEventAvailable

    Boolean coverageStop
    Boolean changeStop

    //TODO: Testing only. Remove for PRD
    Boolean ctsError
    String ctsErrorText
    String secondaryCtsErrorText

    //ChangeEvent changeEvent = new ChangeEvent()
    //ChangeEvent secondaryChangeEvent = new ChangeEvent()
    Integer childLimitingAge
    Integer fullTimeStudentAge
    //ChildUnit childUnit

    @JsonProperty("eoiApplicable")
    Boolean isEoiApplicable() {
        return productType ? productType.eoiApplicable : null
    }

    boolean isContrib() {
        return contribType == ContribType.Contrib || (dependentContribType == ContribType.Contrib && relationship != PersonType.EMPLOYEE)
    }
}

package com.moo.tpw.model.enr

enum MutualProduct {

    Life(["LIFE"], "Basic Life", [CompassProductType.Life], false),
    LifeADD(["LIFE/AD&D"], "Basic Life/Basic AD&D", [CompassProductType.Life, CompassProductType.ADD], false),
    VTL(["VoluntaryLife", "VTL"], "Voluntary Term Life", [CompassProductType.VTL], false),
    VTLADD(["VoluntaryLife/AD&D","VTL/AD&D"], "Voluntary Term Life/Voluntary AD&D", [CompassProductType.VTL, CompassProductType.VADD], false),
    PaidUpLife(["PAID UP LIFE"], "Paid-Up Life", [CompassProductType.PaidUpLife], false),
    LTD(["LTD"], "Long-Term Disability", [CompassProductType.LTD], false),
    VoluntaryLTD(["VOLUNTARY LTD"], "Voluntary Long-Term Disability", [CompassProductType.VLTD], false),
    STD(["STD"], "Short-Term Disability", [CompassProductType.STD], false),
    VoluntarySTD(["VOLUNTARY STD"], "Voluntary Short-Term Disability", [CompassProductType.VSTD], false),
    Dental(["DENTAL"], "Dental", [CompassProductType.Dental], false),
    VoluntaryDental(["VOLUNTARY DENTAL"], "Voluntary Dental", [CompassProductType.VDental], false),
    CI(["CI"], "Critical Illness", [CompassProductType.CI], false),
    VCI(["VCI"], "Voluntary Critical Illness", [CompassProductType.VCI], false),
    ACC(["ACC"], "Accident", [CompassProductType.ACC], false),
    VACC(["VACC"], "Voluntary Accident", [CompassProductType.VACC], false),
    DentaBenefits(["DENTABENEFITS"], "DentaBenefits", null, true),
    VoluntaryDentaBenefits(["VOLUNTARY DENTABENEFITS"], "Voluntary DentaBenefits", null, true),
    mpower360(["MPOWER360"], "mpower360", null, true),
    EBGateway(["EB GATEWAY"], "EB Gateway", null, true)

    private List<String> shortDescriptions
    private String longDescription
    private boolean excluded
    private List<CompassProductType> compassProductTypes

    private MutualProduct(List<String> shortDescriptions, String longDescription, List<CompassProductType> compassProducts, boolean excluded) {
        this.shortDescriptions = shortDescriptions
        this.longDescription = longDescription
        this.excluded = excluded
        this.compassProductTypes = compassProducts
    }

    static MutualProduct fromShortDescription(String shortDescription) {
        def product = values().find{ product ->
            shortDescription?.toUpperCase() in product.shortDescriptions
        }
        if(!product) {
            throw new IllegalArgumentException("Could not find product with short description [${shortDescription}].")
        }
        return product
    }

    boolean isEoiApplicable() {
        return this in [STD,VoluntarySTD,LTD,VoluntaryLTD,CI,VCI,Life,VTL,LifeADD,VTLADD]
    }

    boolean isLife() {
        return this in [Life, LifeADD, PaidUpLife, VTL, VTLADD]
    }

    boolean isVTL() {
        return this in [VTL, VTLADD]
    }

    boolean isDisability() {
        return this in [STD, VoluntarySTD, LTD, VoluntaryLTD]
    }

    boolean isDental() {
        return this in [DentaBenefits, VoluntaryDentaBenefits, Dental, VoluntaryDental]
    }

    boolean isCriticalIllness() {
        return this in [CI, VCI]
    }

    boolean isAccident() {
        return this in [ACC, VACC]
    }

    String getLongDescription() {
        return this.longDescription
    }

    String getPrimaryDescription(){
        return this.longDescription.tokenize("/").get(0)
    }

    String getSecondaryDescription(){
        String desc = ""
        List<String> descriptions = this.longDescription.tokenize("/")
        if(descriptions.size() == 2){
            desc = descriptions.get(1)
        }
        return desc
    }

    boolean isExcluded() {
        return excluded
    }

    List<CompassProductType> getAssociatedCompassProducts() {
        return compassProductTypes
    }
}
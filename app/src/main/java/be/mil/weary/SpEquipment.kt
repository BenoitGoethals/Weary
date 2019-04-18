package be.mil.weary

data class SpEquipment(val eqNone:Boolean, val  eqHoist:Boolean, val eqExtraction:Boolean, val eqVentilator:Boolean, val eqOther:Boolean) {
    override fun toString(): String {
        return "SpEquipment(eqNone=$eqNone, eqHoist=$eqHoist, eqExtraction=$eqExtraction, eqVentilator=$eqVentilator, eqOther=$eqOther)"
    }
}
package be.mil.weary

data class PatientsType(val natoMilitary:Int, val natoCivilian:Int, val nonNatoMilitary:Int,val nonNatoCivilian:Int, val detainee:Int, val embeddedInterpreter:Int, val cIVCAScausedbyNATO:Int, val child:Int) {
    override fun toString(): String {
        return "PatientsType(natoMilitary=$natoMilitary, natoCivilian=$natoCivilian, nonNatoMilitary=$nonNatoMilitary, nonNatoCivilian=$nonNatoCivilian, detainee=$detainee, embeddedInterpreter=$embeddedInterpreter, cIVCAScausedbyNATO=$cIVCAScausedbyNATO, child=$child)"
    }
}
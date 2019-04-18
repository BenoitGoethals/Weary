package be.mil.weary

data class MedivacReport(val id:String,val loc:LocationGps , val patients:Patients ,val spEquipment:SpEquipment ,val patientsType: PatientsType , val security:Boolean, val securityIED:Boolean, val zoneMarking:ZoneMarking,val nationality:Nationality, val comment:String){

    override fun toString(): String {
        return "MedivacReport(id='$id', loc=$loc, patients=$patients, spEquipment=$spEquipment, patientsType=$patientsType, security=$security, securityIED=$securityIED, zoneMarking=$zoneMarking, nationality=$nationality, comment='$comment')"
    }
}


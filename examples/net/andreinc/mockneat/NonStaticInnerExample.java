package net.andreinc.mockneat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class NonStaticInnerExample {
    public static void main(String[] args) {

        MockNeat m = MockNeat.threadLocal();
        MockNeat mockNeat = MockNeat.threadLocal();

        Function<Patient, Patient> dobPopulator =
                (patient) -> {
                    Patient.DOB dob = m.filler(() -> patient.new DOB())
                                       .setter(Patient.DOB::setPremature, mockNeat.bools())
                                       .setter(Patient.DOB::setEstimated, mockNeat.bools())
                                       .val();
                    patient.setdOB(dob);
                    return patient;
                };


        List<Patient> patientList = mockNeat.filler(() -> new Patient())
                                   .setter(Patient::setFirstName, m.names().first())
                                   .setter(Patient::setLastName, m.names().last())
                                   .map(dobPopulator)
                                   .list(() -> new ArrayList<>(), 10)
                                   .val();

        System.out.println(patientList);
    }
}

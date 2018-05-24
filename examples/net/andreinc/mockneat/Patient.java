package net.andreinc.mockneat;

public class Patient {

    public String lastName;
    public String firstName;
    public DOB dOB;

    public class DOB {
        public String dobDate;
        public Boolean estimated;
        public Boolean premature;
        public String duration;
        public String measure;

        public DOB() {
        }

        public String getDobDate() {
            return dobDate;
        }

        public void setDobDate(String dobDate) {
            this.dobDate = dobDate;
        }

        public Boolean getEstimated() {
            return estimated;
        }

        public void setEstimated(Boolean estimated) {
            this.estimated = estimated;
        }

        public Boolean getPremature() {
            return premature;
        }

        public void setPremature(Boolean premature) {
            this.premature = premature;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        @Override
        public String toString() {
            return "DOB{" +
                    "dobDate='" + dobDate + '\'' +
                    ", estimated=" + estimated +
                    ", premature=" + premature +
                    ", duration='" + duration + '\'' +
                    ", measure='" + measure + '\'' +
                    '}';
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public DOB getdOB() {
        return dOB;
    }

    public void setdOB(DOB dOB) {
        this.dOB = dOB;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dOB=" + dOB +
                '}';
    }
}
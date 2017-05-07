package net.andreinc.mockneat;

/**
 * Created by andreinicolinciobanu on 02/03/2017.
 */
public class EmployeePC {
    private String uuid;
    private String username;
    private String operatingSystem;
    private String ipAddress;
    private String macAddress;

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    @Override
    public String toString() {
        return "EmployeePC{" +
                "uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", macAddress='" + macAddress + '\'' +
                '}';
    }
}

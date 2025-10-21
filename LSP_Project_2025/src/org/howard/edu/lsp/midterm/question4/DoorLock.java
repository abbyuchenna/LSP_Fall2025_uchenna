package org.howard.edu.lsp.midterm.question4;

/**
 * Represents a smart door lock device.
 * Extends Device and implements Networked and BatteryPowered.
 */
public class DoorLock extends Device implements Networked, BatteryPowered {
    private int batteryPercent;

    public DoorLock(String id, String location, int initialBattery) {
        super(id, location);
        setBatteryPercent(initialBattery);
    }

    // Networked interface methods
    @Override
    public void connect() {
        setConnected(true);
    }

    @Override
    public void disconnect() {
        setConnected(false);
    }

    @Override
    public boolean isConnected() {
        return super.isConnected();
    }

    // BatteryPowered interface methods
    @Override
    public int getBatteryPercent() {
        return batteryPercent;
    }

    @Override
    public void setBatteryPercent(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("battery 0..100");
        }
        this.batteryPercent = percent;
    }

    // Device status override
    @Override
    public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "DoorLock[id=" + getId() + ", loc=" + getLocation() +
               ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
    }
}
package org.howard.edu.lsp.midterm.question4;

/**
 * Abstract base class representing a generic device in the smart campus system.
 * Provides shared properties and methods for all devices (e.g., DoorLock, Thermostat, Camera).
 */
public abstract class Device {
    private String id;
    private String location;
    private long lastHeartbeatEpochSeconds;
    private boolean connected;

    /**
     * Constructs a Device with a given id and location.
     * @param id unique identifier for the device
     * @param location physical or logical location of the device
     * @throws IllegalArgumentException if id or location is null or empty
     */
    public Device(String id, String location) {
        if (id == null || id.isEmpty() || location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Invalid id or location");
        }
        this.id = id;
        this.location = location;
        this.lastHeartbeatEpochSeconds = 0;
        this.connected = false;
    }

    /** @return device ID */
    public String getId() {
        return id;
    }

    /** @return device location */
    public String getLocation() {
        return location;
    }

    /** @return last heartbeat timestamp in seconds */
    public long getLastHeartbeatEpochSeconds() {
        return lastHeartbeatEpochSeconds;
    }

    /** @return whether the device is connected to the network */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Sets the connection status.
     * Protected so that subclasses and interfaces like Networked can modify it.
     */
    protected void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Updates the device’s heartbeat timestamp to the current system time (in seconds).
     */
    public void heartbeat() {
        this.lastHeartbeatEpochSeconds = System.currentTimeMillis() / 1000;
    }

    /**
     * Abstract method that must be implemented by subclasses to return device status.
     * @return formatted status string describing device state.
     */
    public abstract String getStatus();
}
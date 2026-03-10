package seedu.address.model.room;

import seedu.address.model.tag.Tag;

import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Room in the system.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Room {

    // Identity fields
    private final RoomName name;

    // Data fields
    private final Location location;
    private final Status status;
    private final Set<Tag> tagSet;

    /**
     * Every field must be present and not null.
     */
    public Room(RoomName name, Location location, Status status) {
        requireAllNonNull(name, location, status);
        this.name = name;
        this.location = location;
        this.status = status;
        this.tagSet = new Set<Tag>();
    }

    /**
     * Placeholder or checking if room in list based on only roomname
     * @param name
     */
    public Room(RoomName name) {
        requireAllNonNull(name);
        this.name = name;
    }

    public RoomName getName() { return name; }
    public Location getLocation() { return location; }
    public Status getStatus() { return status; }

    /**
     * Returns true if both rooms have the same name.
     * This defines a weaker notion of equality between two rooms (used for duplicate checking).
     */
    public boolean isSameRoom(Room otherRoom) {
        if (otherRoom == this) {
            return true;
        }
        return otherRoom != null && otherRoom.getName().equals(getName());
    }

    public void addTags(Set<Tag> tags) {
        this.tagSet.addAll(tags);
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Location: %s | Status: %s", name, location, status);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Room)) return false;
        Room otherRoom = (Room) other;
        return name.equals(otherRoom.name) && location.equals(otherRoom.location);
    }
}
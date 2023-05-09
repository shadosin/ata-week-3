package com.kenzie.ata.attendance.participants;

/**
 * A POJO representing an ATA participant.
 */
public class Participant {

    private long id;
    private String alias;
    private String name;

    /**
     * Instantiates a new Participant.
     *
     * @param id    the id
     * @param name  the name
     * @param alias the alias
     */
    public Participant(long id, String name, String alias) {
        this.id = id;
        this.alias = alias;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Participant ID: %d, Alias: %s, Name: %s", this.id, this.alias, this.name);
    }
}

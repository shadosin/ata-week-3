package com.kenzie.ata.attendance.management;

import com.kenzie.ata.attendance.participants.ParticipantManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WeeklyRosterManagerTest {

    @Test
    public void addParticipantsToSectionTest() {
        WeeklyRosterManager sectionManager = new WeeklyRosterManager(LocalDate.of(2019, 8, 13),
                new ParticipantManager());
        sectionManager.addParticipantsToSection(SectionDay.TUESDAY, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17," +
                "18,19,20,21,22,23,24,25,26,27,28,29");

        assertEquals(sectionManager.getSectionAttendees(SectionDay.TUESDAY).size(), 29);
    }

    @Test
    public void addParticipantsToSectionTwiceTest() {
        WeeklyRosterManager sectionManager = new WeeklyRosterManager(LocalDate.of(2019, 8, 13),
                new ParticipantManager());
        sectionManager.addParticipantsToSection(SectionDay.TUESDAY, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17");

        sectionManager.addParticipantsToSection(SectionDay.TUESDAY, "18,19,20,21,22,23,24,25,26,27,28,29");

        assertEquals(sectionManager.getSectionAttendees(SectionDay.TUESDAY).size(), 29);
    }

    @Test
    public void scheduleAbsenceTest() {
        WeeklyRosterManager sectionManager = new WeeklyRosterManager(LocalDate.of(2019, 8, 13),
                new ParticipantManager());

        sectionManager.addParticipantsToSection(SectionDay.TUESDAY, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17," +
                "18,19,20,21,22,23,24,25,26,27,28,29");

        boolean absence = sectionManager.scheduleAbsence(SectionDay.TUESDAY, 1);

        assertTrue(absence);
        assertEquals(sectionManager.getSectionAttendees(SectionDay.TUESDAY).size(), 28);
    }

    @Test
    public void scheduleAbsenceFailsTest() {
        WeeklyRosterManager sectionManager = new WeeklyRosterManager(LocalDate.of(2019, 8, 13),
                new ParticipantManager());

        sectionManager.addParticipantsToSection(SectionDay.TUESDAY, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17," +
                "18,19,20,21,22,23,24,25,26,27,28,29");

        sectionManager.scheduleAbsence(SectionDay.TUESDAY, 1);
        boolean absence = sectionManager.scheduleAbsence(SectionDay.TUESDAY, 1);

        assertFalse(absence);
        assertEquals(sectionManager.getSectionAttendees(SectionDay.TUESDAY).size(), 28);
    }

    @Test
    public void scheduleSwapTest() {
        WeeklyRosterManager sectionManager = new WeeklyRosterManager(LocalDate.of(2019, 8, 13),
                new ParticipantManager());

        sectionManager.addParticipantsToSection(SectionDay.TUESDAY, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17");
        sectionManager.addParticipantsToSection(SectionDay.THURSDAY, "18,19,20,21,22,23,24,25,26,27,28,29");

        boolean swap = sectionManager.scheduleSwap(SectionDay.TUESDAY,1);

        assertTrue(swap);
        assertEquals(sectionManager.getSectionAttendees(SectionDay.TUESDAY).size(), 16);
    }

    @Test
    public void scheduleSwapFailsTest() {
        WeeklyRosterManager sectionManager = new WeeklyRosterManager(LocalDate.of(2019, 8, 13),
                new ParticipantManager());

        sectionManager.addParticipantsToSection(SectionDay.TUESDAY, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17");
        sectionManager.addParticipantsToSection(SectionDay.THURSDAY, "18,19,20,21,22,23,24,25,26,27,28,29");

        sectionManager.scheduleSwap(SectionDay.TUESDAY,1);
        boolean swap = sectionManager.scheduleSwap(SectionDay.TUESDAY,1);

        assertFalse(swap);
        assertEquals(sectionManager.getSectionAttendees(SectionDay.TUESDAY).size(), 16);
    }
}

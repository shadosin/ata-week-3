package com.kenzie.ata.attendance.management;

import com.kenzie.ata.attendance.participants.Participant;
import com.kenzie.ata.attendance.participants.ParticipantManager;
import com.kenzie.ata.set.ATASet;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * A tool to help ATA program managers track a week's attendance and swaps.
 */
//Worked with group CAP and ChatGPT to complete
public class WeeklyRosterManager {
    // the monday of the week the classes will be held
    private LocalDate mondayOfTeachingWeek;
    // a class with knowledge about each participant, linking their participant id with name and alias data
    private ParticipantManager manager;

    private int maxClassSize = 60;

    ATASet<Long> tuesdayParticipants = new ATASet<>(maxClassSize);
    ATASet<Long> thursdayParticipants = new ATASet<>(maxClassSize);
    
    // The maximum swap size
    private int maxSwapSize = 5;
    ATASet<Long> tuesdaySwapIns = new ATASet<>(maxSwapSize);
    ATASet<Long> thursdaySwapIns = new ATASet<>(maxSwapSize);


    /**
     * Instantiates a new Weekly roster manager.
     *
     * @param weekOfDate         the week of date
     * @param participantManager the participant manager
     */
    public WeeklyRosterManager(LocalDate weekOfDate, ParticipantManager participantManager) {
        mondayOfTeachingWeek = weekOfDate;
        manager = participantManager;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        WeeklyRosterManager sectionManager = new WeeklyRosterManager(LocalDate.of(2019, 8, 13),
                                                                     new ParticipantManager());

        System.out.println("Setting class rosters to standard sections.");
        sectionManager.addParticipantsToSection(SectionDay.TUESDAY, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17," +
            "18,19,20,21,22,23,24,25,26,27,28,29");

        sectionManager.addParticipantsToSection(SectionDay.THURSDAY, "30,31,32,33,34,35,36,37,38,39,40,41,42,43," +
            "44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59");


        System.out.println("\nMarking participants as absent.");
        // Mark Brian Oller absent from correct section
        boolean brianMarkedAbsent = sectionManager.scheduleAbsence(SectionDay.TUESDAY, 7);
        checkEquals(true, brianMarkedAbsent,
                    "ERROR: Unsuccessful in marking Participant with id: 7 as absent from " + SectionDay.TUESDAY);
        System.out.println("Participant with id: 7 marked as absent from " + SectionDay.TUESDAY);

        // Mark Caleb Santana absent from correct section
        boolean calebMarkedAbsent = sectionManager.scheduleAbsence(SectionDay.THURSDAY, 37);
        checkEquals(true, calebMarkedAbsent,
                    "ERROR: Unsuccessful in marking Participant with id: 37 as absent from " + SectionDay.THURSDAY);
        System.out.println("Participant with id: 37 marked as absent from " + SectionDay.THURSDAY);

        // Unable to mark Mwila Mwila absent from wrong section
        boolean mwilaMarkedAbsent = sectionManager.scheduleAbsence(SectionDay.TUESDAY, 57);
        checkEquals(false, mwilaMarkedAbsent,
                    "ERROR: Successful in marking Participant with id: 57 as absent in wrong section.");

//         COMPLETION FINISHED
//
//         EXTENSION - uncomment the below code blocks to test your swap logic

         System.out.println("\nPerforming participant swaps.");
         //Swap Subin Hong from the Tuesday section to the Thursday section
         boolean subinSwapped = sectionManager.scheduleSwap(SectionDay.TUESDAY, 22);
         checkEquals(true, subinSwapped, "ERROR: Unsuccessful in swapping participant with id: 22 into: " +
                      SectionDay.THURSDAY);
         System.out.println("Participant with id: 22 swapped into " + SectionDay.THURSDAY);

         //Swap Kundan Rai from the Thursday section to the Tuesday section
         boolean kundanSwapped = sectionManager.scheduleSwap(SectionDay.THURSDAY, 36);
         checkEquals(true,kundanSwapped, "ERROR: Unsuccessful in swapping participant with id: 36 into: " +
                      SectionDay.TUESDAY);
         System.out.println("Participant with id: 36 swapped into " + SectionDay.TUESDAY);

         //Unable to swap Theta Milhoan from the wrong section
         boolean thetaSwapped = sectionManager.scheduleSwap(SectionDay.THURSDAY, 8);
        checkEquals(false, thetaSwapped, "ERROR: Successful in swapping participant with id: 8 into section " +
                      "they already attend");

        System.out.println("\nPrinting section rosters.\n");
        sectionManager.printSectionRosters();
    }

    private static void checkEquals(boolean expected, boolean actual, String errorMessage) {
        if (expected != actual) {
            System.out.println(errorMessage);
            System.exit(1);
        }
    }

    /**
     * Helper method to choose the appropriate attendance set.
     * @param section The day of attendance.
     * @return The ATASet corresponding to the section.
     */
    public ATASet<Long> getSectionAttendees(SectionDay section) {
        ATASet<Long> sectionAttendees;
        if (section == SectionDay.TUESDAY) {
            sectionAttendees = tuesdayParticipants;
        } else if (section == SectionDay.THURSDAY) {
            sectionAttendees = thursdayParticipants;
        } else {
            throw new IllegalArgumentException(String.format(
                    "Can't get attendees for unknown section day %s!", section.name()));
        }
        return sectionAttendees;
    }

    /**
     * COMPLETION.
     * <p>
     * Add a comma separated list of participant ids to the denoted section
     * PARTICIPANTS: hint 1 - Java String's split method may be useful here
     * hint 2 - Our pre-work introduced us to wrapper class methods that transform strings to numbers
     * hint 3 - Use your print statement debugging technique here to ensure you're correctly adding
     * the participants
     *
     * @param section         which section the participants should be added to, TUESDAY or THURSDAY
     * @param participantList a comma separated list of ids
     */
    public void addParticipantsToSection(SectionDay section, String participantList) {
        // ATA Participants - implement
        ATASet<Long> sectionParticipants;
        if (section == SectionDay.TUESDAY) {
            sectionParticipants = tuesdayParticipants;
        } else if (section == SectionDay.THURSDAY) {
            sectionParticipants = thursdayParticipants;
        } else {
            throw new IllegalArgumentException("Invalid section day.");
        }

        String[] participantIds = participantList.split(",");
        for (String idString : participantIds) {
            try {
                long id = Long.parseLong(idString.trim());
                if (sectionParticipants.size() < maxClassSize) {
                    sectionParticipants.add(id);
                } else {
                    System.out.println("Class is full. Unable to add participant with id: " + id);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid participant ID: " + idString);
            }
        }

    //This method starts by checking which section day was passed in, and retrieves the appropriate ATASet instance for that section. It then splits the participant list string by the comma character, and loops through each substring. Inside the loop, it tries to parse the substring into a long value, and then checks if the section is already full. If not, it adds the participant ID to the appropriate ATASet. If the section is already full, it prints a message saying that it's unable to add that participant. If the substring cannot be parsed into a long value, it prints a message saying that the ID is invalid.



    }

    /**
     * COMPLETION.
     * <p>
     * Prints roster of attendees for both TUESDAY and THURSDAY sections, see README for an example.
     */
    public void printSectionRosters() {
        // ATA Participants - implement
        System.out.println("Tuesday section:");
        Iterator<Long> tuesdayIterator = tuesdayParticipants.iterator();
        while (tuesdayIterator.hasNext()) {
            long id = tuesdayIterator.next();
            System.out.println(manager.lookupParticipantById(id));
        }

        System.out.println("Thursday section:");
        Iterator<Long> thursdayIterator = thursdayParticipants.iterator();
        while (thursdayIterator.hasNext()) {
            long id = thursdayIterator.next();
            System.out.println(manager.lookupParticipantById(id));
        }

    }

    /**
     * COMPLETION.
     * <p>
     * Remove a participant from the list of attendees for the class.
     *
     * @param attendedSection the section the participant will be absent from
     * @param participantId   the id of the participant that will be absent
     * @return true if the participant was successful removed, false otherwise
     */
    public boolean scheduleAbsence(SectionDay attendedSection, long participantId) {
        // ATA Participants - implement
        ATASet<Long> sectionParticipants;
        if (attendedSection == SectionDay.TUESDAY) {
            sectionParticipants = tuesdayParticipants;
        } else if (attendedSection == SectionDay.THURSDAY) {
            sectionParticipants = thursdayParticipants;
        } else {
            throw new IllegalArgumentException("Invalid section day.");
        }

        if (sectionParticipants.remove(participantId)) {
            System.out.println("Marking participants as absent.");
            System.out.println("Participant with id: " + participantId + " marked as absent from " + attendedSection);
            return true;
        } else {
            return false;
        }

    }

    /**
     * EXTENSION.
     * <p>
     * Will swap a participant, denoted by their id, out of the provided section and into the other. Only 5 swaps
     * are allowed per section. If the maximum has been reached, the swap should fail.
     *
     * @param swapOutOf     the section the participant usually attends
     * @param participantId the id of the participant to swap
     * @return true if the swap was successful, false otherwise
     */
    public boolean scheduleSwap(SectionDay swapOutOf, long participantId) {
        // ATA Participants - implement
        ATASet<Long> sectionSwapOuts;
        ATASet<Long> sectionSwapIns;

        // Determine the swap-out and swap-in sets
        if (swapOutOf == SectionDay.TUESDAY) {
            sectionSwapOuts = tuesdayParticipants;
            sectionSwapIns = tuesdaySwapIns;
        } else if (swapOutOf == SectionDay.THURSDAY) {
            sectionSwapOuts = thursdayParticipants;
            sectionSwapIns = thursdaySwapIns;
        } else {
            throw new IllegalArgumentException("Invalid section day.");
        }

        // Check if the maximum number of swaps has already been reached for the section
        if (sectionSwapIns.size() >= 5) {
            System.out.println("Maximum number of swaps reached for this section.");
            return false;
        }

        // Attempt to remove the participant from the swap-out set
        if (!sectionSwapOuts.remove(participantId)) {
            System.out.println("Participant not found in section.");
            return false;
        }

        // Attempt to add the participant to the swap-in set
        if (!sectionSwapIns.add(participantId)) {
            System.out.println("Participant already in swap-ins for this section.");
            sectionSwapOuts.add(participantId);  // Put the participant back in the swap-out set
            return false;
        }

        // Swap successful
        return true;
    }

    /**
     * Helper method to choose the appropriate attendance swap-in set.
     * @param section The day of attendance.
     * @return The ATASet corresponding to the swap-ins for the section.
     */
    private ATASet<Long> getSectionSwapIns(SectionDay section) {
        ATASet<Long> sectionList;
        if (section == SectionDay.TUESDAY) {
            sectionList = tuesdaySwapIns;
        } else if (section == SectionDay.THURSDAY) {
            sectionList = thursdaySwapIns;
        } else {
            throw new IllegalArgumentException(String.format(
                    "Can't find swap ins for unknown section day %s!", section.name()));
        }
        return sectionList;
    }

    /**
     * Helper method to change a swap-from section day to a swap-to section day.
     * @param section The section day to convert.
     * @return The other section day.
     */
    private SectionDay otherSectionDay(SectionDay section) {
        if (section == SectionDay.TUESDAY) {
            return SectionDay.THURSDAY;
        } else if (section == SectionDay.THURSDAY) {
            return SectionDay.TUESDAY;
        }
        throw new IllegalArgumentException(String.format(
                "Can't find converse of unknown section day %s!", section.name()));
    }
}

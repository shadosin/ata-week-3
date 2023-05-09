package com.kenzie.ata.attendance.participants;

import com.kenzie.ata.set.ATASet;

/**
 * Participant management class with hardcoded data. This should read from a downstream data storage,
 * but we have a simplified version for class.
 */
public class ParticipantManager {

    private ATASet<Participant> participants;

    /**
     * Instantiates a new Participant manager.
     */
    public ParticipantManager() {
        participants = new ATASet<>(59);
        addSectionOneParticipants();
        addSectionTwoParticipants();
    }

    /**
     * Lookup participant by id participant.
     *
     * @param id the id
     * @return the participant
     */
    public Participant lookupParticipantById(long id) {
        for (Participant p : participants) {
            if (p.getId() == id) {
                return p;
            }
        }

        throw new IllegalArgumentException(String.format("Invalid id, %d does not correspond to a participant.",
                                                         id));
    }

    private void addSectionTwoParticipants() {
        participants.add(new Participant(30, "Hrach Gabrielyan", "hrachyag"));
        participants.add(new Participant(31, "Ty Warren", "wartyle"));
        participants.add(new Participant(32, "Anthony Leng", "anthleng"));
        participants.add(new Participant(33, "Kuljot Anand", "kuljot"));
        participants.add(new Participant(34, "Mahsa Nategh", "mnnategh"));
        participants.add(new Participant(35, "Evgenia Belofastowa", "evgenb"));
        participants.add(new Participant(36, "Kundan Rai", "rakundan"));
        participants.add(new Participant(37, "Caleb Santana", "aasantan"));
        participants.add(new Participant(38, "Dave Frost", "davfrost"));
        participants.add(new Participant(39, "Lina Liu", "llna"));
        participants.add(new Participant(40, "Jon Belvin", "belvinjb"));
        participants.add(new Participant(41, "Julia Brosseau", "jbrossea"));
        participants.add(new Participant(42, "Jami Schwarzwalder", "jamisch"));
        participants.add(new Participant(43, "Carolyn Sun", "suncarol"));
        participants.add(new Participant(44, "London Brown", "londonbr"));
        participants.add(new Participant(45, "Bri Jackson", "bjckso"));
        participants.add(new Participant(46, "Ricky Halim", "halirick"));
        participants.add(new Participant(47, "Adlaai Stelung", "stelunga"));
        participants.add(new Participant(48, "Christopher Benben", "cbbenben"));
        participants.add(new Participant(49, "Brandon Williams", "mbrandow"));
        participants.add(new Participant(50, "Callahan Jacobs", "callaj"));
        participants.add(new Participant(51, "Chad Whitesel", "cwhitese"));
        participants.add(new Participant(52, "Zach Bitseff", "bitsez"));
        participants.add(new Participant(53, "Janani Ravi", "janaravi"));
        participants.add(new Participant(54, "Oz Brown", "oliviabr"));
        participants.add(new Participant(55, "Mario Popish", "mpopish"));
        participants.add(new Participant(56, "Stephanie Song", "stepsong"));
        participants.add(new Participant(57, "Mwila Mwila", "mwimwila"));
        participants.add(new Participant(58, "Y Ngo", "ynng"));
        participants.add(new Participant(59, "Jacob Newton-Gladstein", "jacobnew"));
    }

    private void addSectionOneParticipants() {
        participants.add(new Participant(1, "Conner Beckwith", "beckwc"));
        participants.add(new Participant(2, "Shane Lyse", "shanelys"));
        participants.add(new Participant(3, "Matt Case", "macase"));
        participants.add(new Participant(4, "Aaron Alsept", "alsepta"));
        participants.add(new Participant(5, "Bob Snyder", "rosnyde"));
        participants.add(new Participant(6, "Kabila Williams", "kabilaw"));
        participants.add(new Participant(7, "Brian Oller", "ollbrian"));
        participants.add(new Participant(8, "Theta Milhoan", "milhoanb"));
        participants.add(new Participant(9, "Scott Sullivan", "sulscott"));
        participants.add(new Participant(10, "Max Twogood", "metwg"));
        participants.add(new Participant(11, "John-Paul Mullally", "jpaulm"));
        participants.add(new Participant(12, "Michael Martinez", "marmi"));
        participants.add(new Participant(13, "Aaron Edberg", "aeedberg"));
        participants.add(new Participant(14, "Clinton Looney", "clooney"));
        participants.add(new Participant(15, "Tina Yun", "tinayun"));
        participants.add(new Participant(16, "Jacob Best", "jakebest"));
        participants.add(new Participant(17, "Blake Pollitt", "bpollitt"));
        participants.add(new Participant(18, "Austin Carrillo", "carriaus"));
        participants.add(new Participant(19, "Sammy Wickert", "wickerts"));
        participants.add(new Participant(20, "Hollee Klesh", "holleej"));
        participants.add(new Participant(21, "Adam Albaum", "albaum"));
        participants.add(new Participant(22, "Subin Hong", "hansubin"));
        participants.add(new Participant(23, "Pavel Khinotskiy", "khinp"));
        participants.add(new Participant(24, "Lindsay Beilfuss", "beilfuss"));
        participants.add(new Participant(25, "Mary Liu", "maryliu"));
        participants.add(new Participant(26, "Daniel Fishbone", "fishbone"));
        participants.add(new Participant(27, "Mitch Reed", "mitcreed"));
        participants.add(new Participant(28, "Jackson Lin", "jackslin"));
        participants.add(new Participant(29, "Thomas Ralls", "thrall"));
    }
}

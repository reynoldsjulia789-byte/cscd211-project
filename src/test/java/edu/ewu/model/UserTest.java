package edu.ewu.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest
{
    /**
     * Tests if creating a new user stores username.
     * Tests if a new user starts with 0 sessions.
     *
     * @author Julia Reynolds
     */
    @Test
    @DisplayName("new user has no sessions, but has a username")
    void newUserHasNoSessions()
    {
        User user = new User("test");

        // Tests if username is stored correctly
        assertEquals("test", user.getUsername());

        // Tests if user starts with 0 sessions
        assertEquals(0, user.getSessions().size());
    }

    /**
     * Tests if user allows for blank username.
     * Blank or null usernames should not be allowed.
     *
     * @author Julia Reynolds
     */
    @Test
    @DisplayName("Check for invalid usernames")
    void cantCreateUserWithOutUsername()
    {
        // Should throw exception if username is blank
        assertThrows(IllegalArgumentException.class, () -> new User(""));

        // Should throw exception if username is null
        assertThrows(IllegalArgumentException.class, () -> new User(null));
    }

    /**
     * Tests if you can add sessions and track how many there are
     *
     * @author Julia Reynolds
     */
    @Test
    @DisplayName("ability to add sessions is intact")
    void canAddSessions()
    {
        User user = new User("Test");

        user.addSession(new StudySession());
        user.addSession(new StudySession());

        assertEquals(2, user.getSessions().size());
    }

    /**
     * Tests if you can modify user sessions
     *
     * @author Julia Reynolds
     */
    @Test
    @DisplayName("create a deep copy")
    void getSessionsReturnsDefensiveCopy() {
        User user = new User("Test user");
        user.addSession(new StudySession());

        // Get the list and try to modify it
        var sessions = user.getSessions();
        sessions.clear();

        // Original should be unaffected
        assertEquals(1, user.getSessions().size());
    }
}

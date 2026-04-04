package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.util.Duration;
import seedu.address.model.issue.IssueRecord;
import seedu.address.model.person.Person;
import seedu.address.model.reservation.Reservation;
import seedu.address.testutil.PersonBuilder;

public class PersonListPanelTest {

    @BeforeAll
    public static void setUpFxToolkit() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Platform.startup(latch::countDown);
        } catch (IllegalStateException e) {
            latch.countDown();
        }
        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void constructor_setsUpAutoRefreshTimeline() throws Exception {
        ObservableList<Person> persons = FXCollections.observableArrayList(
                new PersonBuilder().build());
        ObservableList<IssueRecord> issueRecords = FXCollections.observableArrayList();
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();

        PersonListPanel[] panelHolder = new PersonListPanel[1];
        runOnFxThread(() -> panelHolder[0] = new PersonListPanel(persons, issueRecords, reservations));

        PersonListPanel panel = panelHolder[0];
        assertNotNull(panel);

        Timeline refreshTimeline = (Timeline) getPrivateField(panel, "refreshTimeline");
        assertNotNull(refreshTimeline);
        assertEquals(Animation.Status.RUNNING, refreshTimeline.getStatus());
        assertEquals(Timeline.INDEFINITE, refreshTimeline.getCycleCount());
        assertEquals(1, refreshTimeline.getKeyFrames().size());

        KeyFrame keyFrame = refreshTimeline.getKeyFrames().get(0);
        assertEquals(Duration.seconds(30), keyFrame.getTime());
        assertNotNull(keyFrame.getOnFinished());
    }

    @Test
    public void constructor_setsItemsIntoPersonListView() throws Exception {
        ObservableList<Person> persons = FXCollections.observableArrayList(
                new PersonBuilder().withName("Alice Pauline").withStudentId("a1234567a").build(),
                new PersonBuilder().withName("Bob Choo").withStudentId("a2345678b").build());
        ObservableList<IssueRecord> issueRecords = FXCollections.observableArrayList();
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();

        PersonListPanel[] panelHolder = new PersonListPanel[1];
        runOnFxThread(() -> panelHolder[0] = new PersonListPanel(persons, issueRecords, reservations));

        @SuppressWarnings("unchecked")
        ListView<Person> personListView = (ListView<Person>) getPrivateField(panelHolder[0], "personListView");

        assertNotNull(personListView);
        assertEquals(persons, personListView.getItems());
        assertEquals(2, personListView.getItems().size());
    }

    private static Object getPrivateField(Object target, String fieldName) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }

    private static void runOnFxThread(Runnable action) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        final Throwable[] throwable = new Throwable[1];

        Platform.runLater(() -> {
            try {
                action.run();
            } catch (Throwable t) {
                throwable[0] = t;
            } finally {
                latch.countDown();
            }
        });

        latch.await(5, TimeUnit.SECONDS);

        if (throwable[0] != null) {
            throw new RuntimeException(throwable[0]);
        }
    }
}

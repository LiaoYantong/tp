package seedu.address.model.equipment;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.equipment.exceptions.DuplicateEquipmentException;

public class UniqueEquipmentListTest {
    private final UniqueEquipmentList uniqueEquipmentList = new UniqueEquipmentList();
    private final Equipment basketball = new Equipment(new EquipmentName("Wilson-Evolution"),
            "Basketball", EquipmentStatus.AVAILABLE);

    @Test
    public void add_duplicateEquipment_throwsDuplicateEquipmentException() {
        uniqueEquipmentList.add(basketball);
        assertThrows(DuplicateEquipmentException.class, () -> uniqueEquipmentList.add(basketball));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueEquipmentList.asUnmodifiableObservableList().remove(0));
    }
}

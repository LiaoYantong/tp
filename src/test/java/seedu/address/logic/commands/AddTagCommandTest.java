package seedu.address.logic.commands;


import seedu.address.model.ModelStub;
import seedu.address.model.alias.AliasMapping;
import seedu.address.model.equipment.Equipment;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.Taggable;

import static java.util.Objects.requireNonNull;

public class AddTagCommandTest {



    private static class ModelStubAcceptingTagAdded extends ModelStub {
        private final Tag tag;
        //private final Taggable taggable;
        ModelStubAcceptingTagAdded(Equipment equipment, Tag tag) {
            requireNonNull(tag);
            //this.taggable = tage;
            this.tag = tag;
        }

//        @Override
//        public boolean hasTaggable() {
//
//        }
//
//        @Override
//        public void addTag(tag) {
//
//        }
    }

}

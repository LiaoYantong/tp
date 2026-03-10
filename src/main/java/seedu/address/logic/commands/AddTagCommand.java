package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;
import seedu.address.model.tag.Tag;

import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class AddTagCommand extends Command{

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tags an existing room to the system. "
            + "Parameters: "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "MPSH-1 "
            + PREFIX_TAG + "Renovation";

    public static final String MESSAGE_SUCCESS = "Success! %1$s has been tagged";
    public static final String MESSAGE_ERROR = "Failure! Tagging was unsuccessful";


    private final Room room;
    private final Set<Tag> tags;

    public AddTagCommand(RoomName roomName, Set<Tag> tagList) {
        requireNonNull(room);

        this.room = new Room(roomName);
        this.tags = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);


        if (!model.hasRoom(room)) {
            throw new CommandException(MESSAGE_ERROR);
        }

        model.addTag(roomName, tags);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tags));
    }
}


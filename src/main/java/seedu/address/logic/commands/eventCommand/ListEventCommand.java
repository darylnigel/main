package seedu.address.logic.commands.eventCommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all events in the address book to the user.
 */

public class ListEventCommand extends Command {
    public static final String COMMAND_WORD = "listEvent";

    public static final String COMMAND_ALIAS = "lE";

    public static final String MESSAGE_SUCCESS = "Listed all events";

    /**
     * List all the event in the ClubHub.
     * @param model {@code Model} which the command should operate on.
     * @param history {@code CommandHistory} which the command should operate on.
     * @return
     */
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(MESSAGE_SUCCESS);

    }
}

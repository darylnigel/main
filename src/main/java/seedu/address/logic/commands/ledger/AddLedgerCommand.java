package seedu.address.logic.commands.ledger;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ledger.Ledger;





/**
 * Adds a ledger to the Club Book
 */
public class AddLedgerCommand extends Command {
    public static final String COMMAND_WORD = "addLedger";
    public static final String MESSAGE_DUPLICATE_LEDGER = "This ledger already exists in the club book";
    public static final String MESSAGE_SUCCESS = "New ledger added: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a finance entry into the ledger. "
            + "parameters: d/[DATE DD/MM]";
    private final Logger logger = LogsCenter.getLogger(AddLedgerCommand.class);
    private final Ledger addLedger;

    public AddLedgerCommand(Ledger ledger) {
        requireNonNull(ledger);
        addLedger = ledger;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasLedger(addLedger)) {
            throw new CommandException(MESSAGE_DUPLICATE_LEDGER);
        }
        model.addLedger(addLedger);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, addLedger));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLedgerCommand // instanceof handles nulls
                && addLedger.equals(((AddLedgerCommand) other).addLedger));
    }
}

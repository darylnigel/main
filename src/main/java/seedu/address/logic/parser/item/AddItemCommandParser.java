package seedu.address.logic.parser.item;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.item.AddItemCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemLocation;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.ItemQuantity;

/**
 * Parses input arguments and creates a new AddItemCommand object
 */
public class AddItemCommandParser implements Parser<AddItemCommand> {

    private final Logger logger = LogsCenter.getLogger(AddItemCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the AddItemCommand
     * and returns an AddItemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddItemCommand parse(String args) throws ParseException {
        logger.info("Still parsing...");
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ITEM_NAME, PREFIX_ITEM_QUANTITY,
                PREFIX_ITEM_LOCATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_NAME, PREFIX_ITEM_QUANTITY, PREFIX_ITEM_LOCATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE));
        }

        logger.info("Parsed");
        ItemName itemName = ParserUtil.parseItemName(argMultimap.getValue(PREFIX_ITEM_NAME).get());
        ItemQuantity itemQuantity = ParserUtil.parseItemQuantity(argMultimap.getValue(PREFIX_ITEM_QUANTITY).get());
        ItemLocation itemLocation = ParserUtil.parseItemLocation(argMultimap.getValue(PREFIX_ITEM_LOCATION).get());

        logger.info("Creating new item");
        Item item = new Item(itemName, itemQuantity, itemLocation);
        logger.info("Created new item");

        return new AddItemCommand(item);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

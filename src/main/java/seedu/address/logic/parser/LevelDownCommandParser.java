package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXCLUDE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LevelDownCommand;
import seedu.address.logic.commands.LevelUpCommand;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new LevelDownCommand object
 */
public class LevelDownCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the LevelDownCommand
     * and returns a LevelDownCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LevelDownCommand parse(String args) throws ParseException {
        Prefix firstPrefix;
        try {
            firstPrefix = ArgumentTokenizer.getLastPrefix(
                    args, PREFIX_EXCLUDE);
        } catch (ParseException pe) {
            firstPrefix = new Prefix("");
        }
        List<String> indexStrings;
        if (firstPrefix.getPrefix().equals("")) {
            indexStrings = Arrays.asList();
        } else {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_EXCLUDE);
            String indexString = argMultimap.getValue(PREFIX_EXCLUDE).get();
            indexStrings = Arrays.asList(indexString.split(" "));
        }
        List<Index> indices = new ArrayList<>();
        for (int i = 0; i < indexStrings.size(); i++) {
            Index index;
            try {
                index = ParserUtil.parseIndex(indexStrings.get(i));
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, LevelUpCommand.MESSAGE_USAGE), pe);
            }
            indices.add(index);
        }
        return new LevelDownCommand(indices);
    }
}



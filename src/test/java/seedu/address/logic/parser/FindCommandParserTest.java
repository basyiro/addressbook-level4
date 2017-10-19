package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate(
                        FindCommand.COMMAND_WORD, Arrays.asList("Alice", "Bob"), false));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        FindCommand expectedAddressFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate(
                        FindCommand.COMMAND_WORD_ADDRESS, Arrays.asList("wall", "michegan"), false));
        assertParseSuccess(parser, "wall michegan", expectedAddressFindCommand);

        FindCommand expectedEmailFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate(
                        FindCommand.COMMAND_WORD_EMAIL, Arrays.asList(
                                "heinz@example.com", "werner@example.com"), false));
        assertParseSuccess(parser, "heinz@example.com werner@example.com", expectedEmailFindCommand);

        FindCommand expectedPhoneFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate(
                        FindCommand.COMMAND_WORD_PHONE, Arrays.asList("95352563", "9482224"), false));
        assertParseSuccess(parser, "95352563 9482224", expectedPhoneFindCommand);


        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
        assertParseSuccess(parser, " \n wall \n \t michegan  \t", expectedAddressFindCommand);
        assertParseSuccess(parser, " \n heinz@example.com \n \t werner@example.com  \t", expectedEmailFindCommand);
        assertParseSuccess(parser, " \n 95352563 \n \t 9482224  \t", expectedPhoneFindCommand);
    }

}

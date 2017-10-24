package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.ui.MainWindow;

/**
 * Selects a person identified using it's last displayed index from the address book.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";
    public static final String COMMAND_ALIAS = "t";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Switches to selected theme\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_THEME_SUCCESS = "Theme updated: %1$s";

    private final Index targetIndex;

    public ThemeCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() throws CommandException {

        List<String> themeList = new ArrayList<>();
        themeList.add("BlueTheme.css");
        themeList.add("DarkTheme.css");

        if (targetIndex.getZeroBased() >= themeList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        switch (targetIndex.getOneBased()){
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
        return new CommandResult(String.format(MESSAGE_THEME_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ThemeCommand // instanceof handles nulls
                && this.targetIndex.equals(((ThemeCommand) other).targetIndex)); // state check
    }

    public void setTheme(String args) {

    }
}

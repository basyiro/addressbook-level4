package seedu.address.logic.commands;

/**
 * Sorts all persons in the address book by alphabetical order of contact's name and lists to the user.
 */
public class SortCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays all persons in the address book as a list"
            + " sorted by alphabetical order of name.\n";

    public static final String MESSAGE_SUCCESS = "Sorted all persons";

    private final boolean isDescendingSort;

    public SortCommand(boolean isDescendingSort) {
        this.isDescendingSort = isDescendingSort;
    }


    @Override
    public CommandResult executeUndoableCommand() {
        model.sortContactList(isDescendingSort);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

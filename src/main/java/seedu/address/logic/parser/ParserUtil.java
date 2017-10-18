package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Homepage;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 * {@code ParserUtil} contains methods that take in {@code Optional} as parameters. However, it goes against Java's
 * convention (see https://stackoverflow.com/a/39005452) as {@code Optional} should only be used a return type.
 * Justification: The methods in concern receive {@code Optional} return values from other methods as parameters and
 * return {@code Optional} values based on whether the parameters were present. Therefore, it is redundant to unwrap the
 * initial {@code Optional} before passing to {@code ParserUtil} as a parameter and then re-wrap it into an
 * {@code Optional} return value inside {@code ParserUtil} methods.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INSUFFICIENT_PARTS = "Number of parts must be more than 1.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws IllegalValueException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws IllegalValueException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new IllegalValueException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code Optional<String> name} into an {@code Optional<Name>} if {@code name} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Name> parseName(Optional<String> name) throws IllegalValueException {
        requireNonNull(name);
        return name.isPresent() ? Optional.of(new Name(name.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> phone} into an {@code Optional<Phone>} if {@code phone} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Phone> parsePhone(Optional<String> phone) throws IllegalValueException {
        requireNonNull(phone);
        return phone.isPresent() ? Optional.of(new Phone(phone.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> address} into an {@code Optional<Address>} if {@code address} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Address> parseAddress(Optional<String> address) throws IllegalValueException {
        requireNonNull(address);
        return address.isPresent() ? Optional.of(new Address(address.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> email} into an {@code Optional<Email>} if {@code email} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Email> parseEmail(Optional<String> email) throws IllegalValueException {
        requireNonNull(email);
        return email.isPresent() ? Optional.of(new Email(email.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> homepage} into an {@code Optional<Homepage>} if {@code homepage} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Homepage> parseHomepage(Optional<String> homepage) throws IllegalValueException {
        requireNonNull(homepage);
        if (homepage.isPresent()) {
            if (homepage.get().equals("")) {
                return Optional.of(new Homepage());
            } else {
                return Optional.of(new Homepage(homepage.get()));
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws IllegalValueException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code ArrayList<String>}.
     */
    public static ArrayList<String> parseAllDetail(Collection<String> detail, String commandType)
            throws IllegalValueException {
        requireNonNull(detail);
        ArrayList<String> detailList = new ArrayList<String>();
        String []detailString = detail.toString().split("\\s+");
        for (String string : detailString) {
            string = string.replaceAll("['\\[\\]']", "");
            if (commandType.equals(FindCommand.COMMAND_WORD_ADDRESS) && !Address.isValidAddress(string)) {
                throw new IllegalValueException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
            } else if (commandType.equals(FindCommand.COMMAND_WORD_EMAIL) && !Email.isValidEmail(string)) {
                throw new IllegalValueException(Email.MESSAGE_EMAIL_CONSTRAINTS);
            } else if (commandType.equals(FindCommand.COMMAND_WORD_PHONE) && !Phone.isValidPhone(string)) {
                throw new IllegalValueException(Phone.MESSAGE_PHONE_CONSTRAINTS);
            } else if (commandType.equals(FindCommand.COMMAND_WORD_HOMEPAGE) && !Homepage.isValidHomepage(string)) {
                throw new IllegalValueException(Homepage.MESSAGE_HOMEPAGE_CONSTRAINTS);
            } else if (commandType.equals(FindCommand.COMMAND_WORD_TAG) && !Tag.isValidTagName(string)) {
                throw new IllegalValueException(Tag.MESSAGE_TAG_CONSTRAINTS);
            } else {
                detailList.add(string);
            }
        }
        return detailList;
    }
}

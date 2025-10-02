package grace;

public class Parser {
    public static Command parse(String input) throws GraceException {
        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            int markIndex = parseIndex(input);
            return new MarkCommand(markIndex);
        case "unmark":
            int unmarkIndex = parseIndex(input);
            return new UnmarkCommand(unmarkIndex);
        case "delete":
            int deleteIndex = parseIndex(input);
            return new DeleteCommand(deleteIndex);
        case "todo":
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new GraceException("You can’t add a todo without a description.");
            }
            return new AddCommand(new Todo(parts[1]));
        case "deadline": {
            String[] deadlineParts = input.substring(8).split("/by", 2);
            if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                throw new GraceException("A deadline needs both a description and a /by time.");
            }
            return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
        }
        case "event": {
            String[] eventParts = input.substring(5).split("/from |/to ");
            if (eventParts.length < 3) {
                throw new GraceException("An event needs a description, /from time, and a /to time.");
            }
            return new AddCommand(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
        }
        case "find":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new GraceException("The find command requires a keyword.");
            }
            return new FindCommand(parts[1].trim());
        default:
            throw new GraceException("Hmm, I don't quite understand that command!");
        }
    }

    private static int parseIndex(String input) throws GraceException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new GraceException("That's not a valid task number.");
        }
    }
}

package grace;

public class Parser {
    public static Command parse(String input) throws GraceException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            int index = parseIndex(input);
            return new MarkCommand(index);
        } else if (input.startsWith("unmark ")) {
            int index = parseIndex(input);
            return new UnmarkCommand(index);
        } else if (input.startsWith("delete ")) {
            int index = parseIndex(input);
            return new DeleteCommand(index);
        } else if (input.startsWith("todo")) {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                throw new GraceException("You can’t add a todo without a description.");
            }
            return new AddCommand(new Todo(description));
        } else if (input.startsWith("deadline")) {
            String[] parts = input.substring(8).split("/by", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new GraceException("A deadline needs both a description and a /by time.");
            }
            return new AddCommand(new Deadline(parts[0].trim(), parts[1].trim()));
        } else if (input.startsWith("event")) {
            String[] parts = input.substring(5).split("/from |/to ");
            if (parts.length < 3) {
                throw new GraceException("An event needs a description, /from time, and a /to time.");
            }
            return new AddCommand(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
        } else if (input.startsWith("find")) {
            String[] parts = input.trim().split(" ", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new GraceException("The find command requires a keyword.");
            }
            return new FindCommand(parts[1].trim());
        }

        else {
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

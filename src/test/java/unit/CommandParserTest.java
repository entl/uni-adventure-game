package unit;

import com.university.elements.items.ItemFactory;
import com.university.utils.commands.*;
import com.university.utils.parsers.CommandParser;
import com.university.utils.ui.UI;
import com.university.utils.ui.UIManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CommandParserTest {

    private CommandParser commandParser;

    @Mock
    private ItemFactory mockItemFactory;

    @Mock
    private UI mockUI;

    @Before
    public void setUp() {
        // create mocks automatically
        MockitoAnnotations.openMocks(this);
        commandParser = new CommandParser();
        UIManager.setInstance(mockUI);
    }

    @Test
    public void parse_ShouldReturnMoveCommand_WhenInputContainsValidDirection() {
        String input = "move north";
        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof MoveCommand);
    }

    @Test
    public void parse_ShouldReturnPickUpCommand_WhenInputContainsValidItem() {
        String input = "pick up hammer";

        when(mockItemFactory.findBestMatchingItemName(any())).thenReturn("hammer");

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof PickUpCommand);
    }

    @Test
    public void parse_ShouldReturnUseCommand_WhenInputContainsValidItem() {
        String input = "use potion";

        when(mockItemFactory.findBestMatchingItemName(any())).thenReturn("potion");

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof UseCommand);
    }

    @Test
    public void parse_ShouldReturnNull_WhenInvalidDirectionIsProvided() {
        String input = "move upward";

        ICommand command = commandParser.parse(input);

        assertNull(command);
        verify(mockUI).displayMessage("* Specify direction");
    }

    @Test
    public void parse_ShouldReturnNull_WhenInvalidCommandIsProvided() {
        String input = "jump";

        ICommand command = commandParser.parse(input);

        assertNull(command);
        verify(mockUI).displayMessage("* I can't understand your input.\n* Enter `help` to see commands");
    }

    @Test
    public void parse_ShouldReturnLookAroundCommand_WhenInputIsLookAround() {
        String input = "look around";

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof LookAroundCommand);
    }

    @Test
    public void parse_ShouldReturnHelpCommand_WhenInputIsHelp() {
        String input = "help";

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    public void parse_ShouldReturnQuitCommand_WhenInputIsQuit() {
        String input = "quit";

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof QuitCommand);
    }

    @Test
    public void parse_ShouldIgnoreStopwords_WhenInputContainsStopwords() {
        String input = "the move to the north";

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof MoveCommand);
    }
}
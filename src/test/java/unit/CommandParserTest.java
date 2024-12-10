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
    private CommandFactory mockCommandFactory;

    @Mock
    private UI mockUI;

    @Before
    public void setUp() {
        // create mocks automatically
        MockitoAnnotations.openMocks(this);
        commandParser = new CommandParser(mockItemFactory, mockCommandFactory);
        UIManager.setInstance(mockUI);
    }

    @Test
    public void parse_ShouldReturnMoveCommand_WhenInputContainsValidDirection() {
        String input = "move north";
        when(mockCommandFactory.createCommand(any(), any())).thenReturn(new MoveCommand(Direction.NORTH));
        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof MoveCommand);
    }

    @Test
    public void parse_ShouldReturnPickUpCommand_WhenInputContainsValidItem() {
        String input = "pick up hammer";

        when(mockItemFactory.findBestMatchingItemName(any())).thenReturn("hammer");
        when(mockCommandFactory.createCommand(any(), any())).thenReturn(new PickUpCommand("hammer"));

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof PickUpCommand);
    }

    @Test
    public void parse_ShouldReturnUseCommand_WhenInputContainsValidItem() {
        String input = "use potion";

        when(mockItemFactory.findBestMatchingItemName(any())).thenReturn("potion");
        when(mockCommandFactory.createCommand(any(), any())).thenReturn(new UseCommand("potion"));

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof UseCommand);
    }


    @Test
    public void parse_ShouldReturnNull_WhenInvalidCommandIsProvided() {
        String input = "jump";
        when(mockCommandFactory.createCommand(any(), any())).thenReturn(new UnknownCommand());

        ICommand command = commandParser.parse(input);

        assertTrue(command instanceof UnknownCommand);
    }

    @Test
    public void parse_ShouldReturnLookAroundCommand_WhenInputIsLookAround() {
        String input = "look around";
        when(mockCommandFactory.createCommand(any(), any())).thenReturn(new LookAroundCommand());

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof LookAroundCommand);
    }

    @Test
    public void parse_ShouldReturnHelpCommand_WhenInputIsHelp() {
        String input = "help";
        when(mockCommandFactory.createCommand(any(), any())).thenReturn(new HelpCommand());

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    public void parse_ShouldReturnQuitCommand_WhenInputIsQuit() {
        String input = "quit";
        when(mockCommandFactory.createCommand(any(), any())).thenReturn(new QuitCommand());

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof QuitCommand);
    }

    @Test
    public void parse_ShouldIgnoreStopwords_WhenInputContainsStopwords() {
        String input = "the move to the north";
        when(mockCommandFactory.createCommand(any(), any())).thenReturn(new MoveCommand(Direction.NORTH));

        ICommand command = commandParser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof MoveCommand);
    }
}
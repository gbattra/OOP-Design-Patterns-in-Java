package tests;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import htw.game.ConsoleController;
import htw.game.HtwPlayer;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.game.commands.CustomConfigCommand;
import htw.game.commands.DirActionStrategy;
import htw.game.commands.ICommand;
import htw.game.commands.IdActionStrategy;
import htw.game.commands.MoveCommand;
import htw.game.commands.NewConfigCommand;
import htw.game.commands.ShootCommand;
import htw.game.commands.StandardConfigCommand;
import htw.game.commands.StartGameCommand;
import htw.level.Cave;
import htw.level.DeadEnd;
import htw.level.IHtwMaze;
import htw.level.IHtwNode;
import htw.level.StandardStrategy;
import htw.level.TunnelStrategy;
import htw.tools.HtwConfiguration;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwGameBuilder;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfiguration;
import htw.tools.IHtwConfigurationBuilder;
import maze.Direction;
import maze.IMazeBuilder;
import maze.components.Coordinates;
import maze.components.DeadEndNode;
import maze.components.Edge;
import maze.components.GoldRoomNode;
import maze.components.ICoordinates;
import maze.components.IEdge;
import maze.components.IPath;
import maze.components.Node;
import maze.components.Path;
import maze.components.ThiefRoomNode;
import maze.config.IConfiguration;
import maze.config.IConfigurationBuilder;
import maze.config.MazeConfigurationBuilder;
import maze.game.IMazePlayer;
import maze.game.MazePlayer;
import tests.htw.mocks.MockCustomConfigCommand;
import tests.htw.mocks.MockGame;
import tests.htw.mocks.MockHtwCommandMapFactory;
import tests.htw.mocks.MockNewConfigCommand;
import tests.htw.mocks.MockStandardConfigCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Due to Handin server file limits, I have condensed all the tests from previous homework
 * assignments into single test files.
 */
public class CondensedTests {
  @Test
  public void testPlayerValidConstructor() {
    try {
      IMazePlayer player = new MazePlayer("Joe");
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayerInvalidConstructor() {
    IMazePlayer player = new MazePlayer("");
  }

  @Test
  public void testPlayerGetters() {
    Node node = new GoldRoomNode(new Coordinates(0,0), 10);
    IMazePlayer player = new MazePlayer("Joe");
    player = player.loot(node);
    assertEquals(10, player.getGold());
    assertEquals("Joe", player.getName());
  }

  @Test
  public void testMazePathValidConstructor() {
    try {
      ICoordinates target = new Coordinates(1, 1);
      IPath path = new Path(target);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testGetTarget() {
    ICoordinates target = new Coordinates(1, 1);
    IPath path = new Path(target);
    assertEquals(target, path.getTarget());
  }

  @Test
  public void testAddNodes() {
    ICoordinates target = new Coordinates(1, 1);
    Node node = new DeadEndNode();
    IPath path = new Path(target);
    assertEquals(0, path.getCoordinatesTraversed().size());
    path = path.addCoordinates(node.getCoordinates());
    assertEquals(1, path.getCoordinatesTraversed().size());
  }

  @Test
  public void testSetReachesTarget() {
    ICoordinates target = new Coordinates(1, 1);
    IPath path = new Path(target);
    assertFalse(path.reachesTarget());
    path = path.setReachesTarget(true);
    assertTrue(path.reachesTarget());
  }

  @Test
  public void testTotalGold() {
    ICoordinates target = new Coordinates(3, 3);
    IPath path = new Path(target);
    Node room1 = new GoldRoomNode(new Coordinates(0,0), 10);
    Node thief1 = new ThiefRoomNode(new Coordinates(1, 0), 0.1);
    Node room2 = new GoldRoomNode(new Coordinates(2, 0), 10);
    Node thief2 = new ThiefRoomNode(new Coordinates(2, 1), 0.1);
    path = path
            .enter(room1)
            .enter(thief1)
            .enter(room2)
            .enter(thief2);
    assertEquals(17, path.totalGold());
  }

  @Test
  public void testMazeEdgeValidConstructor() {
    try {
      ICoordinates coordinateOne = new Coordinates(1, 1);
      ICoordinates coordinateTwo = new Coordinates(1, 2);
      IEdge mazeEdge = new Edge(coordinateOne, coordinateTwo, Direction.WEST, Direction.EAST);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testMazeEdgeGetters() {
    ICoordinates coordinateOne = new Coordinates(1, 1);
    ICoordinates coordinateTwo = new Coordinates(1, 2);
    IEdge mazeEdge = new Edge(coordinateOne, coordinateTwo, Direction.WEST, Direction.EAST);
    assertEquals(coordinateOne, mazeEdge.getTail());
    assertEquals(coordinateTwo, mazeEdge.getHead());
  }

  @Test
  public void testValidCoordinatesConstructor() {
    try {
      ICoordinates coordinates = new Coordinates(1, 1);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorX() {
    ICoordinates coordinates = new Coordinates(-1, 0);
    fail("Invalid constructor should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorY() {
    ICoordinates coordinates = new Coordinates(0, -1);
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testCoordinatesGetters() {
    ICoordinates coordinates = new Coordinates(1, 2);
    assertEquals(1, coordinates.getX());
    assertEquals(2, coordinates.getY());
  }

  @Test
  public void testCoordinatesToString() {
    ICoordinates coordinates = new Coordinates(2, 3);
    assertEquals("(2, 3)", coordinates.toString());
  }

  @Test
  public void testEquals() {
    ICoordinates coordinatesOne = new Coordinates(1, 1);
    ICoordinates coordinatesTwo = new Coordinates(1, 1);
    assertEquals(coordinatesOne, coordinatesTwo);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountZero() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setColumnCount(0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountZero() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setRowCount(0);
    fail("Invalid setter should have failed.");
  }

  @Test
  public void testGoalChange() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder()
            .setRowCount(14)
            .setColumnCount(14);
    IConfiguration config = builder.build();
    assertEquals(new Coordinates(14, 14), config.goal());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setColumnCount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountOOB() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setColumnCount(66);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setRowCount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountOOB() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setRowCount(66);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartNegCol() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(-1, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartNegRow() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(0, -1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitNegRow() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(0, -1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitNegCol() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(-1, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitOOBCol() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(15, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitOOBRow() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(0, 15);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartOOBRow() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(0, 15);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartOOBCol() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(15, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefFrequencyNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefFrequency(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefFrequencyAboveOne() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefFrequency(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefPenaltyNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefPenalty(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefPenaltyAboveOne() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefPenalty(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldFrequencyNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoldFrequency(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldFrequencyAboveOne() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoldFrequency(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldAmountNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoldAmount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetTargetRoomCountNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefFrequency(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetTargetRoomCountOOB() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setTargetEdgeCount(500);
    fail("Invalid setter should have failed.");
  }

  @Test
  public void testOppositeOf() {
    assertEquals(Direction.SOUTH,Direction.NORTH.opposite());
    assertEquals(Direction.NORTH, Direction.SOUTH.opposite());
    assertEquals(Direction.EAST, Direction.WEST.opposite());
    assertEquals(Direction.WEST, Direction.EAST.opposite());
  }

  @Test
  public void testStringToDirection() {
    assertEquals(Direction.NORTH, Direction.stringToDirection("N"));
    assertEquals(Direction.NORTH, Direction.stringToDirection("NORTH"));

    assertEquals(Direction.SOUTH, Direction.stringToDirection("S"));
    assertEquals(Direction.SOUTH, Direction.stringToDirection("SOUTH"));

    assertEquals(Direction.EAST, Direction.stringToDirection("E"));
    assertEquals(Direction.EAST, Direction.stringToDirection("EAST"));

    assertEquals(Direction.WEST, Direction.stringToDirection("W"));
    assertEquals(Direction.WEST, Direction.stringToDirection("WEST"));
  }

  @Test
  public void testDeadEndNodeConstructor() {
    try {
      Node node = new DeadEndNode();
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testDeadEndGetters() {
    Node node = new DeadEndNode();
    assertEquals(0, node.getGoldCount());
    assertEquals(0, node.getThiefPenalty(), 0.00001);
    assertEquals(new Coordinates(0, 0), node.getCoordinates());
    assertEquals(new DeadEndNode(), node.getNode(Direction.EAST));
    assertFalse(node.canReach(new Coordinates(0, 0)));
    assertEquals(
            new Path(new Coordinates(0, 0)),
            node.wealthiestPathTo(new Coordinates(0, 0)));
    assertEquals(
            new Path(new Coordinates(0, 0)),
            node.exploreTo(new Coordinates(0, 0)));
    assertEquals("Dead End Node", node.toString());
    assertEquals(node.get(new Coordinates(0, 0)), new DeadEndNode());
  }

  @Test
  public void testLoot() {
    Node node = new DeadEndNode();
    assertEquals(10, node.loot(10));
  }

  @Test
  public void testCustomConfig() {
    try {
      StringBuilder log = new StringBuilder();
      ICommand<IHtwConfigurationBuilder> cmd = new CustomConfigCommand(
              new Scanner("5 5 0.2 0.3 "), log);
      IHtwConfiguration config = cmd.execute(new HtwConfigurationBuilder()).build();
      assertEquals(5, config.rowCount());
      assertEquals(5, config.columnCount());
      assertEquals(0.2, config.pitFrequency(), 0.001);
      assertEquals(0.3, config.batFrequency(), 0.001);
      assertFalse(config.isRoomMaze());
      assertFalse(config.isWrappingMaze());
      assertEquals(
              "Row count: "
                      + "Column count: "
                      + "Pit frequency (double): "
                      + "Bat frequency (double): ",
              log.toString());
    } catch (Exception e) {
      fail("Valid execute should not have failed.");
    }
  }

  @Test
  public void testNewGameCommand() {
    try {
      StringBuilder log = new StringBuilder();
      ICommand<IHtwConfigurationBuilder> configCmd = new MockNewConfigCommand(log);
      ICommand<IHtwGame> gameCommand = new StartGameCommand(
              new Scanner("Greg 10"), System.out, configCmd);
      gameCommand.execute(null);
      assertEquals("config - execute", log.toString());
    } catch (Exception e) {
      fail("Valid execute() should not have failed.");
    }
  }

  @Test
  public void testExecute() {
    try {
      ICommand<IHtwConfigurationBuilder> standard = new StandardConfigCommand();
      IHtwConfigurationBuilder builder = new HtwConfigurationBuilder();
      assertEquals(builder, standard.execute(builder));
    } catch (Exception e) {
      fail("Valid execute should not have failed.");
    }
  }

  @Test
  public void testShootById() {
    StringBuffer log = new StringBuffer();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> shootCmd = new ShootCommand(
            new Scanner("1 1"), System.out, new IdActionStrategy());
    try {
      shootCmd.execute(game);
      assertEquals("shoot - 1 - 1", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }

  @Test
  public void testShootByDir() {
    StringBuffer log = new StringBuffer();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> shootCmd = new ShootCommand(
            new Scanner("e 1"), System.out, new DirActionStrategy());
    try {
      shootCmd.execute(game);
      assertEquals("shoot - EAST - 1", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }

  @Test
  public void testNewConfig() {
    try {
      StringBuilder log = new StringBuilder();
      ICommand<IHtwConfigurationBuilder> customCmd = new MockCustomConfigCommand(log);
      ICommand<IHtwConfigurationBuilder> standardCmd = new MockStandardConfigCommand(log);
      ICommand<IHtwConfigurationBuilder> newConfigCommand = new NewConfigCommand(
              new Scanner("custom standard"), System.out, customCmd, standardCmd);
      newConfigCommand.execute(null);
      assertEquals("custom - execute", log.toString());
      log.setLength(0);
      newConfigCommand.execute(null);
      assertEquals("standard - execute", log.toString());
    } catch (Exception e) {
      fail("Valid execute() should not have failed.");
    }
  }

  @Test
  public void testMoveById() {
    StringBuffer log = new StringBuffer();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> moveCmd = new MoveCommand(
            new Scanner("1"), System.out, new IdActionStrategy());
    try {
      moveCmd.execute(game);
      assertEquals("move - 1", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }

  @Test
  public void testMoveByDir() {
    StringBuffer log = new StringBuffer();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> moveCmd = new MoveCommand(
            new Scanner("e"), System.out, new DirActionStrategy());
    try {
      moveCmd.execute(game);
      assertEquals("move - EAST", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }

  @Test
  public void testValidConstructor() {
    try {
      IHtwConfiguration config = new HtwConfiguration(
              5,5, new Coordinates(0, 0), 0.1, 0.2, true, false, 3, 1, System.out, 10, 1);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IHtwConfiguration config = new HtwConfiguration(
            5,5, new Coordinates(0, 0), -0.1, -0.2, true, false, 3, 1, System.out, 10, 1);
  }

  @Test
  public void testGetters() {
    IHtwConfiguration config = new HtwConfiguration(
            5,5, new Coordinates(0, 0), 0.1, 0.2, true, false, 3, 1, System.out, 10, 1);
    assertEquals(0.1, config.pitFrequency(), 0.001);
    assertEquals(0.2, config.batFrequency(), 0.001);
  }
  @Test
  public void testConstructor() {
    try {
      StringBuffer log = new StringBuffer();
      Runnable controller = new ConsoleController(
              new Scanner(""),
              log,
              new DirActionStrategy(),
              new MockHtwCommandMapFactory(new Scanner(""), log));
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCntrlConstructor() {
    Runnable controller = new ConsoleController(
            null, null, null, null);
  }

  @Test
  public void testQuit() {
    try {
      Scanner scanner = new Scanner("greg 10 standard q");
      StringBuffer log = new StringBuffer();
      Runnable controller = new ConsoleController(
              scanner,
              log,
              new DirActionStrategy(),
              new MockHtwCommandMapFactory(scanner, log));
      controller.run();
      assertEquals(
              "execute - start - greg - 10 - standard"
                      + "\nStarting game..."
                      + "\nQuit -> 'q' / 'quit'"
                      + "\nRestart -> 'restart'"
                      + "\n"
                      + "\nstatus - status"
                      + "\n'shoot' or 'move'? "
                      + "Quitting...",
              log.toString());
    } catch (Exception e) {
      fail("Valid quit() should not have failed.");
    }
  }

  @Test
  public void testMoveAndShoot() {
    try {
      Scanner scanner = new Scanner("greg 10 standard move e shoot e 1 q");
      StringBuffer log = new StringBuffer();
      Runnable controller = new ConsoleController(
              scanner,
              log,
              new DirActionStrategy(),
              new MockHtwCommandMapFactory(scanner, log));
      controller.run();
      assertEquals(
              "execute - start - greg - 10 - standard"
                      + "\nStarting game..."
                      + "\nQuit -> 'q' / 'quit'"
                      + "\nRestart -> 'restart'"
                      + "\n"
                      + "\nstatus - status"
                      + "\n'shoot' or 'move'? "
                      + "execute - move - e"
                      + "\nstatus - status"
                      + "\n'shoot' or 'move'? "
                      + "execute - shoot - e - 1"
                      + "\nstatus - status"
                      + "\n'shoot' or 'move'? "
                      + "Quitting...",
              log.toString());
    } catch (Exception e) {
      fail("Valid quit() should not have failed.");
    }
  }

  @Test
  public void testValidBuild() {
    try {
      IHtwConfiguration configuration = new HtwConfigurationBuilder().build();
      IHtwGame game = new HtwGameBuilder(configuration).build();
    } catch (Exception e){
      fail("Valid build() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGameBuilderConstructor() {
    IHtwGame game = new HtwGameBuilder(null).build();
    fail("Invalid constructor should have failed.");
  }

  private IHtwConfigurationBuilder configBuilder = (IHtwConfigurationBuilder)
          new HtwConfigurationBuilder()
                  .setBatFrequency(0.2)
                  .setPitFrequency(0.3)
                  .setRowCount(3)
                  .setColumnCount(3)
                  .setStart(0, 0)
                  .setRandomSeed(1);

  @Test
  public void testMazeBuilderConstructor() {
    try {
      IMazeBuilder builder = new HtwMazeBuilder(configBuilder.build());
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMazeBuilderConstructor() {
    IMazeBuilder builder = new HtwMazeBuilder(null);
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testGenerateRoom() {
    Coordinates coordinates = new Coordinates(1, 1);
    IMazeBuilder builder = new HtwMazeBuilder(this.configBuilder.build());
    assertEquals(
            new Cave(
                    1,
                    coordinates,
                    new TunnelStrategy(),
                    System.out),
            builder.generateRoom(coordinates));
  }

  @Test
  public void testBuild() {
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(this.configBuilder.build()).build();
    assertTrue(maze.shoot(new HtwPlayer("Joe", 10), 2, 1));
  }

  @Test
  public void testPlayerConstructor() {
    try {
      IHtwPlayer player = new HtwPlayer("Joe", 10);
    } catch (Exception e) {
      fail();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorEmptyName() {
    IHtwPlayer player = new HtwPlayer("", 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNulls() {
    IHtwPlayer player = new HtwPlayer(null, 0);
  }

  @Test
  public void testDecrement() {
    IHtwPlayer player = new HtwPlayer("Joe", 2);
    player.decrementArrowCount();
    assertEquals(1, player.arrowCount());
  }

  @Test(expected = IllegalStateException.class)
  public void testEnter() {
    try {
      IHtwNode deadend = new DeadEnd();
      deadend.enter(Direction.NORTH);
      fail("Dead end node enter() should have failed.");
    } catch (IOException e) {
      // do nothing
    }
  }

  @Test
  public void testShoot() {
    IHtwNode deadend = new DeadEnd();
    assertFalse(deadend.shoot(Direction.SOUTH, 1));
  }

  @Test(expected = IllegalStateException.class)
  public void testSetStrategy() {
    IHtwNode deadend = new DeadEnd();
    deadend.setStrategy(new StandardStrategy());
    fail("Dead end node setStrategy() should have failed.");
  }

  @Test
  public void testReceive() {
    try {
      IHtwNode deadend = new DeadEnd();
      deadend.receive(new HtwPlayer("Joe", 10));
      fail("Dead end node receive() should have failed.");
    } catch (Exception e) {
      // do nothing
    }
  }

  @Test
  public void testToString() {
    IHtwNode deadend = new DeadEnd();
    assertEquals("Dead End", deadend.toString());
  }

  @Test
  public void testSmelly() {
    IHtwNode deadend = new DeadEnd();
    assertFalse(deadend.smelly(Direction.SOUTH));
  }

  @Test
  public void testDrafty() {
    IHtwNode deadend = new DeadEnd();
    assertFalse(deadend.drafty(Direction.SOUTH));
  }

  @Test
  public void testCanEnter() {
    assertFalse(new DeadEnd().canEnter());
  }

  @Test(expected = IllegalStateException.class)
  public void testNeighbors() {
    new DeadEnd().neighbors();
  }
}

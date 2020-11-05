package tests.codes.application;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import codes.application.EncoderClient;
import codes.application.Controller;
import mocks.DummyController;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the EncoderClient.
 */
public class  EncoderClientTest {
  private StringBuilder stringBuilder;
  private Controller<String, String> controller;

  @Before
  public void setup() {
    this.stringBuilder = new StringBuilder();
    this.controller = new DummyController(this.stringBuilder);
  }

  @Test
  public void testQuit() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("q");
    EncoderClient client = new EncoderClient(this.controller, in, out);
    client.run();
    assertEquals(
            "Enter: 'new', 'load', 'save', 'encode', or 'decode' ('q' or 'quit' to exit):\n"
            + "Quitting...\n", out.toString());
  }

  @Test
  public void testInvalidCommand() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("afdadfssa\nq");
    EncoderClient client = new EncoderClient(this.controller, in, out);
    client.run();
    assertEquals(
            "Enter: 'new', 'load', 'save', 'encode', or 'decode' ('q' or 'quit' to exit):\n"
            + "Command not found. Try again.\n"
            + "Enter: 'new', 'load', 'save', 'encode', or 'decode' ('q' or 'quit' to exit):\n"
            + "Quitting...\n",
            out.toString());
  }

  @Test
  public void testNewEncoder() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("new\n01\nabcd\nq");
    EncoderClient client = new EncoderClient(this.controller, in, out);
    client.run();
    assertEquals("new-01-abcd", this.stringBuilder.toString());
  }

  @Test
  public void testLoadEncoder() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("load\nfilepath\nq");
    EncoderClient client = new EncoderClient(this.controller, in, out);
    client.run();
    assertEquals("load-filepath", this.stringBuilder.toString());
  }

  @Test
  public void testSaveEncoder() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("save\nfilepath\nq");
    EncoderClient client = new EncoderClient(this.controller, in, out);
    client.run();
    assertEquals("save-filepath", this.stringBuilder.toString());
  }

  @Test
  public void testEncode() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("encode\nabcd\nq");
    EncoderClient client = new EncoderClient(this.controller, in, out);
    client.run();
    assertEquals("encode-abcd", this.stringBuilder.toString());
  }

  @Test
  public void testDecode() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("decode\n0101\nq");
    EncoderClient client = new EncoderClient(this.controller, in, out);
    client.run();
    assertEquals("decode-0101", this.stringBuilder.toString());
  }
}

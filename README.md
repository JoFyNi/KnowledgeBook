KnowledgeBook

The KnowledgeBook is a collection of programm that are included inside a Wiki system.

JTree for the organisation for all files/ methods.

Goal of KnowledgeBook
 - Listing all Methods which I got to know
 - Opportunity to get examples from old projects I made, if I have to do something similar
 - Showing all knowledge and skills about Java

Please notice that this project is not jet finished and not all the files are implemented inside the project. 


Idees:
- Blueprints -> fo each Folder a Blueprint section with base code methods

API
 - Network driver monitoring
 - autodetect
 
 
 
 Beispiel eines Java Quellcodes:
 
` public class Example {
  // instance variable
  private String message;
  // constructor
  public Example(String message) {
    this.message = message;
  }
  // method with a return value
  public String getMessage() {
    return message;
  }
  // method with a parameter
  public void setMessage(String message) {
    this.message = message;
  }
  public static void main(String[] args) {
    // local variable
    String message = "Hello, World!";
    // object creation
    Example example = new Example(message);
    // call methods on object
    System.out.println(example.getMessage());
    example.setMessage("Goodbye, World!");
    System.out.println(example.getMessage());
  }
}`

_message_ ist eine Instanzvariable, die in der Klasse _Example_ gespeichert wird.
Der Konstruktor _Example(String message)_ wird aufgerufen, wenn ein neues _Example_-Objekt erstellt wird. Es initialisiert die Instanzvariable message.
Die Methoden _getMessage()_ und _setMessage(String message)_ bieten Zugriff auf die Instanzvariable _message_. Die erste Methode gibt einen Wert zurück, die zweite Methode nimmt einen Wert entgegen.
In der _main_-Methode wird eine lokale Variable message erstellt und ein _Example_-Objekt mit diesem Wert als Argument erstellt.
Schließlich werden die Methoden _getMessage_ und _setMessage_ aufgerufen, um den Inhalt der Instanzvariable _message_ auszugeben und zu ändern.


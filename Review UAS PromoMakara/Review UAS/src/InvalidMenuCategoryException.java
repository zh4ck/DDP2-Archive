public class InvalidMenuCategoryException extends Exception {
    public InvalidMenuCategoryException(){
        super("Error: menunya gaada cik");
    }

    public InvalidMenuCategoryException(String message) {
        super(message);
    }
}

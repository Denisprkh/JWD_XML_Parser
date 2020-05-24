package by.prokhorenko.xmlparser.exception;

public class TouristVoucherParserException extends Exception{
    public TouristVoucherParserException() {
    }

    public TouristVoucherParserException(String message) {
        super(message);
    }

    public TouristVoucherParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public TouristVoucherParserException(Throwable cause) {
        super(cause);
    }
}

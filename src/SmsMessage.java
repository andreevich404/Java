import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class SmsMessage {
    private String phoneNumber;
    private String message;

    @Override
    public String toString() {
        return "SMS to " + phoneNumber + ": " + message;
    }
}

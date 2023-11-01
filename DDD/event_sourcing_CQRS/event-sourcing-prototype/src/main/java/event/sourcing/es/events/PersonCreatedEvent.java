package event.sourcing.es.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PersonCreatedEvent extends Event {
    private String personId;
    private String firstName;
    private String latName;
}

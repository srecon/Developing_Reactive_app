package event.sourcing.es.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PersonAddContactsEvent extends Event{
    private String contactType;
    private String contactDetails;
}

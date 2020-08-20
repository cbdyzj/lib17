package draft.json;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Apple {

    @NonNull
    private String name;

    @NonNull
    private String type;
}

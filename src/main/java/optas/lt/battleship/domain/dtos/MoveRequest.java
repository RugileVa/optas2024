package optas.lt.battleship.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MoveRequest {
    private int x;
    private int y;
}

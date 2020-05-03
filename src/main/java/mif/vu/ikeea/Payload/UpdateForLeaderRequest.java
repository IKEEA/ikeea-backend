package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateForLeaderRequest {

    private Integer restrictionDays;

    private Long managerId;
}

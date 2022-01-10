import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldsValueMatch.List({
        @FieldsValueMatch(
                ifStatus = Status.PENDING_EDITION,
                field = {"status", "contact", "emailContact", "paymentDeadline"}
        ),
        @FieldsValueMatch(
                ifStatus = Status.ACTIVE,
                field = {"status"}
        ),
        @FieldsValueMatch(
                ifStatus = Status.INACTIVE,
                field = {"status"}
        )
})
public class UpdateContract {
    private Status status;
    private String contactB2W;
    private String emailContactB2W;
    private Integer phoneContactB2W;
    private Integer dddPhoneContactB2W;
    private Integer extension;
    private PaymentType paymentType;
    private BreakDate paymentDeadline;
    private boolean onlineSale;
    private Boolean onlineSale;
}



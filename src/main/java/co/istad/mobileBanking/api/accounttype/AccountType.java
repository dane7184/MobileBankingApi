package co.istad.mobileBanking.api.accounttype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AccountType {
    private Integer id;
    private String name;
}

package co.istad.mobileBanking.api.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String gender;
    private String oneSignalId;
    private String studentCardId;
    private Boolean isStudent;
    private Boolean isDelete;

    // Auth
    private String email;
    private String password;
    private Boolean isVerified;
    private String verifiedCode;
}

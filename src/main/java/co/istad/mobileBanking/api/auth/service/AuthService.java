package co.istad.mobileBanking.api.auth.service;

import co.istad.mobileBanking.api.auth.web.RegisterDto;

public interface AuthService {

    void register(RegisterDto registerDto);

    void verify(String email);

    void checkVerify(String email, String verifiedCode);

}

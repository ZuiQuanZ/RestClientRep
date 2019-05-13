package jm.student.secutiry.handlers;

import jm.student.secutiry.utilities.CodeMessenger;
import jm.student.secutiry.utilities.SuccessCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CodeMessenger.setSuccessCode(SuccessCode.LOGOUT);
        super.onLogoutSuccess(request, response, authentication);
    }
}

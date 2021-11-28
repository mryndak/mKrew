package pl.mkrew.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mkrew.app.domain.UserEntity;
import pl.mkrew.app.service.EmailService;
import pl.mkrew.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/v1/user")
public class PasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public ModelAndView displayForgotPasswordPage() {
        return new ModelAndView("forgotPassword");
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) {

        Optional<UserEntity> userEntity = userService.findUserByEmail(userEmail);

        if (!userEntity.isPresent()) {
            modelAndView.addObject("errorMessage", "Konto z takim email nie istnieje");
        } else {

            UserEntity user = userEntity.get();
            user.setResetToken(UUID.randomUUID().toString());
            userService.save(user);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            emailService.sendEmail(user.getEmail(), "Reset hasła w serwisie Mkrew", "Witaj "
                    + user.getName()
                    + ". Poprosiłeś o reset hasła w serwisie mKrew. Przesyłamy link resetujący, który jest ważny 15min "
                    + "http://localhost:8080/v1/user/reset?token="
                    + user.getResetToken());

            modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
        }

        modelAndView.setViewName("forgotPassword");
        return modelAndView;
        }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

        Optional<UserEntity> user = userService.findUserByResetToken(requestParams.get("token"));

        if (user.isPresent()) {

            UserEntity resetUser = user.get();

            resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

            resetUser.setResetToken(null);

            userService.save(resetUser);

            redir.addFlashAttribute("successMessage", "Reset hasła zakończył się pomyślnie. Możesz znuw się zalogować");

            modelAndView.setViewName("redirect:login");
            return modelAndView;

        } else {
            modelAndView.addObject("errorMessage", "Błędny link do resetu hasła.");
            modelAndView.setViewName("resetPassword");
        }

        return modelAndView;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("redirect:login");
    }
}


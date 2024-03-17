package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userRepository.updateById(sessionUser.getId(), reqDTO.getPassword(), reqDTO.getEmail());
        session.setAttribute("sessionUser", newSessionUser);
        return "redirect:/";
    }

    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);
        return "user/update-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO){
        userRepository.save(reqDTO.toEntity());
        return "redirect:/login-form";
    }
    @GetMapping("/join-form")
    public String joinForm() {
        return "/user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userRepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword());

        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}

package site.metacoding.miniproject1.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject1.service.MypageService;
import site.metacoding.miniproject1.web.dto.response.StatusAllDto;

@RequiredArgsConstructor
@Controller
public class MypageController {

    private final HttpSession session;
    private final MypageService mypageService;

    @GetMapping("/applicationstatusall")
    public String getAll(Model model, String keyword) {
        StatusAllDto statusAllDto = mypageService.viewAll(keyword);
        model.addAttribute("statusAllDto", statusAllDto);
        Map<String, Object> referer = new HashMap<>();
        referer.put("keyword", statusAllDto.getKeyword());
        session.setAttribute("referer", referer);
        return "mypage/applicationStatusAll";
    }

}

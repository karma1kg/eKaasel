package bt.gov.voice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String publicVoice(ModelMap model) {
        return "index";
    }

@RequestMapping(value = "/public", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String publicVoice1(ModelMap modelMap){
    return "index";
}

}

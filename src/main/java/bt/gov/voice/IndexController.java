package bt.gov.voice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * map for load at start
 * @return public page
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String publicVoice(ModelMap model) {
        return "index";
    }

/*@RequestMapping(value = "/public", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String publicVoice(ModelMap modelMap){
    return "index";
}*/
@RequestMapping(value = "/user_status",method = {RequestMethod.GET, RequestMethod.HEAD})
public String user_status(ModelMap modelMap,HttpServletRequest request){
    return "user_status";
}
}


package mona.joyhentai.controller;

import mona.joyhentai.Util.JoyConfig;
import mona.joyhentai.model.JoyResult;
import mona.joyhentai.service.JoyConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @Author manonmona
 * @@Date 2021/10/26 15:49
 */
@RestController
public class JoyConfigController {

    @Autowired
    JoyConfigService joyConfigService;

    @GetMapping("/config/save")
    public JoyResult saveJoyConfig(){

        return null;
    }

    @GetMapping("/config/get")
    public JoyResult getJoyConfig() throws IOException, URISyntaxException {
        JoyConfig joyConfig = joyConfigService.getJoyConfig();
        JoyResult joyResult = new JoyResult();
        joyResult.setCode(0);
        joyResult.setMsg("成功");
        joyResult.setData(joyConfig);
        return joyResult;
    }
}
